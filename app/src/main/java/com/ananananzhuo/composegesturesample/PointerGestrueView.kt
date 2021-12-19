package com.ananananzhuo.composegesturesample

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

/**
 * author  :mayong
 * function:
 * date    :2021/12/19
 **/
@Composable
fun PointerView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Green)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            App.toast("单点事件触发")
                        },
                        onDoubleTap = {
                            App.toast("双击事件")
                        },
                        onPress = {
//                            App.toast("按压事件")
                        },
                        onLongPress = {
                            App.toast("长按事件")
                        }
                    )
                }, contentAlignment = Alignment.Center
        ) {
            Text(text = "单点、双击、按压、长按事件")
        }
    }
}