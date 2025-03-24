package com.ascoding.transfluent.common.utils.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.Font
import transfluent.composeapp.generated.resources.Asap_Bold
import transfluent.composeapp.generated.resources.Asap_Medium
import transfluent.composeapp.generated.resources.Asap_Regular
import transfluent.composeapp.generated.resources.Res

@Composable
fun Type(typeMode: TypeMode): FontFamily {
    return when (typeMode) {
        TypeMode.REGULAR ->
            FontFamily(
                Font(Res.font.Asap_Regular)
            )

        TypeMode.MEDIUM ->
            FontFamily(
                Font(Res.font.Asap_Medium)
            )

        TypeMode.BOLD ->
            FontFamily(
                Font(Res.font.Asap_Bold)
            )
    }
}

enum class TypeMode {
    REGULAR, BOLD, MEDIUM
}