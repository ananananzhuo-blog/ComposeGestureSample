package com.ananananzhuo.composegesturesample

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * author  :mayong
 * function:实现普通控件水平和竖直滑动
 * date    :2021/12/19
 **/

@Composable
fun HorAndVerScrollView() {
    val list = (0..255)
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            Modifier
                .fillMaxWidth(10f)
                .height(100.dp)
                .background(Color.Green)
                .horizontalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "安安安安卓，欢迎关注掘金号、公众号、csdn，分享android相关知识,安安安安卓，欢迎关注掘金号、公众号、csdn，分享android相关知识,安安安安卓，欢迎关注掘金号、公众号、csdn，分享android相关知识",
                style = TextStyle(color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
        }

        repeat(40) {
            Box(
                Modifier
                    .height(80.dp)
                    .fillMaxWidth()
                    .background(Color(list.random(), list.random(), list.random())),
                contentAlignment = Alignment.Center
            ) {
                Text(text = it.toString(), style = TextStyle(color = Color.White, fontSize = 20.sp))
            }
        }
    }
}