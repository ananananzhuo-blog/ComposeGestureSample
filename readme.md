## 概览
Compose提供了多个api用来实现各种手势，这些手势包括`点击、按压、双击、长按、水平垂直滑动、拖动、双指平移缩放旋转、嵌套欢动效果`

## 各种手势效果介绍

### 点击手势
#### 代码

```kt
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
```

#### 效果
![](https://files.mdnice.com/user/15648/bd4f239f-aca1-4501-a913-0b049f4c338a.gif)

### 单点、按压、双击、长按
#### 代码
```kt
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
```

#### 说明
双击、单击、长按都会触发按压的效果回调，
pointerInput.detectTapGestures
#### 效果
![](https://files.mdnice.com/user/15648/0b5e2417-771d-4f90-b0d6-12dc3c6f10ee.gif)

### 水平和垂直滑动
#### 代码

```kt
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
```

#### 说明
任何控件，只需要调用一下两个修饰符就可以实现滑动效果

`.verticalScroll(rememberScrollState())`

`.horizontalScroll(rememberScrollState())`
#### 效果
![](https://files.mdnice.com/user/15648/d6e689ab-13e5-4871-8945-56598c5ac732.gif)


### 拖动手势
#### 代码

```kt
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

```

#### 说明
#### 效果
![](https://files.mdnice.com/user/15648/9ab49378-9b06-4368-a14a-00e65ab35f24.gif)


### 多段滑动
#### 代码

```kt
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

```

#### 说明
这是个实验性质的代码

这里的关键代码如下：
这个anchors会把父容器分为五段，当滑动结束，滑动控件会自动平移到合适的区域位置。

```
 val anchors = mapOf(
        0f to 0,
        sizePx to 1,
        sizePx * 2 to 2,
        sizePx * 3 to 3,
        sizePx * 4 to 4
    )
```


#### 效果
![](https://files.mdnice.com/user/15648/9df0a5aa-53d5-4b88-813f-af9678b84f55.gif)

### 双指手势（平移、缩放、旋转）
#### 代码

```kt
var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            Modifier
                // apply other transformations like rotation and zoom
                // on the pizza slice emoji
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = rotation,
                    translationX = offset.x,
                    translationY = offset.y
                )
                // add transformable to listen to multitouch transformation events
                // after offset
                .transformable(state = state)
                .background(Color.Green)
                .fillMaxSize(0.5f),
            contentAlignment = Alignment.Center
        ){
            Text(text = "两指操作，平移，缩放，z轴旋转,单指操作不会有效果", color = Color.White)
        }
    }


```

#### 说明
#### 效果
![](https://files.mdnice.com/user/15648/3ec5ff15-bb11-44c8-943b-d05062e57cc4.gif)

### 嵌套滑动效果
#### 代码

```kt
val toolbarHeight = 48.dp//Topbar的高度
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }//topbar高度转换为px
    val toolbarOffsetHeightPx =
        remember { mutableStateOf(0f) }//topbar的动态垂直平移距离
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx.value + delta//更新topbar的动态平移距离
                toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)//保证topbar的平移距离在(-toolbarHeightPx, 0f)之间
                return Offset.Zero
            }
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        LazyColumn(contentPadding = PaddingValues(top = toolbarHeight)) {
            items(100) { index ->
                Text("I'm item $index", modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp))
            }
        }
        TopAppBar(
            modifier = Modifier
                .height(toolbarHeight)
                .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) },
            title = { Text("toolbar offset is ${toolbarOffsetHeightPx.value}") }
        )
    }
```

#### 说明

#### 效果

![](https://files.mdnice.com/user/15648/486769b3-bd35-4601-aaf0-9d575d6623a0.gif)
