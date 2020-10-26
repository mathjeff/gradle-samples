package androidx.build
import androidx.build.AndroidXPlugin.Companion.ZIP_TEST_CONFIGS_WITH_APKS_TASK
import androidx.build.gradle.isRoot
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
        project.configureRootProject()
    }
    private fun Project.configureRootProject() {
        DiffAndDocs.configureDiffAndDocs(
            this,
            listOf(RELEASE_RULE)
        )
        TaskUpToDateValidator.setup(project)
    }
    /*private fun Project.setDependencyVersions() {
        androidx.build.dependencies.kotlinVersion = "1.4.0"
        androidx.build.dependencies.kotlinCoroutinesVersion = "1.3.9"
    }*/
}
