package com.picoder.practicecompose.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picoder.practicecompose.R

@Composable
fun TextUI(text: String) {
    Column() {
        // download font file *.ttf from fonts.google.com
        val customFont = Font(R.font.dancingscript_variablefont_wght)
        val customFontFamily = FontFamily(customFont)
        // from foundation
        BasicText(
            text = "Welcome", modifier = Modifier
                .padding(4.dp)
                .border(1.dp, Color.White),
            style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.Bold)
        )

        BasicText(
            text = text, modifier = Modifier
                .padding(4.dp),
            style = TextStyle.Default.copy(
                color = Color.Red,
                fontSize = 50.sp,
                fontFamily = customFontFamily
            )
        )

        Spacer(modifier = Modifier.size(16.dp))

        // from material
        Text(
            text = text, modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.h6.copy(
                color = Color.Green,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic
            )
        )

    }
}

@Preview
@Composable
fun PreviewTextUI() {
    val textToDisplay = "Hello world! Welcome to my Text UI component"
    TextUI(textToDisplay)
}