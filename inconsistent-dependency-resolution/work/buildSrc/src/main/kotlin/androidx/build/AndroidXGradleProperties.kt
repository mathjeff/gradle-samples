/*
 */

package androidx.build
import org.gradle.api.Project
/**
 */
const val TEST_FAILURES_DO_NOT_FAIL_TEST_TASK = "androidx.ignoreTestFailures"
/**
 */
const val DISPLAY_TEST_OUTPUT = "androidx.displayTestOutput"
/**
 */
/**
 */
const val COVERAGE_ENABLED = "androidx.coverageEnabled"
/**
 */
const val ENABLE_DOCUMENTATION = "androidx.enableDocumentation"
/**
 */
const val SUMMARIZE_STANDARD_ERROR = "androidx.summarizeStderr"
/**
 */
const val WRITE_VERSIONED_API_FILES = "androidx.writeVersionedApiFiles"
/**
 */
const val STUDIO_TYPE = "androidx.studio.type"
/**
 */
/**
 */
/**
 */
const val PLAYGROUND_DOKKA_BUILD_ID = "androidx.playground.dokkaBuildId"
/**
 */
val ALL_ANDROIDX_PROPERTIES = setOf(
    TEST_FAILURES_DO_NOT_FAIL_TEST_TASK,
)
/**
 */
fun Project.validateAllAndroidxArgumentsAreRecognized() {
}
/**
 */
fun Project.isDisplayTestOutput(): Boolean =
    (project.findProperty(DISPLAY_TEST_OUTPUT) as? String)?.toBoolean() ?: true
/**
 */
fun Project.isWriteVersionedApiFilesEnabled(): Boolean =
    (project.findProperty(WRITE_VERSIONED_API_FILES) as? String)?.toBoolean() ?: true
/**
 */
fun Project.isDocumentationEnabled(): Boolean =
    (project.findProperty(ENABLE_DOCUMENTATION) as? String)?.toBoolean() ?: true
/**
 */
fun Project.isCoverageEnabled(): Boolean =
    (project.findProperty(COVERAGE_ENABLED) as? String)?.toBoolean() ?: false
/**
 */
fun Project.studioType() = StudioType.findType(
    findProperty(STUDIO_TYPE)?.toString()
)
enum class StudioType {
    ANDROIDX,
    COMPOSE;
    companion object {
        fun findType(value: String?) = when (value) {
            null, "androidx" -> ANDROIDX
            else -> error("Invalid project type $value")
        }
    }
}
