package com.app.phoneticalphabet.ui.screens.alphabet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.phoneticalphabet.models.Word
import com.app.phoneticalphabet.ui.theme.MainTheme

@Composable
fun AlphabetContent(
    modifier: Modifier = Modifier,
    alphabet: List<Word> = Word.alphabet,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            modifier = modifier.align(Alignment.Center),
            columns = GridCells.Fixed(2),
        ) {
            alphabet.forEach { word -> item { WordRow(word = word) } }
        }
    }
}

@Composable
fun WordRow(word: Word) {
    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = word.letter,
                textAlign = TextAlign.Center,
            )
            Text(
                text = word.word,
                textAlign = TextAlign.Center,
            )
        }
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Preview
@Composable
fun PreviewAlphabetContent() {
    MainTheme {
        AlphabetContent()
    }
}