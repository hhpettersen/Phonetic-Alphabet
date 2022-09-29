package com.app.phoneticalphabet.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
        enabled = enabled
    ) {
        Text(text = text)
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