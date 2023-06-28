/*
 */

@file:OptIn(
    InternalComposeApi::class,
)
package androidx.compose.runtime

@JvmInline
value class SkippableUpdater2<T> constructor(
    internal val composer: Composer2
) {
}

