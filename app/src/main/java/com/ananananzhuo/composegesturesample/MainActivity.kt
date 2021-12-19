package com.ananananzhuo.composegesturesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ananananzhuo.composegesturesample.ui.theme.ComposeGestureSampleTheme
import com.ananananzhuo.composelib.ListView
import com.ananananzhuo.composelib.bean.ItemData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeGestureSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = HOME) {
        composable(HOME) {
            Home(controller)
        }
        composable(CLICK) {
            ClickView()
        }
        composable(POINTER) {
            PointerView()
        }
        composable(VERANDHORSCROLL) {
            HorAndVerScrollView()
        }
        composable(DRAG_VIEW) {
            DragView()
        }
        composable(SWIPE_VIEW) {
            SwipableView()
        }
        composable(TRANS_SCALE_ROTATION) {
            TransScaleRotationView()
        }
        composable(NEST_SCROLL){
            NestScrollView()
        }
    }
}

const val HOME = "home"
const val CLICK = "click"
const val POINTER = "pointer"
const val VERANDHORSCROLL = "verAndhorScroll"
const val DRAG_VIEW = "drag_view"
const val SWIPE_VIEW = "swipe_view"
const val TRANS_SCALE_ROTATION = "trans_scale_rotation"
const val NEST_SCROLL = "nest_scroll"

@Composable
fun Home(controller: NavHostController) {
    ListView(datas = mutableListOf(
        ItemData(title = "点击事件手势", tag = CLICK),
        ItemData(title = "手指手势 单点、按压、双击、长按", content = "按压事件只要触碰控件就会触发", tag = POINTER),
        ItemData(title = "实现普通控件的水平和竖直可滑动", tag = VERANDHORSCROLL),
        ItemData(title = "拖动手势", tag = DRAG_VIEW),
        ItemData(title = "简单范围较小的惯性滑动", tag = SWIPE_VIEW),
        ItemData(title = "双指平移、缩放、旋转手势", tag = TRANS_SCALE_ROTATION),
        ItemData(title = "嵌套滑动效果实现", content = "直接上官方的demo例子", tag = NEST_SCROLL)
    ), click = { itemData: ItemData, i: Int, i1: Int ->
        controller.navigate(itemData.tag)
    })
}