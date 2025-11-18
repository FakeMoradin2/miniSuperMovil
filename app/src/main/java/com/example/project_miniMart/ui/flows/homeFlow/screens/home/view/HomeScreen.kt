package com.example.project_miniMart.ui.flows.homeFlow.screens.home.view

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.snapshotFlow
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
import com.example.minimartapp.ui.widgets.TypeAlert
import com.example.project_miniMart.R
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.view.composables.ProductItemComposeView
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.viewmodel.HomeUiState
import com.example.project_miniMart.ui.flows.homeFlow.screens.home.viewmodel.HomeViewModel
import com.example.project_miniMart.ui.theme.AccentDark
import com.example.project_miniMart.ui.theme.AccentLight
import com.example.project_miniMart.ui.theme.Typography
import com.example.project_miniMart.widgets.heder.NavigationHeaderComposeView
import com.example.project_miniMart.widgets.listCategories.ChipCategoryItem
import com.example.project_miniMart.widgets.listCategories.ListCategories
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Pantalla principal que muestra los productos y categorías.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.isScrollInProgress }
            .distinctUntilChanged()
            .collect { homeViewModel.onGridScroll(it) }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderHome(uiState.userName)

            AnimatedVisibility(visible = !uiState.isScrolling) {
                CategoryGroup(
                    categories = uiState.categories,
                    onCategorySelected = homeViewModel::selectCategory
                )
            }

            LazyVerticalGrid(
                state = gridState,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 8.dp),
                columns = GridCells.Fixed(3)
            ) {
                items(uiState.filteredProducts) { product ->
                    ProductItemComposeView(product)
                }
            }
        }

        SmallFloatingActionButton(
            onClick = { homeViewModel.toggleBottomSheet(true) },
            containerColor = AccentLight,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Menu, contentDescription = stringResource(R.string.menu_button_desc))
        }

        if (uiState.showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { homeViewModel.toggleBottomSheet(false) },
                sheetState = sheetState,
                dragHandle = null
            ) {
                BottomSheetContent()
            }
        }

        if (uiState.isLoading) {
            CircularProgressIndicator()
        }

        uiState.errorMessage?.let {
            InfoDialog(
                onDismiss = homeViewModel::dismissError,
                title = stringResource(R.string.home_error_title),
                message = stringResource(it),
                typeAlert = TypeAlert.ERROR
            )
        }
    }
}

/**
 * Contenido del menú inferior (BottomSheet).
 */
@Composable
private fun BottomSheetContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(R.string.home_menu_title), style = Typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))
        LabelButtonSheet(Icons.Default.AccountCircle, stringResource(R.string.home_my_account)) {}
        Spacer(modifier = Modifier.height(16.dp))
        LabelButtonSheet(Icons.Default.Refresh, stringResource(R.string.home_order_history)) {}
        Spacer(modifier = Modifier.height(16.dp))
        LabelButtonSheet(Icons.Default.Settings, stringResource(R.string.home_settings)) {}
        Spacer(modifier = Modifier.weight(1f))
        LabelButtonSheet(Icons.Default.Info, stringResource(R.string.home_help)) {}
    }
}

/**
 * Grupo de categorías que se muestra en la parte superior.
 */
@Composable
private fun CategoryGroup(
    categories: List<ChipCategoryItem>,
    onCategorySelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(R.string.home_categories_title),
            style = Typography.titleMedium,
            color = AccentDark
        )
        Spacer(modifier = Modifier.height(8.dp))
        ListCategories(modifier = Modifier, listCategories = categories, onCategorySelected = onCategorySelected)
        Spacer(modifier = Modifier.height(10.dp))
        DottedLineComposeView(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
    }
}

/**
 * Cabecera de la pantalla de inicio.
 */
@Composable
private fun HeaderHome(userName: String) {
    ConstraintLayout(Modifier.fillMaxWidth()) {
        val (header, search, shopping) = createRefs()

        NavigationHeaderComposeView(
            titleView = stringResource(R.string.home_welcome, userName),
            descriptionView = stringResource(R.string.home_explore_message),
            modifier = Modifier.constrainAs(header) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
        )

        IconButton(onClick = {}, modifier = Modifier.constrainAs(shopping) {
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
