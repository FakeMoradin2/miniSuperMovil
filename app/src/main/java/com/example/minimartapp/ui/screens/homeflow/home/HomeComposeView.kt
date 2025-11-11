package com.example.minimartapp.ui.screens.homeflow.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.minimartapp.R
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoBoldSp24
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoMediumSp35
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoRegularSp12
import com.example.minimartapp.ui.widgets.ButtonCategorieComposeView
import com.example.minimartapp.ui.widgets.ButtonMenuComposeView
import com.example.minimartapp.ui.widgets.ProductCardComposeView
import com.example.minimartapp.ui.widgets.TypesButtons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeComposeView() {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    // Estado para controlar la barra de búsqueda
    var showSearchBar by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    // Lista de productos con información diferente
    var products by remember {
        mutableStateOf(
            listOf(
                Product(1, "Whole Milk", "25", 8, R.drawable.ic_logo, "Dairy"),
                Product(2, "Chocolate Cake", "50", 5, R.drawable.ic_logo, "Bakery"),
                Product(3, "Orange Juice", "30", 10, R.drawable.ic_logo, "Drinks"),
                Product(4, "Greek Yogurt", "20", 3, R.drawable.ic_logo, "Dairy"),
                Product(5, "White Bread", "15", 12, R.drawable.ic_logo, "Bakery"),
                Product(6, "Coca Cola", "18", 15, R.drawable.ic_logo, "Drinks"),
                Product(7, "Cheese", "35", 6, R.drawable.ic_logo, "Dairy"),
                Product(8, "Croissant", "12", 7, R.drawable.ic_logo, "Bakery"),
                Product(9, "Apple Juice", "22", 9, R.drawable.ic_logo, "Drinks"),
                Product(10, "Butter", "28", 4, R.drawable.ic_logo, "Dairy"),
                Product(11, "Cream Cheese", "19", 6, R.drawable.ic_logo, "Dairy"),
                Product(12, "Mozzarella", "31", 11, R.drawable.ic_logo, "Dairy"),
                Product(13, "Cheddar Cheese", "33", 7, R.drawable.ic_logo, "Dairy"),
                Product(14, "Parmesan", "45", 4, R.drawable.ic_logo, "Dairy"),
                Product(15, "Provolone", "38", 5, R.drawable.ic_logo, "Dairy"),
                Product(16, "Ricotta", "27", 8, R.drawable.ic_logo, "Dairy"),
                Product(17, "Feta Cheese", "29", 6, R.drawable.ic_logo, "Dairy"),
                Product(18, "Gouda", "42", 3, R.drawable.ic_logo, "Dairy"),
                Product(19, "Brie", "48", 2, R.drawable.ic_logo, "Dairy"),
                Product(20, "Blue Cheese", "52", 4, R.drawable.ic_logo, "Dairy"),
                Product(21, "Whipped Cream", "16", 9, R.drawable.ic_logo, "Dairy"),
                Product(22, "Condensed Milk", "21", 7, R.drawable.ic_logo, "Dairy"),
                Product(23, "Evaporated Milk", "23", 5, R.drawable.ic_logo, "Dairy"),
                Product(24, "Goat Cheese", "37", 6, R.drawable.ic_logo, "Dairy"),
                Product(25, "String Cheese", "15", 14, R.drawable.ic_logo, "Dairy"),
                Product(26, "Mascarpone", "44", 3, R.drawable.ic_logo, "Dairy"),
                Product(27, "Monterey Jack", "36", 8, R.drawable.ic_logo, "Dairy"),
                Product(28, "Swiss Cheese", "39", 5, R.drawable.ic_logo, "Dairy"),
                Product(29, "Havarti", "41", 4, R.drawable.ic_logo, "Dairy"),
                Product(30, "Colby Cheese", "34", 7, R.drawable.ic_logo, "Dairy"),
                Product(31, "Chocolate Cake", "50", 5, R.drawable.ic_logo, "Bakery"),
                Product(32, "White Bread", "15", 12, R.drawable.ic_logo, "Bakery"),
                Product(33, "Croissant", "12", 7, R.drawable.ic_logo, "Bakery"),
                Product(34, "Bagels", "18", 9, R.drawable.ic_logo, "Bakery"),
                Product(35, "Donuts", "10", 15, R.drawable.ic_logo, "Bakery"),
                Product(36, "Muffins", "14", 11, R.drawable.ic_logo, "Bakery"),
                Product(37, "Cookies", "8", 20, R.drawable.ic_logo, "Bakery"),
                Product(38, "Brownies", "16", 8, R.drawable.ic_logo, "Bakery"),
                Product(39, "Cupcakes", "22", 6, R.drawable.ic_logo, "Bakery"),
                Product(40, "French Baguette", "13", 10, R.drawable.ic_logo, "Bakery"),
                Product(41, "Cinnamon Roll", "17", 8, R.drawable.ic_logo, "Bakery"),
                Product(42, "Pita Bread", "11", 12, R.drawable.ic_logo, "Bakery"),
                Product(43, "Sourdough", "19", 7, R.drawable.ic_logo, "Bakery"),
                Product(44, "Banana Bread", "21", 5, R.drawable.ic_logo, "Bakery"),
                Product(45, "Pumpkin Pie", "45", 3, R.drawable.ic_logo, "Bakery"),
                Product(46, "Apple Pie", "42", 4, R.drawable.ic_logo, "Bakery"),
                Product(47, "Cheesecake", "55", 2, R.drawable.ic_logo, "Bakery"),
                Product(48, "Danish Pastry", "19", 6, R.drawable.ic_logo, "Bakery"),
                Product(49, "Eclair", "24", 4, R.drawable.ic_logo, "Bakery"),
                Product(50, "Pretzels", "9", 18, R.drawable.ic_logo, "Bakery"),
                Product(51, "Biscotti", "12", 10, R.drawable.ic_logo, "Bakery"),
                Product(52, "Scones", "16", 7, R.drawable.ic_logo, "Bakery"),
                Product(53, "Cornbread", "14", 9, R.drawable.ic_logo, "Bakery"),
                Product(54, "Garlic Bread", "18", 8, R.drawable.ic_logo, "Bakery"),
                Product(55, "Pound Cake", "28", 5, R.drawable.ic_logo, "Bakery"),
                Product(56, "Angel Food Cake", "32", 4, R.drawable.ic_logo, "Bakery"),
                Product(57, "Red Velvet Cake", "48", 3, R.drawable.ic_logo, "Bakery"),
                Product(58, "Carrot Cake", "38", 4, R.drawable.ic_logo, "Bakery"),
                Product(59, "Tiramisu", "52", 2, R.drawable.ic_logo, "Bakery"),
                Product(60, "Macarons", "25", 6, R.drawable.ic_logo, "Bakery"),
                Product(61, "Orange Juice", "30", 10, R.drawable.ic_logo, "Drinks"),
                Product(62, "Coca Cola", "18", 15, R.drawable.ic_logo, "Drinks"),
                Product(63, "Apple Juice", "22", 9, R.drawable.ic_logo, "Drinks"),
                Product(64, "Pepsi", "17", 14, R.drawable.ic_logo, "Drinks"),
                Product(65, "Lemonade", "20", 8, R.drawable.ic_logo, "Drinks"),
                Product(66, "Iced Tea", "16", 12, R.drawable.ic_logo, "Drinks"),
                Product(67, "Coffee", "25", 20, R.drawable.ic_logo, "Drinks"),
                Product(68, "Green Tea", "19", 15, R.drawable.ic_logo, "Drinks"),
                Product(69, "Sparkling Water", "12", 18, R.drawable.ic_logo, "Drinks"),
                Product(70, "Grape Juice", "24", 7, R.drawable.ic_logo, "Drinks"),
                Product(71, "Pineapple Juice", "26", 6, R.drawable.ic_logo, "Drinks"),
                Product(72, "Cranberry Juice", "28", 5, R.drawable.ic_logo, "Drinks"),
                Product(73, "Tomato Juice", "21", 8, R.drawable.ic_logo, "Drinks"),
                Product(74, "Energy Drink", "32", 10, R.drawable.ic_logo, "Drinks"),
                Product(75, "Sports Drink", "23", 9, R.drawable.ic_logo, "Drinks"),
                Product(76, "Coconut Water", "27", 7, R.drawable.ic_logo, "Drinks"),
                Product(77, "Aloe Vera Drink", "29", 6, R.drawable.ic_logo, "Drinks"),
                Product(78, "Soy Milk Drink", "22", 8, R.drawable.ic_logo, "Drinks"),
                Product(79, "Hot Chocolate", "26", 11, R.drawable.ic_logo, "Drinks"),
                Product(80, "Chai Latte", "31", 5, R.drawable.ic_logo, "Drinks"),
                Product(81, "Matcha Latte", "34", 4, R.drawable.ic_logo, "Drinks"),
                Product(82, "Bubble Tea", "38", 3, R.drawable.ic_logo, "Drinks"),
                Product(83, "Smoothie", "33", 6, R.drawable.ic_logo, "Drinks"),
                Product(84, "Milkshake", "35", 5, R.drawable.ic_logo, "Drinks"),
                Product(85, "Mineral Water", "8", 25, R.drawable.ic_logo, "Drinks"),
                Product(86, "Club Soda", "10", 20, R.drawable.ic_logo, "Drinks"),
                Product(87, "Tonic Water", "14", 12, R.drawable.ic_logo, "Drinks"),
                Product(88, "Ginger Ale", "16", 15, R.drawable.ic_logo, "Drinks"),
                Product(89, "Root Beer", "19", 10, R.drawable.ic_logo, "Drinks"),
                Product(90, "Dr Pepper", "18", 13, R.drawable.ic_logo, "Drinks"),
                Product(91, "Apples", "12", 25, R.drawable.ic_logo, "Produce"),
                Product(92, "Bananas", "8", 30, R.drawable.ic_logo, "Produce"),
                Product(93, "Oranges", "15", 20, R.drawable.ic_logo, "Produce"),
                Product(94, "Strawberries", "28", 12, R.drawable.ic_logo, "Produce"),
                Product(95, "Grapes", "22", 18, R.drawable.ic_logo, "Produce"),
                Product(96, "Tomatoes", "10", 22, R.drawable.ic_logo, "Produce"),
                Product(97, "Potatoes", "9", 28, R.drawable.ic_logo, "Produce"),
                Product(98, "Carrots", "7", 32, R.drawable.ic_logo, "Produce"),
                Product(99, "Lettuce", "11", 15, R.drawable.ic_logo, "Produce"),
                Product(100, "Onions", "6", 35, R.drawable.ic_logo, "Produce"),
                Product(101, "Bell Peppers", "14", 16, R.drawable.ic_logo, "Produce"),
                Product(102, "Cucumbers", "9", 20, R.drawable.ic_logo, "Produce"),
                Product(103, "Avocados", "25", 10, R.drawable.ic_logo, "Produce"),
                Product(104, "Lemons", "12", 18, R.drawable.ic_logo, "Produce"),
                Product(105, "Limes", "10", 20, R.drawable.ic_logo, "Produce"),
                Product(106, "Broccoli", "13", 14, R.drawable.ic_logo, "Produce"),
                Product(107, "Cauliflower", "15", 12, R.drawable.ic_logo, "Produce"),
                Product(108, "Spinach", "11", 16, R.drawable.ic_logo, "Produce"),
                Product(109, "Mushrooms", "18", 13, R.drawable.ic_logo, "Produce"),
                Product(110, "Celery", "8", 22, R.drawable.ic_logo, "Produce"),
                Product(111, "Garlic", "5", 40, R.drawable.ic_logo, "Produce"),
                Product(112, "Ginger", "7", 25, R.drawable.ic_logo, "Produce"),
                Product(113, "Blueberries", "32", 8, R.drawable.ic_logo, "Produce"),
                Product(114, "Raspberries", "35", 6, R.drawable.ic_logo, "Produce"),
                Product(115, "Pineapple", "45", 5, R.drawable.ic_logo, "Produce"),
                Product(116, "Watermelon", "38", 4, R.drawable.ic_logo, "Produce"),
                Product(117, "Cantaloupe", "28", 6, R.drawable.ic_logo, "Produce"),
                Product(118, "Honeydew", "26", 7, R.drawable.ic_logo, "Produce"),
                Product(119, "Kiwi", "20", 12, R.drawable.ic_logo, "Produce"),
                Product(120, "Mango", "30", 9, R.drawable.ic_logo, "Produce"),
                Product(121, "Chicken Breast", "45", 8, R.drawable.ic_logo, "Meat"),
                Product(122, "Ground Beef", "38", 10, R.drawable.ic_logo, "Meat"),
                Product(123, "Pork Chops", "42", 7, R.drawable.ic_logo, "Meat"),
                Product(124, "Salmon Fillet", "65", 5, R.drawable.ic_logo, "Meat"),
                Product(125, "Bacon", "28", 12, R.drawable.ic_logo, "Meat"),
                Product(126, "Sausages", "22", 15, R.drawable.ic_logo, "Meat"),
                Product(127, "Turkey", "55", 4, R.drawable.ic_logo, "Meat"),
                Product(128, "Tuna Steak", "48", 6, R.drawable.ic_logo, "Meat"),
                Product(129, "Shrimp", "72", 3, R.drawable.ic_logo, "Meat"),
                Product(130, "Lamb Chops", "68", 4, R.drawable.ic_logo, "Meat"),
                Product(131, "Duck Breast", "58", 3, R.drawable.ic_logo, "Meat"),
                Product(132, "Veal Cutlet", "62", 5, R.drawable.ic_logo, "Meat"),
                Product(133, "Cod Fillet", "42", 8, R.drawable.ic_logo, "Meat"),
                Product(134, "Scallops", "75", 2, R.drawable.ic_logo, "Meat"),
                Product(135, "Crab Legs", "85", 2, R.drawable.ic_logo, "Meat"),
                Product(136, "Potato Chips", "15", 20, R.drawable.ic_logo, "Snacks"),
                Product(137, "Chocolate Bar", "12", 25, R.drawable.ic_logo, "Snacks"),
                Product(138, "Popcorn", "8", 30, R.drawable.ic_logo, "Snacks"),
                Product(139, "Trail Mix", "18", 15, R.drawable.ic_logo, "Snacks"),
                Product(140, "Granola Bars", "14", 18, R.drawable.ic_logo, "Snacks"),
                Product(141, "Crackers", "10", 22, R.drawable.ic_logo, "Snacks"),
                Product(142, "Pretzels", "9", 25, R.drawable.ic_logo, "Snacks"),
                Product(143, "Nuts Mix", "25", 12, R.drawable.ic_logo, "Snacks"),
                Product(144, "Beef Jerky", "32", 8, R.drawable.ic_logo, "Snacks"),
                Product(145, "Fruit Snacks", "11", 20, R.drawable.ic_logo, "Snacks"),
                Product(146, "Rice Cakes", "7", 28, R.drawable.ic_logo, "Snacks"),
                Product(147, "Protein Bars", "22", 14, R.drawable.ic_logo, "Snacks"),
                Product(148, "Cheese Puffs", "13", 16, R.drawable.ic_logo, "Snacks"),
                Product(149, "Tortilla Chips", "16", 18, R.drawable.ic_logo, "Snacks"),
                Product(150, "Salsa Dip", "14", 15, R.drawable.ic_logo, "Snacks")
            )
        )
    }

    var selectedCategory by remember { mutableStateOf("All") }

    // Filtrar productos por categoría y búsqueda
    val filteredProducts = remember(products, selectedCategory, searchText) {
        if (selectedCategory == "All") {
            if (searchText.isBlank()) {
                products
            } else {
                products.filter { product ->
                    product.name.contains(searchText, ignoreCase = true)
                }
            }
        } else {
            if (searchText.isBlank()) {
                products.filter { it.category == selectedCategory }
            } else {
                products.filter { product ->
                    product.category == selectedCategory &&
                            product.name.contains(searchText, ignoreCase = true)
                }
            }
        }
    }

    // Función para disminuir el stock
    fun decreaseStock(productId: Int) {
        products = products.map { product ->
            if (product.id == productId && product.stock > 0) {
                product.copy(stock = product.stock - 1)
            } else {
                product
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.Menu, contentDescription = "Menu") },
                text = { Text("") },
                onClick = { showBottomSheet = true }
            )
        },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                expandedHeight = 100.dp,
                actions = {
                    Row {
                        Icon(
                            modifier = Modifier.offset(y = (-40).dp),
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "Search",
                            tint = Color.White,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            modifier = Modifier.offset(y = (-40).dp),
                            painter = painterResource(R.drawable.ic_car),
                            contentDescription = "Cart",
                            tint = Color.White,
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                },
                title = {
                    Column {
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            "Welcome!",
                            style = textStyleRobotoMediumSp35
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            "Explore, enjoy, and make the most of the app",
                            style = textStyleRobotoRegularSp12
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF64B5F6),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Categories",
                style = textStyleRobotoBoldSp24,
                color = Color(0xFF64B5F6),
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Divider(
                color = Color.LightGray,
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.height(5.dp))

            // Botones de categorías con estado
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.width(6.dp))
                ButtonCategorieComposeView(
                    typesButtons = if (selectedCategory == "All") TypesButtons.Primary else TypesButtons.Secondary,
                    title = "All",
                ) {
                    selectedCategory = "All"
                }

                Spacer(modifier = Modifier.width(6.dp))
                ButtonCategorieComposeView(
                    typesButtons = if (selectedCategory == "Dairy") TypesButtons.Primary else TypesButtons.Secondary,
                    title = "Dairy"
                ) {
                    selectedCategory = "Dairy"
                }

                Spacer(modifier = Modifier.width(6.dp))
                ButtonCategorieComposeView(
                    typesButtons = if (selectedCategory == "Bakery") TypesButtons.Primary else TypesButtons.Secondary,
                    title = "Bakery",
                ) {
                    selectedCategory = "Bakery"
                }

                Spacer(modifier = Modifier.width(6.dp))
                ButtonCategorieComposeView(
                    typesButtons = if (selectedCategory == "Drinks") TypesButtons.Primary else TypesButtons.Secondary,
                    title = "Drinks",
                ) {
                    selectedCategory = "Drinks"
                }

                Spacer(modifier = Modifier.width(6.dp))
                ButtonCategorieComposeView(
                    typesButtons = if (selectedCategory == "Produce") TypesButtons.Primary else TypesButtons.Secondary,
                    title = "Produce",
                ) {
                    selectedCategory = "Produce"
                }

                Spacer(modifier = Modifier.width(6.dp))
                ButtonCategorieComposeView(
                    typesButtons = if (selectedCategory == "Meat") TypesButtons.Primary else TypesButtons.Secondary,
                    title = "Meat",
                ) {
                    selectedCategory = "Meat"
                }

                Spacer(modifier = Modifier.width(6.dp))
                ButtonCategorieComposeView(
                    typesButtons = if (selectedCategory == "Snacks") TypesButtons.Primary else TypesButtons.Secondary,
                    title = "Snacks",
                ) {
                    selectedCategory = "Snacks"
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Grid de productos con información única para cada uno
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                items(filteredProducts, key = { it.id }) { product ->
                    ProductCardComposeView(
                        productName = product.name,
                        price = product.price,
                        stock = product.stock,
                        icon = product.imageRes
                    ) {

                    }
                }
            }
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
            containerColor = Color(0xFFF2F2F7)
        ) {
            Text(
                text = "Menu",
                style = textStyleRobotoBoldSp24,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sheet content
            ButtonMenuComposeView(
                typesButtons = TypesButtons.Terseary,
                title = "My account",
                icon = R.drawable.ic_account
            ) {
            }
            Spacer(modifier = Modifier.height(10.dp))

            ButtonMenuComposeView(
                typesButtons = TypesButtons.Terseary,
                title = "Order History",
                icon = R.drawable.ic_history
            ) {
            }
            Spacer(modifier = Modifier.height(10.dp))

            ButtonMenuComposeView(
                typesButtons = TypesButtons.Terseary,
                title = "Setting",
                icon = R.drawable.ic_settings
            ) {
            }
            Spacer(modifier = Modifier.height(30.dp))

            ButtonMenuComposeView(
                typesButtons = TypesButtons.Terseary,
                title = "Help",
                icon = R.drawable.ic_help
            ) {

            }
        }
    }
}