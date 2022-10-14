package com.app.phoneticalphabet.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.phoneticalphabet.ui.components.PhonButton
import com.app.phoneticalphabet.ui.theme.MainTheme
import com.app.phoneticalphabet.ui.theme.rubik

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAlphabetClicked: () -> Unit,
    onQuizClicked: () -> Unit,
    onFlashCardsClicked: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Row {
                Text(
                    text = "Phon",
                    fontFamily = rubik,
                    fontWeight = FontWeight.Normal,
                    fontSize = 48.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "etic",
                    fontFamily = rubik,
                    fontWeight = FontWeight.Light,
                    fontSize = 48.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(
                "alphabet",
                fontFamily = rubik,
                fontWeight = FontWeight.Light,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        ButtonGroup(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            onAlphabetClicked = onAlphabetClicked,
            onQuizClicked = onQuizClicked,
            onFlashCardsClicked = onFlashCardsClicked
        )
    }
}

@Composable
fun ButtonGroup(
    modifier: Modifier = Modifier,
    onAlphabetClicked: () -> Unit,
    onQuizClicked: () -> Unit,
    onFlashCardsClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(bottom = 56.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            PhonButton(
                modifier = Modifier.weight(1f),
                onClick = onFlashCardsClicked
            ) {
                Text(text = "Flashcards")
            }
            PhonButton(
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                onClick = onAlphabetClicked
            ) {
                Text(text = "Alphabet")
            }
        }
        PhonButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onQuizClicked
        ) {
            Text(text = "Quiz")
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    MainTheme {
        HomeScreen(
            onAlphabetClicked = {},
            onQuizClicked = {},
            onFlashCardsClicked = {},
        )
    }
}
