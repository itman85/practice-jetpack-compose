package com.picoder.practicecompose.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ButtonUI(textMessage:String) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        var message by remember {
            mutableStateOf("")
        }
        Button(onClick = { message = textMessage }) {
            Text("Show message")
        }
        
        Text(text = message, style = MaterialTheme.typography.h2)
    }
}



@Preview
@Composable
fun PreviewButtonUI() {
    ButtonUI(textMessage = "This is my message")
}