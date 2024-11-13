package com.startzplay.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.startzplay.R


@Composable
fun LoadImage(
    url: String,
    modifier: Modifier = Modifier,
    scale: ContentScale = ContentScale.FillBounds,
    emptyImagePlaceHolder: Int = R.drawable.no_image,
    emptyImagePlaceHolderSize: Dp = 50.dp
) {
    if (url.isEmpty()) {
        Image(
            painter = painterResource(emptyImagePlaceHolder),
            contentDescription = null,
            modifier = modifier.wrapContentSize().size(emptyImagePlaceHolderSize),
        )
    } else {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = modifier,
            contentScale = scale
        )
    }
}