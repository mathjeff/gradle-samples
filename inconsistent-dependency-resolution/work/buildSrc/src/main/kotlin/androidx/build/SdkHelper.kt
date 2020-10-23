/*
 */
package androidx.build
import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import java.io.File
import java.util.Locale
/**
 */
fun Project.writeSdkPathToLocalPropertiesFile() {
}
/**
 */
fun Project.getSdkPath(): File {
    /*if (rootProject.plugins.hasPlugin(AndroidXPlaygroundRootPlugin::class.java)) {
    }*/
    val osName = System.getProperty("os.name").toLowerCase(Locale.US)
    val isMacOsX = osName.contains("mac os x") ||
        osName.contains("osx")
    val platform = if (isMacOsX) "darwin" else "linux"
    return File(project.getCheckoutRoot(), "prebuilts/fullsdk-$platform")
}
/**
 */
fun Project.setSupportRootFolder(rootDir: File) {
    val extension = project.rootProject.property("ext") as ExtraPropertiesExtension
    return extension.set("supportRootFolder", rootDir)
}
/**
 */
fun Project.getSupportRootFolder(): File {
    val extension = project.rootProject.property("ext") as ExtraPropertiesExtension
    return extension.get("supportRootFolder") as File
}
/**
 */
fun Project.getCheckoutRoot(): File {
    return project.getSupportRootFolder().parentFile.parentFile
}
