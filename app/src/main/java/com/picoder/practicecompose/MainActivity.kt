package com.picoder.practicecompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import com.picoder.practicecompose.compose.MyNavigationDeeplink
import com.picoder.practicecompose.compose.MyScreensNavigation


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent {
            //MainScreen(message = "Hello world")

            /*val countViewModel: CountViewModel = viewModel()
            val count by countViewModel.count.observeAsState(initial = 0)
            BoxScreen(count) { newCount ->
                countViewModel.onCountChanged(newCount)
            }*/
            //MyScreensNavigation()
            MyNavigationDeeplink()
        }
    }
}

@Composable
fun MainScreen(message: String) {
    Box(
        modifier = Modifier
            .size(300.dp)
            .background(Color.Yellow)
    ) {
        Text(text = message, style = MaterialTheme.typography.h3)
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen(message = "This is first demo of compose")
}

// Column,Row,Box
@Composable
fun ItemScreen() {
    // linear layout orient vertical
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        // like frame layout
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Magenta)
                .height(300.dp)
                .weight(3f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Blue)
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.TopStart)
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.TopEnd)
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.BottomStart)
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.Green)
                    .align(Alignment.BottomEnd)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = "0",
            onValueChange = { /* TODO */ },
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        // linear layout orient horizontal
        Row(modifier = Modifier.weight(1f)) {
            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(text = "Increase")
            }

            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            ) {
                Text(text = "Decrease")
            }
        }
    }
}

@Preview
@Composable
fun PreviewItemScreen() {
    ItemScreen()
}

