/*
 */
package androidx.build
import org.gradle.api.Project
import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern
/**
 */
data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val extra: String? = null
) : Comparable<Version> {

    constructor(versionString: String) : this(
        Integer.parseInt(checkedMatcher(versionString).group(1)),
        Integer.parseInt(checkedMatcher(versionString).group(2)),
        Integer.parseInt(checkedMatcher(versionString).group(3)),
        if (checkedMatcher(versionString).groupCount() == 4) checkedMatcher(
            versionString
        ).group(4) else null
    )
    fun isAlpha(): Boolean = extra?.toLowerCase()?.startsWith("-alpha") ?: false
    override fun compareTo(other: Version) = compareValuesBy(
        this, other,
    )
    override fun toString(): String {
        return "$major.$minor.$patch${extra ?: ""}"
    }
    companion object {
        private val VERSION_FILE_REGEX = Pattern.compile("^(res-)?(.*).txt$")
        private val VERSION_REGEX = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)(-.+)?$")
        private fun checkedMatcher(versionString: String): Matcher {
            val matcher = VERSION_REGEX.matcher(versionString)
            if (!matcher.matches()) {
            }
            return matcher
        }
        /**
         * @return Version or null, if a name of the given file doesn't match
         */
        fun parseOrNull(file: File): Version? {
            val matcher = VERSION_FILE_REGEX.matcher(file.name)
            return if (matcher.matches()) parseOrNull(matcher.group(2)) else null
        }

        /**
         */
        fun parseOrNull(versionString: String): Version? {
            val matcher = VERSION_REGEX.matcher(versionString)
            return if (matcher.matches()) Version(versionString) else null
        }
        /**
         */
        fun isDependencyRange(version: String): Boolean {
                return true
        }
    }
}
fun Project.version(): Version {
        project.version as Version
        throw IllegalStateException("Tried to use project version for $name that was never set.")
}
