package androidx.compose.runtime

import kotlin.coroutines.CoroutineContext

interface Composer2 {
}

abstract class ComposerImpl2(
) : Composer2 {
    private var compositionToken: Int = 0
}

@JvmInline
value class Updater2<T> constructor(
    internal val sampleValue: Int
) {

    inline fun update(
        value: Int,
        block: T.(value: Int) -> Unit
    ) {
    }
}

val currentComposer2: Composer2
    @ReadOnlyComposable
    @Composable get() { throw Exception("Not implemented") }
