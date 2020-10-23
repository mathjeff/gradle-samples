/*
 */
package androidx.build
import androidx.build.SupportConfig.COMPILE_SDK_VERSION
//import androidx.build.dokka.Dokka.configureAndroidProjectForDokka
import androidx.build.gradle.getByType
//import androidx.build.checkapi.LibraryApiTaskConfig
//import androidx.build.checkapi.configureProjectForApiTasks
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.LibraryPlugin
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.api.ApkVariant
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskProvider
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.findByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File
import java.util.concurrent.ConcurrentHashMap
/**
 */
class AndroidXPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create<AndroidXExtension>(EXTENSION_NAME, project)

        project.plugins.all { plugin ->
            when (plugin) {
                is LibraryPlugin -> configureWithLibraryPlugin(project, extension)
                is AppPlugin -> configureWithAppPlugin(project, extension)
            }
        }
    }
    private fun configureWithAppPlugin(project: Project, androidXExtension: AndroidXExtension) {
        val appExtension = project.extensions.getByType<AppExtension>().apply {
            configureAndroidCommonOptions(project, androidXExtension)
        }
    }
    private fun configureWithLibraryPlugin(
        project: Project,
        androidXExtension: AndroidXExtension
    ) {
        val libraryExtension = project.extensions.getByType<LibraryExtension>().apply {
            configureAndroidCommonOptions(project, androidXExtension)
        }
        //project.configureAndroidProjectForDokka(libraryExtension, androidXExtension)
        //DiffAndDocs.get(project).registerAndroidProject(libraryExtension, androidXExtension)
        //DiffAndDocs.get(project).registerPrebuilts(libraryExtension)//, androidXExtension)
        DiffAndDocs.get(project).registerPrebuilts(androidXExtension)
    }
    private fun TestedExtension.configureAndroidCommonOptions(
        project: Project,
        androidXExtension: AndroidXExtension
    ) {
        buildToolsVersion = "30.0.2"
    }
    companion object {
        //const val BUILD_ON_SERVER_TASK = "buildOnServer"
        //const val BUILD_TEST_APKS_TASK = "buildTestApks"
        //const val REPORT_LIBRARY_METRICS_TASK = "reportLibraryMetrics"
        const val ZIP_TEST_CONFIGS_WITH_APKS_TASK = "zipTestConfigsWithApks"
        const val EXTENSION_NAME = "androidx"
        //const val USE_MAX_DEP_VERSIONS = "useMaxDepVersions"
    }

}
