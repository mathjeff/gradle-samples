/*
 */
package androidx.build.gradle
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer
inline fun <reified T : Any> ExtensionContainer.getByType(): T = getByType(T::class.java)
val Project.isRoot get() = this == rootProject
