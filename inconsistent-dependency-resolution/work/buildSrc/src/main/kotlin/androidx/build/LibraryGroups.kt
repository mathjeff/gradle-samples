/*
 */
package androidx.build
/**
 */
object LibraryGroups {
    val AUTOFILL = LibraryGroup("androidx.autofill", LibraryVersions.AUTOFILL)
    val CORE = LibraryGroup("androidx.core", null)
    val ENTERPRISE = LibraryGroup("androidx.enterprise", LibraryVersions.ENTERPRISE)
    val HEIFWRITER = LibraryGroup("androidx.heifwriter", LibraryVersions.HEIFWRITER)
    val LEANBACK = LibraryGroup("androidx.leanback", null)
    val MEDIA2 = LibraryGroup("androidx.media2", LibraryVersions.MEDIA2)
    val MEDIAROUTER = LibraryGroup("androidx.mediarouter", LibraryVersions.MEDIAROUTER)
    val PAGING = LibraryGroup("androidx.paging", LibraryVersions.PAGING)
    val RECYCLERVIEW = LibraryGroup("androidx.recyclerview", null)
    val REMOTECALLBACK = LibraryGroup("androidx.remotecallback", LibraryVersions.REMOTECALLBACK)
    val ROOM = LibraryGroup("androidx.room", LibraryVersions.ROOM)
    object Compose {
    }
}
/**
 */
data class LibraryGroup(val group: String = "unspecified", val forcedVersion: Version?) {
}
