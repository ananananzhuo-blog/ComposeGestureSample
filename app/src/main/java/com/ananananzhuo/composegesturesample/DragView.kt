package com.ananananzhuo.composegesturesample

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

/**
 * author  :mayong
 * function:滑动View
 * date    :2021/12/19
 **/
@Composable
fun DragView() {
    var offsetX by remember {
        mutableStateOf(0f)
    }
    var offsetY by remember {
        mutableStateOf(0f)
    }
    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) }
                .size(200.dp)
                .background(Color.Green, shape = CircleShape)
                .pointerInput(Unit) {
                    detectDragGestures(onDrag = { pointerInputChange: PointerInputChange, offset: Offset ->
                        pointerInputChange.consumeAllChanges()//消费滑动
                        offsetX += offset.x
                        offsetY += offset.y
                    })
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "轻按滑动圆")
        }
    }
}