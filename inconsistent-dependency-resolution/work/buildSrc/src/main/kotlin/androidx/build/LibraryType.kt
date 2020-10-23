/*
 */
package androidx.build
import org.gradle.api.Project
/**
 */
enum class LibraryType(
    val publish: Publish = Publish.NONE,
    val sourceJars: Boolean = false,
    val generateDocs: Boolean = false,
    val checkApi: RunApiTasks = RunApiTasks.No("Unknown Library Type"),
    val compilationTarget: CompilationTarget = CompilationTarget.DEVICE
) {
    SAMPLES(Publish.SNAPSHOT_AND_RELEASE, true, false, RunApiTasks.No("Sample Library")),
    ANNOTATION_PROCESSOR(
        Publish.SNAPSHOT_AND_RELEASE, false, true,
    ),
    UNSET()
}
fun Project.isSamplesProject(): Boolean {
    return project.extensions.findByType(AndroidXExtension::class.java)?.type == LibraryType.SAMPLES
}
enum class CompilationTarget {
    HOST,
    DEVICE
}
/**
 */
enum class Publish {
    NONE, SNAPSHOT_ONLY, SNAPSHOT_AND_RELEASE;
}
sealed class RunApiTasks {
    data class No(val reason: String) : RunApiTasks()
}
