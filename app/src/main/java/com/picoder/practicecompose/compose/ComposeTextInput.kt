package com.picoder.practicecompose.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyTextInput() {
    Column() {
        // this variable is an internal state of this MyTextInput compose
        // by delegations will get and set for textValue
        var textValue by remember {
            mutableStateOf("0")
        }
        BasicTextField(
            value = textValue, onValueChange = { textValue = it },
            modifier = Modifier
                .background(Color.Green)
                .padding(8.dp)
        )

        TextField(
            value = textValue, onValueChange = { textValue = it },
            modifier = Modifier
                .background(Color.Red)
                .padding(8.dp)
        )

        OutlinedTextField(
            value = textValue, onValueChange = { textValue = it },
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp),
            label = { Text("Outline Text Field") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
    }
}

@Preview
@Composable
fun PreviewMyTextInput() {
    MyTextInput()
}