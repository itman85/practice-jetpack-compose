package com.picoder.practicecompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

// ConstraintLayout
@Composable
fun ConstrainItemScreen() {
    ConstraintLayout(modifier = Modifier.fillMaxHeight()) {
        val (box, input, increase, decrease) = createRefs()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Magenta)
                .height(300.dp)
                .constrainAs(box) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
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

        OutlinedTextField(
            value = "0",
            onValueChange = { /* TODO */ },
            modifier = Modifier
                .padding(8.dp)
                .constrainAs(input) {
                    top.linkTo(box.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .padding(8.dp)
                .constrainAs(increase) {
                    top.linkTo(input.bottom)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    end.linkTo(decrease.start)
                }
        ) {
            Text(text = "Increase")
        }

        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .padding(8.dp)
                .constrainAs(decrease) {
                    top.linkTo(increase.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(increase.bottom)
                    width = Dimension.fillToConstraints
                    start.linkTo(increase.end)
                }
        ) {
            Text(text = "Decrease")
        }
    }
}

// use ConstraintSet
@Composable
fun ConstrainItemScreen2() {
    val constrains = ConstraintSet {
        val box = createRefFor("box")
        constrain(box) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        }
        val input = createRefFor("input")
        constrain(input) {
            top.linkTo(box.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        val increase = createRefFor("increase")
        val decrease = createRefFor("decrease")

        constrain(increase) {
            top.linkTo(input.bottom)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            width = Dimension.fillToConstraints
            end.linkTo(decrease.start)
        }
        constrain(decrease) {
            top.linkTo(increase.top)
            end.linkTo(parent.end)
            bottom.linkTo(increase.bottom)
            width = Dimension.fillToConstraints
            start.linkTo(increase.end)
        }
    }
    ConstraintLayout(modifier = Modifier.fillMaxHeight(), constraintSet = constrains) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Magenta)
                .height(500.dp)
                .layoutId("box"),
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

        OutlinedTextField(
            value = "0",
            onValueChange = { /* TODO */ },
            modifier = Modifier
                .padding(8.dp)
                .layoutId("input")
        )

        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .padding(8.dp)
                .layoutId("increase")
        ) {
            Text(text = "Increase")
        }

        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .padding(8.dp)
                .layoutId("decrease")
        ) {
            Text(text = "Decrease")
        }
    }
}

@Preview
@Composable
fun PreviewConstrainItemScreen() {
    //ConstrainItemScreen()
    ConstrainItemScreen2()
}