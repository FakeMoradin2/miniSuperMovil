package com.example.project_miniMart.ui.flows.authFlow.screens.onboarding.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.R
import kotlinx.coroutines.delay


@Composable
fun PagerAutoScroll(modifier: Modifier = Modifier) {
    val items = remember {
        listOf(
            R.drawable.ic_tab_one,
            R.drawable.ic_tab_two,
            R.drawable.ic_tab_three,
            R.drawable.ic_tab_four
        )
    }

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { items.size })

    LaunchedEffect(pagerState) {
        while (true) {
            delay(2500L)
            val next = (pagerState.currentPage + 1) % items.size
            pagerState.animateScrollToPage(next)
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            pageSpacing = 16.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { page ->
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.extraLarge),
                painter = painterResource(id = items[page]),
                contentDescription = "page $page",
                contentScale = ContentScale.Crop
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(items.size) { index ->
                val isSelected = pagerState.currentPage == index
                val dotColor =
                    if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray
                val dotSize = if (isSelected) 10.dp else 8.dp

                Surface(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(dotSize)
                        .clip(CircleShape),
                    color = dotColor
                ) {}
            }
        }
    }
}