package androidx.build
import androidx.build.Strategy.Prebuilts
import androidx.build.Strategy.TipOfTree
import androidx.build.doclava.DoclavaTask
//import androidx.build.docs.GenerateDocsTask
import androidx.build.gradle.isRoot
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.api.SourceKind
import com.google.common.base.Preconditions
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ResolveException
import org.gradle.api.file.FileCollection
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Zip
import org.gradle.api.tasks.javadoc.Javadoc
import java.io.File
private const val DOCLAVA_DEPENDENCY = "com.android:doclava:1.0.6"
data class DacOptions(val libraryroot: String, val dataname: String)
class DiffAndDocs private constructor(
    root: Project,
    additionalRules: List<PublishDocsRules> = emptyList()
) {
    private val docsProject: Project?
    private val rules: List<PublishDocsRules>
    private val docsTasks: MutableMap<String, TaskProvider<DoclavaTask>> = mutableMapOf()
    init {
        val doclavaConfiguration = root.configurations.create("doclava")
        doclavaConfiguration.dependencies.add(root.dependencies.create(DOCLAVA_DEPENDENCY))
        rules = additionalRules //+ TIP_OF_TREE
        docsProject = root.findProject(":docs-fake")
        rules.forEach { rule ->
            val generateDocsTask = createGenerateDocsTask(
                project = root,
                doclavaConfig = doclavaConfiguration,
                destDir = File(root.docsDir(), rule.name),
                taskName = "${rule.name}DocsTask"
            )
            generateDocsTask.configure { task ->
                task.doFirst {
                    System.out.println("Jeff printing initial classpath for " + task)
                    task.classpath.forEach { f ->
                        System.out.println("" + f)
                    }
                    System.out.println("Jeff done printing initial classpath for " + task)
                }
            }

            docsTasks[rule.name] = generateDocsTask
        }
    }
    companion object {
        private const val EXT_NAME = "DIFF_AND_DOCS_EXT"
        fun get(project: Project): DiffAndDocs {
            return project.rootProject.extensions.findByName(EXT_NAME) as? DiffAndDocs
                ?: throw IllegalStateException("must call configureDiffAndDocs first")
        }
        fun configureDiffAndDocs(
            root: Project,
            additionalRules: List<PublishDocsRules> = emptyList()
        ) {
            Preconditions.checkState(
                root.extensions.findByName(EXT_NAME) == null,
            )
            val instance = DiffAndDocs(
                root = root,
                additionalRules = additionalRules
            )
            root.extensions.add(EXT_NAME, instance)
            instance.setupDocsProject()
        }
    }
    private fun prebuiltSources(
        root: Project,
        mavenId: String,
        originName: String,
        originRule: DocsRule
    ): FileTree {
        val configuration = root.configurations.detachedConfiguration(
            root.dependencies.create(mavenId)
        )
        val artifacts = try {
            configuration.resolvedConfiguration.resolvedArtifacts
        } catch (e: ResolveException) {
            root.logger.error(
                    "or add overriding \"ignore\" or \"tipOfTree\" rules"
            )
            throw e
        }
        val artifact = artifacts.find { it.moduleVersion.id.toString() == mavenId }
            ?: throw GradleException()
        System.out.println("Jeff resolving prebuiltSources " + mavenId + ", got " + artifact)
        val folder = artifact.file.parentFile
        val tree = root.zipTree(File(folder, "${artifact.file.nameWithoutExtension}-sources.jar"))
        return tree
    }
    private fun setupDocsProject() {
        docsProject?.afterEvaluate { docs ->
            System.out.println("Jeff DocsProject afterEvaluate")
            val appExtension = docs.extensions.findByType(AppExtension::class.java)
                ?: throw GradleException("Android app plugin is missing on docsProject")
            rules.forEach { rule ->
                System.out.println("Jeff rules foreach found rule " + rule + " (" + rule.name + ")")
                appExtension.productFlavors.register(rule.name)
            }
            appExtension.applicationVariants.all { appVariant ->
                System.out.println("Jeff variants foreach found variant " + appVariant + " (" + appVariant.flavorName + ")")
                val taskProvider = docsTasks[appVariant.flavorName]
                System.out.println("Jeff taskProvider = " + taskProvider + " for variant " + appVariant.flavorName)
                if (appVariant.buildType.getName() == "release" && taskProvider != null) {
                    registerAndroidProjectForDocsTask(taskProvider, appVariant)
                    val runtimeConfiguration = appVariant.runtimeConfiguration
                    taskProvider.configure({ task ->
                        task.classpath += docsProject.files(
                            docsProject.provider({
                                val resolved = docsProject.files(runtimeConfiguration.resolvedConfiguration.files)
                                System.out.println("Jeff resolved for task " + task + " these files:")
                                resolved.forEach { f ->
                                    System.out.println("" + f)
                                }
                                System.out.println("Jeff end of resolutions")
                                resolved
                            })
                        )
                    })
                }
            }
        }
    }
    fun registerPrebuilts(extension: AndroidXExtension) {
        System.out.println("Jeff DiffAndDocs registerPrebuilts " + extension)
        val docs = docsProject
        if (docs == null) {
            System.out.println("Jeff no docs project")
            return
        }
        docsProject?.afterEvaluate { docs ->
            val depHandler = docs.dependencies
            val root = docs.rootProject
            rules.forEach { rule ->
                val resolvedRule = rule.resolve(extension)
                val strategy = resolvedRule?.strategy
                System.out.println("Jeff doc strategy is " + strategy + " for group " + extension.mavenGroup + ", module " + extension.project.name)
                if (strategy is Prebuilts) {
                    val dependencyText = strategy.dependency(extension)
                    System.out.println("Jeff DiffAndDocs registerPrebuilts dependencyText = " + dependencyText)
                    val dependency = root.dependencies.create(dependencyText)
                    depHandler.add("${rule.name}Implementation", dependency)
                    docsTasks[rule.name]!!.configure {
                        it.source(
                            prebuiltSources(docs, dependencyText, rule.name, resolvedRule)
                        )
                    }
                }
            }
        }
    }
}
private fun registerAndroidProjectForDocsTask(
    taskProvider: TaskProvider<out Javadoc>,
    releaseVariant: BaseVariant
) {
    val task = taskProvider.get()
    val javaCompileProvider = releaseVariant.javaCompileProvider
    val project = task.project
    val files = releaseVariant.getCompileClasspath(null)

    task.classpath += files
    task.dependsOn(files)
    val compileTask = javaCompileProvider.get()
    task.dependsOn(compileTask)
    System.out.println("Jeff adding dependency from docs task " + task + " to compile task " + compileTask)
    task.doFirst {
        System.out.println("Jeff registered android project getting sources:")
        files.forEach { f ->
            System.out.println("" + f)
        }
        System.out.println("Jeff registered android project done getting sources")
        System.out.println("Jeff registered android project getting sources #2:")
        val files2 = releaseVariant.getCompileClasspath(null)
        files2.forEach { f ->
            System.out.println("" + f)
        }
        System.out.println("Jeff registered android project done getting sources #2")
    }
    //val compileDestDir = javaCompileProvider.get().destinationDir
    //System.out.println("Compile dest dir = " + compileDestDir)
}

private fun createGenerateDocsTask(
    project: Project,
    doclavaConfig: Configuration,
    destDir: File,
    taskName: String = "generateDocs"
): TaskProvider<DoclavaTask> =
    project.tasks.register(taskName, DoclavaTask::class.java) {
        it.apply {
            // it seems that this dependsOn(doclavaConfig) is required for reproducing the problem in this smaller reproduction case
            dependsOn(doclavaConfig)
            destinationDir = destDir
        }
    }
fun Project.docsDir(): File {
    val actualRootProject = if (project.isRoot) project else project.rootProject
    return File(actualRootProject.buildDir, "javadoc")
}

