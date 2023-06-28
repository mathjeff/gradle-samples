package androidx.compose.ui.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReusableComposeNode2
import androidx.compose.ui.Modifier2

@Suppress("ComposableLambdaParameterPosition")
@Composable
inline fun Layout2(
    content: @Composable () -> Unit,
    modifier: Modifier2 = Modifier2
) {
    ReusableComposeNode2(
        update = {
        },
        skippableUpdate = materializerOf2(modifier)
    )
}

fun materializerOf2(
    modifier: Modifier2
): @Composable Any.() -> Unit = {
}

