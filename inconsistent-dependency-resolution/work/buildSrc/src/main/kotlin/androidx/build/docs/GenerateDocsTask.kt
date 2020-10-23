package androidx.build.docs
import androidx.build.doclava.DoclavaTask
open class GenerateDocsTask : DoclavaTask() {
    private data class Since(val path: String, val apiLevel: String)
    private data class Artifact(val path: String, val artifact: String)
    private val sinces = mutableListOf<Since>()
}