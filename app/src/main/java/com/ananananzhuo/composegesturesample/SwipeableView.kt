package com.ananananzhuo.composegesturesample

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

/**
 * author  :mayong
 * function:
 * date    :2021/12/19
 **/


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipableView() {
    val list = (0..255)

    val width = 300.dp
    val squareSize = 60.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(
        0f to 0,
        sizePx to 1,
        sizePx * 2 to 2,
        sizePx * 3 to 3,
        sizePx * 4 to 4
    ) // Maps anchor points (in px) to states

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Row(
            Modifier
                .width(width)
                .height(squareSize)) {
            repeat(5) {
                Box(
                    Modifier
                        .width(squareSize)
                        .fillMaxHeight()
                        .background(color = Color(list.random(), list.random(), list.random()))
                )
            }
        }
        Box(
            modifier = Modifier
                .width(width)
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Horizontal
                )
        ) {
            Box(
                Modifier
                    .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                    .width(squareSize)
                    .height(200.dp)
                    .background(Color.Yellow)
            ){
                Text(text = "滑动滑块")
            }
        }
    }

}
