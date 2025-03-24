package com.ascoding.transfluent.common.utils.extensions

import androidx.compose.ui.Modifier

inline fun Modifier.applyIf(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
): Modifier {
    return if (condition) then(modifier()) else this
}
