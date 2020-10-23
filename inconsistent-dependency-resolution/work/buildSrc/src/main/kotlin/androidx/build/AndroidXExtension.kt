/*
 */
package androidx.build
import groovy.lang.Closure
import org.gradle.api.Project
/**
 */
open class AndroidXExtension(val project: Project) {
    var name: String? = null
    var mavenVersion: Version? = null
    var mavenGroup: LibraryGroup? = null
    private fun chooseProjectVersion() {
    }
    private fun verifyVersionExtraFormat(version: Version) {
    }
    private fun isGroupVersionOverrideAllowed(): Boolean {
        val version = mavenVersion
        return version != null && version.major == 1 && version.minor == 0 && version.patch == 0 &&
            version.isAlpha()
    }
    private var versionIsSet = false
    fun isVersionSet(): Boolean {
        return versionIsSet
    }
    var inceptionYear: String? = null
    /**
     */
    private var licenses: MutableCollection<License> = ArrayList()
    var publish: Publish = Publish.NONE
    /**
     */
    var type: LibraryType = LibraryType.UNSET
    var failOnDeprecationWarnings = true
    var compilationTarget: CompilationTarget = CompilationTarget.DEVICE
    /**
     */
    /**
     */
    // TODO: decide whether we want to support overriding generateDocs
    fun license(closure: Closure<*>): License {
        val license = project.configure(License(), closure) as License
        return license
    }
    fun getLicenses(): Collection<License> {
        return licenses
    }

    companion object {
    }
}
class License {
}
