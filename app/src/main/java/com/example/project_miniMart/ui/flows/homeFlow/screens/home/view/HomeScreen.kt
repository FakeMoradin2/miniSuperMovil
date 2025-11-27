package com.example.project_miniMart.ui.flows.homeFlow.screens.home.view

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.minimartapp.ui.widgets.DottedLineComposeView
import com.example.minimartapp.ui.widgets.InfoDialog
import com.example.minimartapp.ui.widgets.LabelButtonSheet
import com.example.project_miniMart.R
import com.example.project_miniMart.ui.flows.homeFlow.navigation.DestinationHistory
import com.example.project_miniMart.ui.flows.homeFlow.navigation.DestinationShoppingCar
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.HomeViewModel
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.intent.HomeIntents
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.model.HomeStates
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.view.composables.ProductItemComposeView
import com.example.project_miniMart.ui.theme.AccentDark
import com.example.project_miniMart.ui.theme.AccentLight
import com.example.project_miniMart.ui.theme.Styles.roboto16Medium
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoMediumSp16
import com.example.project_miniMart.utils.uiManager.HomeEventManager
import com.example.project_miniMart.utils.uiManager.events.HomeEvent
import com.example.project_miniMart.widgets.heder.NavigationHeaderComposeView
import com.example.project_miniMart.widgets.listCategories.ListCategories


@SuppressLint("FrequentlyChangingValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()
    homeViewModel.channel.trySend(HomeIntents.GetUserInfo)

    val sheetState = rememberModalBottomSheetState()
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState.isScrollInProgress, gridState.firstVisibleItemScrollOffset) {
        if (gridState.isScrollInProgress) {
            val currentOffset = gridState.firstVisibleItemScrollOffset
            val isScrollingDown = currentOffset > 0

            homeViewModel.channel.send(
                HomeIntents.UpdateGridScrolling(isScrollingDown)
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderHome(state)

           AnimatedContent(
                targetState = !state.isScrolling,
                label = "category-animation"
            ) { shouldShow ->
                if (shouldShow) {
                    CategoryGroup(state, homeViewModel)
                }
            }

            LazyVerticalGrid(
                state = gridState,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 3.dp),
                columns = GridCells.Fixed(3)
            ) {
                items(state.allProducts) {
                    ProductItemComposeView(it){
                        homeViewModel.channel.trySend(HomeIntents.AddProductShoppingCar(it))
                    }
                }
            }
        }

        SmallFloatingActionButton(
            onClick = {
                homeViewModel.channel.trySend(HomeIntents.ShowAndHideButtonSheet(true))
            },
            containerColor = AccentLight,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Menu, contentDescription = null)
        }

        if (state.showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    homeViewModel.channel.trySend(HomeIntents.ShowAndHideButtonSheet(false))
                },
                sheetState = sheetState,
                dragHandle = null,
            ) {
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.4f)
                            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                            .background(Color.White)
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Menu", style = textStyleRobotoMediumSp16)
                        Spacer(modifier = Modifier.height(24.dp))
                        LabelButtonSheet(Icons.Default.AccountCircle, "My account") {

                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        LabelButtonSheet(Icons.Default.Refresh, "Order history") {
                            homeViewModel.channel.trySend(HomeIntents.HideButtonSheet)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        LabelButtonSheet(Icons.Default.Settings, "Settings") {}
                        Spacer(modifier = Modifier.weight(1f))
                        LabelButtonSheet(Icons.Default.Info, "Help") {}
                    }
                }
            }
        }
    }

    if (state.showAlert) InfoDialog(
        onDismiss = {
            homeViewModel.channel.trySend(HomeIntents.HideAlert)
        },
        "Oops! Nothing Here Yet",
        stringResource(state.errorMassageAlert),
        typeAlert = state.typeAlert!!
    )
}


@Composable
fun CategoryGroup(state: HomeStates, homeViewModel: HomeViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text("Categories", style = roboto16Medium, color = AccentDark)
        Spacer(modifier = Modifier.height(10.dp))
        ListCategories(modifier = Modifier, listCategories = state.allCategories) {
            homeViewModel.channel.trySend(HomeIntents.CheckCategory(it))
        }
        Spacer(modifier = Modifier.height(16.dp))
        DottedLineComposeView(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun HeaderHome(state: HomeStates) {
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val (header, search, shopping) = createRefs()

        NavigationHeaderComposeView(
            titleView = "Welcome ${state.userName}",
            descriptionView = "Explore, enjoy, and make the most of the app.",
            modifier = Modifier.constrainAs(header) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )

        IconButton(onClick = {
            HomeEventManager.triggerEvent(HomeEvent.NavigateTo(DestinationShoppingCar))
        }, modifier = Modifier.constrainAs(shopping) {
            end.linkTo(header.end, margin = 4.dp)
            top.linkTo(header.top, margin = 8.dp)
        }) {
            Icon(painter = painterResource(R.drawable.ic_shopping), "", tint = Color.White)
        }

        IconButton(onClick = {}, modifier = Modifier.constrainAs(search) {
            end.linkTo(shopping.start)
            top.linkTo(shopping.top)
        }) {
            Icon(painter = painterResource(R.drawable.ic_search), "", tint = Color.White)
        }
    }
}