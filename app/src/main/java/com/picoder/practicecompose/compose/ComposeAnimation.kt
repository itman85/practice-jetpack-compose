package com.picoder.practicecompose.compose

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picoder.practicecompose.R

@ExperimentalAnimationApi
@Composable
fun AnimatedVisibilitySample() {
    val (checked, onCheckedChange) = remember {
        mutableStateOf(true)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .shadow(2.dp),
    ) {
        Row(Modifier.padding(16.dp)) {
            Checkbox(checked = checked, onCheckedChange = onCheckedChange)
            Text(text = "Show content", modifier = Modifier.align(Alignment.CenterVertically))
        }
        AnimatedVisibility(
            visible = checked, modifier = Modifier.padding(16.dp),
            enter = fadeIn() + expandIn(expandFrom = Alignment.TopStart) + slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    visibilityThreshold = IntOffset(200, 100)
                )
            ),
            exit = shrinkOut(shrinkTowards = Alignment.BottomStart, animationSpec = tween(1000))
        ) {
            Box(modifier = Modifier.size(400.dp), contentAlignment = Alignment.BottomCenter) {
                Image(
                    painter = painterResource(id = R.drawable.field_small),
                    contentDescription = "field content",
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Jetpack compose animation",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Yellow,
                    modifier = Modifier
                        .align(Alignment.Center)
                        // ExperimentalAnimationApi
                        .animateEnterExit(
                            enter = slideInVertically(
                                initialOffsetY = { 2 * it },
                                animationSpec = tween(2000)
                            )
                        ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewAnimatedVisibilitySample() {
    AnimatedVisibilitySample()
}