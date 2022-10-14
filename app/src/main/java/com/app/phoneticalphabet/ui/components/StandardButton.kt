package com.app.phoneticalphabet.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.phoneticalphabet.ui.theme.MainTheme

@Composable
fun StandardButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 72.dp),
        onClick = onClick,
        enabled = enabled,
    ) {
        Text(text = text)
    }
}

@Composable
fun PhonButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(5.dp),
        colors = colors,
        onClick = onClick,
        content = content
    )
}

@Preview
@Composable
fun PreviewPhonButton() {
    MainTheme {
        PhonButton(onClick = { /*TODO*/ }) {
            Text(text = "Test")
        }
    }
}

@Preview
@Composable
fun PreviewStandButton() {
    MainTheme {
        StandardButton(
            text = "Test",
            onClick = {}
        )
    }
}