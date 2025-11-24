package com.example.project_miniMart.widgets.newswipeable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.project_miniMart.R
import com.example.project_miniMart.ui.theme.AccentDark
import com.example.project_miniMart.ui.theme.AccentLight
import com.example.project_miniMart.ui.theme.DimensSp.Sp16
import com.example.project_miniMart.ui.theme.robotoFontFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun SwipeButtonComposeView(
    modifier: Modifier,
    isSwipeEnabled: Boolean,
    isResetRequired: Boolean = false,
    isDataComplete: Boolean = true,
    onComplete: () -> Unit,
    setReset:()-> Unit
) {
    var containerWidth by rememberSaveable {
        mutableStateOf(0f)
    }
    val buttonWidth = 80.dp
    val swipeState = rememberSwipeableState(0)
    val buttonWidthPx = with(LocalDensity.current) { buttonWidth.toPx() }
    val coroutineScope = rememberCoroutineScope()
    val swipeAnchors = if (containerWidth > 0f) {
        mapOf(
            0f to 0,
            (containerWidth - buttonWidthPx).coerceAtLeast(0f) to 1
        )
    } else {
        emptyMap()
    }




    LaunchedEffect(isResetRequired) {
        coroutineScope.launch {
            swipeState.animateTo(0) // Reset
            setReset()
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .onSizeChanged { containerWidth = it.width.toFloat() }
            .then(
                if (swipeAnchors.isNotEmpty() && isSwipeEnabled && isDataComplete) {
                    Modifier.swipeable(
                        state = swipeState,
                        anchors = swipeAnchors,
                        thresholds = { _, _ -> FractionalThreshold(0.8f) },
                        orientation = Orientation.Horizontal
                    )
                } else {
                    Modifier
                }
            )
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = 2.dp,
                color = sliderColor(swipeAnchors.isNotEmpty() && isSwipeEnabled  && isDataComplete),
                shape = MaterialTheme.shapes.medium
            )
            .background(Color.Transparent)
    ) {
        val currentWidthPx = remember(swipeState.offset.value, containerWidth) {
            val maxOffset =
                (containerWidth - buttonWidthPx).coerceAtLeast(0f)
            val offset = swipeState.offset.value.coerceIn(0f, maxOffset)
            buttonWidthPx + offset
        }

        val maxOffset =
            (containerWidth - buttonWidthPx).coerceAtLeast(1f)
        val progress = (swipeState.offset.value / maxOffset).coerceIn(0f, 1f)


        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (box, text) = createRefs()

            Text(
                "Swipe to confirm",
                style = TextStyle(
                    fontSize = Sp16,
                    fontWeight = FontWeight.Normal,
                    fontFamily = robotoFontFamily
                ),
                color = sliderColor(swipeAnchors.isNotEmpty() && isSwipeEnabled && isDataComplete).copy(alpha = 1f - progress),
                modifier = Modifier.constrainAs(text) {
                    start.linkTo(parent.start, margin = 50.dp)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })

            Box(
                Modifier
                    .size(
                        width = with(LocalDensity.current) { currentWidthPx.toDp() },
                        height = buttonWidth
                    )
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(sliderColor(swipeAnchors.isNotEmpty() && isSwipeEnabled  && isDataComplete))
                    .constrainAs(box) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                contentAlignment = Alignment.CenterEnd
            ) {
                ArrowIcon()
            }
        }
    }

    LaunchedEffect(swipeState.currentValue) {
        if (swipeState.currentValue == 1) {
            onComplete.invoke()
        }
    }
}

@Composable
private fun sliderColor(isEnabled: Boolean): Color {
    return if (isEnabled) {
        AccentLight
    } else {
        Color(0XFF8C8C8C)
    }
}

@Composable
private fun ArrowIcon() {
    Row(modifier = Modifier.width(80.dp), horizontalArrangement = Arrangement.Center) {
        Icon(
            painter = painterResource(R.drawable.ds_icon_arrow_forward),
            contentDescription = "",
            tint = Color.White
        )
    }
}
