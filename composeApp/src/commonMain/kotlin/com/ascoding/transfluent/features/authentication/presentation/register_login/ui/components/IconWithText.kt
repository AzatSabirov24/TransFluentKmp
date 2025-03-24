package com.ascoding.transfluent.features.authentication.presentation.register_login.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ascoding.transfluent.common.utils.ui.Type
import com.ascoding.transfluent.common.utils.ui.TypeMode
import com.ascoding.transfluent.theme.LocalExtendedColorScheme

@Composable
fun IconWithText(
    icon: ImageVector,
    text: String,
    textStyle: TextStyle = TextStyle(
        fontFamily = Type(TypeMode.REGULAR),
        fontSize = 16.sp,
        color = LocalExtendedColorScheme.current.textRegular
    ),
    iconTint: Color,
    contentDescription: String
) {
    Row(
        modifier = Modifier
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = iconTint
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = text,
            style = textStyle
        )
    }
}