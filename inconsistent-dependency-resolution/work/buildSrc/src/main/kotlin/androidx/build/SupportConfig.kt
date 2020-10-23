/*
 */
package androidx.build
import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import java.io.File
object SupportConfig {
    const val DEFAULT_MIN_SDK_VERSION = 14
    const val INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    const val BUILD_TOOLS_VERSION = "30.0.2"
    /**
     */
    const val COMPILE_SDK_VERSION = "android-30"
    /**
     */
    const val TARGET_SDK_VERSION = 30
    fun isUiProject() = System.getenv("DIST_SUBDIR") == "/ui"
    fun getJavaToolsJarPath() = System.getenv("JAVA_TOOLS_JAR")
}
fun Project.getExternalProjectPath(): File {
    return File(project.getCheckoutRoot(), "external")
}
fun distSubdir(): String {
    val subdir = System.getenv("DIST_SUBDIR")
    if (subdir != null && subdir.isNotEmpty()) {
    }
    return ""
}
fun Project.getKeystore(): File {
    return File(project.getSupportRootFolder(), "development/keystore/debug.keystore")
}
fun Project.getPrebuiltsRoot(): File {
    val ext = project.rootProject.property("ext") as ExtraPropertiesExtension
    val reposProperties = ext.get("repos") as Map<*, *>
    return File(reposProperties["prebuiltsRoot"].toString())
}
