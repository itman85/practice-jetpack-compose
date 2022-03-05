package com.picoder.practicecompose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Preview
@Composable
fun PreviewBoxScreen() {
    /*var count by remember {
        mutableStateOf(0)
    }
     BoxScreen(count) { newCount ->
        count = newCount
    }
    */
    /**
    The componentN() operators allow state objects to be used with the property destructuring syntax
    var (foo, setFoo) = remember { mutableStateOf(0) }
    setFoo(123) // set
    foo == 123 // get
    MutableState has component1() and component2()
    operator fun component1(): T
    operator fun component2(): (T) -> Unit
     */
    val (count, onCountChange) = remember {
        mutableStateOf(0)
    }
    BoxScreen(count) { newCount ->
        onCountChange(newCount)
    }
}

// stateless compose (no state inside)
// whenever compose state changed, it will call code in @composable again
@Composable
fun BoxScreen(count: Int, onCountChange: (Int) -> Unit) {
    val boxSize = 400.dp
    /*
    // count as state in side compose
    var count by remember {
        mutableStateOf(0)
    }*/
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(boxSize)
                .background(Color.Red),
            contentAlignment = Alignment.Center
        ) {
            var childSize = boxSize - 20.dp
            for (i in 0 until count) {
                Box(
                    modifier = Modifier
                        .size(childSize)
                        .rotate(i * 3f)
                        .background(Color.Gray)
                        .border(1.dp, Color.Black)
                )
                childSize -= 20.dp
            }
        }

        OutlinedTextField(value = "$count", onValueChange = {})

        Row() {
            Button(onClick = { onCountChange(count + 1) }, Modifier.padding(8.dp)) {
                Text("Increase")
            }

            Button(
                onClick = { onCountChange(if (count <= 0) 0 else count - 1) },
                Modifier.padding(8.dp)
            ) {
                Text("Decrease")
            }
        }
    }
}



