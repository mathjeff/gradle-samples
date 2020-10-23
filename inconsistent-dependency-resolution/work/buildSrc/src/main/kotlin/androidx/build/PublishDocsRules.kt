/*
 */

package androidx.build
import androidx.build.ArtifactsPredicate.All
import androidx.build.ArtifactsPredicate.Benchmark
import androidx.build.ArtifactsPredicate.Exact
import androidx.build.ArtifactsPredicate.Group
import androidx.build.Strategy.Ignore
import androidx.build.Strategy.Prebuilts
import androidx.build.Strategy.TipOfTree
/**
 */
val RELEASE_RULE = docsRules("public", false) {
    prebuilts(LibraryGroups.AUTOFILL, "1.1.0-alpha02")
    prebuilts(LibraryGroups.LEANBACK, "1.1.0-alpha05")
    prebuilts(LibraryGroups.MEDIA2, "media2-widget", "1.1.0-beta01")
    prebuilts(LibraryGroups.MEDIAROUTER, "1.2.0-rc02")
    prebuilts(LibraryGroups.PAGING, "3.0.0-alpha07")
    prebuilts(LibraryGroups.RECYCLERVIEW, "recyclerview", "1.2.0-alpha06")
    prebuilts(LibraryGroups.ROOM, "2.3.0-alpha02")
}
/**
 */
val TIP_OF_TREE = docsRules("tipOfTree", true) {
    default(TipOfTree)
}
fun docsRules(
    name: String,
    offline: Boolean,
    init: PublishDocsRulesBuilder.() -> Unit
): PublishDocsRules {
    val f = PublishDocsRulesBuilder(name, offline)
    f.init()
    return f.build()
}
/**
 */
class PublishDocsRulesBuilder(private val name: String, private val offline: Boolean) {
    private val rules: MutableList<DocsRule> = mutableListOf(DocsRule(Benchmark, Ignore))
    /**
     */
    fun tipOfTree(groupName: String) {
    }

    /**
     */
    /**
     */
    fun prebuilts(libraryGroup: LibraryGroup, moduleName: String, version: String): Prebuilts {
        val strategy = Prebuilts(Version(version))
        rules.add(DocsRule(Exact(libraryGroup.group, moduleName), strategy))
        return strategy
    }
    /**
     */
    fun prebuilts(libraryGroup: LibraryGroup, version: String) =
        prebuilts(libraryGroup, Version(version))
    /**
     */
    fun prebuilts(libraryGroup: LibraryGroup, version: Version): Prebuilts {
        val strategy = Prebuilts(version)
        rules.add(DocsRule(Group(libraryGroup.group), strategy))
        return strategy
    }
    /**
     */
    fun default(strategy: Strategy) {
        rules.add(DocsRule(All, strategy))
    }
    /**
     */
    fun ignore(groupName: String) {
    }
    /**
     */
    fun ignore(groupName: String, name: String) {
    }
    /**
     */
    fun build() = PublishDocsRules(name, offline, rules)
}
/**
 */
sealed class ArtifactsPredicate {
    /**
     */
    abstract fun apply(inGroup: String, inName: String): Boolean
    /**
     */
    object All : ArtifactsPredicate() {
        override fun apply(inGroup: String, inName: String) = true
    }
    /**
     */
    class Group(val group: String) : ArtifactsPredicate() {
        override fun apply(inGroup: String, inName: String) = inGroup == group
    }
    /**
     */
    class Exact(val group: String, val name: String) : ArtifactsPredicate() {
        override fun apply(inGroup: String, inName: String) = group == inGroup && name == inName
    }
    /**
     */
    object Benchmark : ArtifactsPredicate() {
        override fun apply(inGroup: String, inName: String) = inName.endsWith("-benchmark")
    }
}
/**
 */
data class DocsRule(val predicate: ArtifactsPredicate, val strategy: Strategy) {
}

/**
 */
sealed class Strategy {
    /**
     */
    object TipOfTree : Strategy()

    /**
     */
    object Ignore : Strategy()
    /**
     */
    class Prebuilts(val version: Version) : Strategy() {
        /**
         */
        var stubs: MutableList<String>? = null
        /**
         */
        fun addStubs(path: String) {
        }
        /**
         */
        fun dependency(extension: AndroidXExtension): String {
            return "${extension.mavenGroup?.group}:${extension.project.name}:$version"
        }
    }
}
/**
 */
class PublishDocsRules(val name: String, val offline: Boolean, private val rules: List<DocsRule>) {
    /**
     */
    fun resolve(extension: AndroidXExtension): DocsRule? {
        val mavenGroup = extension.mavenGroup
        return if (mavenGroup == null) null else resolve(mavenGroup.group, extension.project.name)
    }
    /**
     */
    fun resolve(groupName: String, moduleName: String): DocsRule {
        return rules.find { it.predicate.apply(groupName, moduleName) } ?: throw Error()
    }
}
