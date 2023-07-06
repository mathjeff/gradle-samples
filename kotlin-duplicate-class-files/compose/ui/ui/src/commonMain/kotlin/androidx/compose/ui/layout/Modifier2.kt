package androidx.compose.ui

interface Modifier2 {
    fun amIAModifier2(): Boolean

    companion object : Modifier2 {
        override fun amIAModifier2() = true
        override fun toString() = "Modifier2"
    }
}
