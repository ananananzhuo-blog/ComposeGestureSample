package com.ananananzhuo.composegesturesample

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * author  :mayong
 * function:点击事件手势
 * date    :2021/12/19
 **/
@Composable
fun ClickView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(modifier = Modifier
            .size(200.dp)
            .background(Color.Green)
            .clickable {
                App.toast("被点击")
            }, contentAlignment = Alignment.Center
        ) {
            Text(text = "点击事件")
        }
    }
}