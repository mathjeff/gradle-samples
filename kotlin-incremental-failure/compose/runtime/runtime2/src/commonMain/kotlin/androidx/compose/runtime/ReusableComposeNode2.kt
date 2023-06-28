/*
 */

package androidx.compose.runtime

@Composable
inline fun ReusableComposeNode2(
    update: Any.() -> Unit,
    skippableUpdate: @Composable Any.() -> Unit,
) {
    Updater2<Any>(0).update()
    SkippableUpdater2<Any>(currentComposer2).skippableUpdate()
}
