/*
 */

package androidx.build
import androidx.build.AndroidXPlugin.Companion.ZIP_TEST_CONFIGS_WITH_APKS_TASK
import androidx.build.gradle.isRoot
//import androidx.build.playground.VerifyPlaygroundGradlePropertiesTask
import androidx.build.uptodatedness.TaskUpToDateValidator
import com.android.build.gradle.api.AndroidBasePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.bundling.Zip
import org.gradle.kotlin.dsl.KotlinClosure1
import org.gradle.kotlin.dsl.extra
import java.io.File
class AndroidXRootPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        //if (!project.isRoot) {
        //}
        project.configureRootProject()
    }
    private fun Project.configureRootProject() {
        // we're still halfway through applying it on the root). The check licenses code runs on the
        setDependencyVersions()
        /*val buildOnServerTask = tasks.create(
            AndroidXPlugin.BUILD_ON_SERVER_TASK,
        )
        buildOnServerTask.dependsOn(
                CreateAggregateLibraryBuildInfoFileTask::class.java
        )
        buildOnServerTask.dependsOn(
        )
        VerifyPlaygroundGradlePropertiesTask.createIfNecessary(project)?.let {
        }*/

        //val createArchiveTask = Release.getGlobalFullZipTask(this)
        //val partiallyDejetifyArchiveTask = partiallyDejetifyArchiveTask(
        //    createArchiveTask.get().archiveFile
        //)
        //if (partiallyDejetifyArchiveTask != null)
        //    buildOnServerTask.dependsOn(partiallyDejetifyArchiveTask)
        /*subprojects { project ->
            project.extra.set(
                PROJECT_OR_ARTIFACT_EXT_NAME,
                KotlinClosure1<String, Project>(
                    function = {
                        project.project(this)
                    }
                )
            )
        }*/
        /*if (partiallyDejetifyArchiveTask != null) {
        }
        val buildTestApks = tasks.register(AndroidXPlugin.BUILD_TEST_APKS_TASK)
        if (project.isCoverageEnabled()) {
        }*/
        /*val zipTestConfigsWithApks = project.tasks.register(
            ZIP_TEST_CONFIGS_WITH_APKS_TASK, Zip::class.java
        )
        zipTestConfigsWithApks.configure {
        }*/
        //if (project.isDocumentationEnabled()) {
            DiffAndDocs.configureDiffAndDocs(
                this,
                //DacOptions("androidx", "ANDROIDX_DATA"),
                listOf(RELEASE_RULE)
            )
        //}
        /*if (hasProperty(AndroidXPlugin.USE_MAX_DEP_VERSIONS)) {
        }*/
        TaskUpToDateValidator.setup(project)
        /*project.tasks.register("listTaskOutputs", ListTaskOutputsTask::class.java) { task ->
        }*/
    }
    // this somehow seems to be needed to prevent an error about being unable to find build tools version 29.0.2
    private fun Project.setDependencyVersions() {
        val buildVersions = (project.rootProject.property("ext") as ExtraPropertiesExtension)
            .let { it.get("build_versions") as Map<*, *> }
        fun getVersion(key: String) = checkNotNull(buildVersions[key]) {
        }.toString()
        androidx.build.dependencies.kotlinVersion = getVersion("kotlin")
        androidx.build.dependencies.kotlinCoroutinesVersion = getVersion("kotlin_coroutines")
        androidx.build.dependencies.hiltVersion = getVersion("hilt")
    }
    /*companion object {
        const val PROJECT_OR_ARTIFACT_EXT_NAME = "projectOrArtifact"
    }*/
}
