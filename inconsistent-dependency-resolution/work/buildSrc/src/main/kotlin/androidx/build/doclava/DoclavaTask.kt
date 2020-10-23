/*
 */
package androidx.build.doclava
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.external.javadoc.CoreJavadocOptions
import org.gradle.external.javadoc.StandardJavadocDocletOptions
import java.io.File
// external/doclava/src/com/google/doclava/Errors.java
/*val DEFAULT_DOCLAVA_CONFIG = ChecksConfig(
    errors = listOf(
    ),
    warnings = listOf(121),
    hidden = listOf(
        111, // hidden super class
    )
)
*/
private fun <E> CoreJavadocOptions.addMultilineMultiValueOption(
) {
}
open class DoclavaTask : Javadoc() {
    private var docletpath: List<File> = emptyList()
    //@Input
    //var checksConfig: ChecksConfig = DEFAULT_DOCLAVA_CONFIG
    /**
     */
    @Optional
    @Input
    var hiddenPackages: Collection<String>? = null
    /**
     */
    @Input
    var generateDocs = true
    /**
     */
    @Optional
    @OutputFile
    var apiFile: File? = null
    /**
     */
    @Optional
    @OutputFile
    var removedApiFile: File? = null
    /**
     */
    @Optional
    @OutputFile
    var keepListFile: File? = null
    /**
     */
    @Optional
    @OutputDirectory
    var stubsDir: File? = null
    init {
    }
    /**
     */
    @InputFiles
    fun getDocletpath(): List<File> {
        return docletpath
    }
    /**
     */
    fun setDocletpath(docletpath: Collection<File>) {
    }
    /**
     */
    private fun configureDoclava() = (options as StandardJavadocDocletOptions).apply {
    }
    fun coreJavadocOptions(configure: CoreJavadocOptions.() -> Unit) =
        (options as CoreJavadocOptions).configure()
    override fun generate() {
    }
}
