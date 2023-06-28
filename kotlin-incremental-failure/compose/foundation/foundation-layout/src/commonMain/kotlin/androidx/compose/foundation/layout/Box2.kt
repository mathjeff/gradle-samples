package androidx.compose.foundation.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier2
import androidx.compose.ui.layout.Layout2

@Composable
inline fun Box2(
    modifier: Modifier2 = Modifier2,
    content: @Composable BoxScope.() -> Unit
) {
    Layout2(
        content = { BoxScopeInstance.content() },
        modifier = modifier
    )
}

@Composable
fun Box2(modifier: Modifier2) {
}

interface BoxScope {
}

internal object BoxScopeInstance : BoxScope {
}
