package com.picoder.practicecompose.compose

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.picoder.practicecompose.R

@Composable
fun ComposeImages() {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Vector Graphic")
        Image(
            painter = painterResource(id = R.drawable.ic_android),
            contentDescription = "android icon",
            Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(24.dp))

        Text(text = "Bitmap Graphic")
        Image(
            painter = painterResource(id = R.drawable.field_small),
            contentDescription = "Field content",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun PreviewComposeImages() {
    ComposeImages()
}

@Composable
fun NetworkImage() {
    Column() {
        // from coil.compose
        AsyncImage(
            model = "https://picsum.photos/400/400",
            contentDescription = "Coil network image loader"
        )
    }
}

@Preview
@Composable
fun PreviewNetworkImage() {
    NetworkImage()
}