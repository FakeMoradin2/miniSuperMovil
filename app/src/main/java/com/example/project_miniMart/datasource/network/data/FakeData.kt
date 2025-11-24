package com.example.project_miniMart.datasource.network.data

import com.example.project_miniMart.R
import com.example.project_miniMart.datasource.network.requests.LoginRequest
import com.example.project_miniMart.datasource.network.requests.RegisterRequest
import com.example.project_miniMart.datasource.network.responses.ProductsResponse
import com.example.project_miniMart.datasource.network.responses.LoginResponse
import com.example.project_miniMart.datasource.network.responses.ProductApi
import com.example.project_miniMart.datasource.network.responses.RegisterResponse
import com.example.project_miniMart.datasource.network.responses.UserDataResponse
import kotlin.random.Random

private var products =
    listOf(
        ProductApi(1, "Whole Milk", 25.0, 8, "Dairy"),
        ProductApi(2, "Chocolate Cake", 50.0, 5, "Bakery"),
        ProductApi(3, "Orange Juice", 30.0, 10, "Drinks"),
        ProductApi(4, "Greek Yogurt", 20.0, 3, "Dairy"),
        ProductApi(5, "White Bread", 15.0, 12, "Bakery"),
        ProductApi(6, "Coca Cola", 18.0, 15, "Drinks"),
        ProductApi(7, "Cheese", 35.0, 6, "Dairy"),
        ProductApi(8, "Croissant", 12.0, 7, "Bakery"),
        ProductApi(9, "Apple Juice", 22.0, 9, "Drinks"),
        ProductApi(10, "Butter", 28.0, 4, "Dairy"),
        ProductApi(11, "Cream Cheese", 19.0, 6, "Dairy"),
        ProductApi(12, "Mozzarella", 31.0, 11, "Dairy"),
        ProductApi(13, "Cheddar Cheese", 33.0, 7, "Dairy"),
        ProductApi(14, "Parmesan", 45.0, 4, "Dairy"),
        ProductApi(15, "Provolone", 38.0, 5, "Dairy"),
        ProductApi(16, "Ricotta", 27.0, 8, "Dairy"),
        ProductApi(17, "Feta Cheese", 29.0, 6, "Dairy"),
        ProductApi(18, "Gouda", 42.0, 3, "Dairy"),
        ProductApi(19, "Brie", 48.0, 2, "Dairy"),
        ProductApi(20, "Blue Cheese", 52.0, 4, "Dairy"),
        ProductApi(21, "Whipped Cream", 16.0, 9, "Dairy"),
        ProductApi(22, "Condensed Milk", 21.0, 7, "Dairy"),
        ProductApi(23, "Evaporated Milk", 23.0, 5, "Dairy"),
        ProductApi(24, "Goat Cheese", 37.0, 6, "Dairy"),
        ProductApi(25, "Cheese", 15.0, 14, "Dairy"),
        ProductApi(26, "Mascarpone", 44.0, 3, "Dairy"),
        ProductApi(27, "Monterey Jack", 36.0, 8, "Dairy"),
        ProductApi(28, "Swiss Cheese", 39.0, 5, "Dairy"),
        ProductApi(29, "Havarti", 41.0, 4, "Dairy"),
        ProductApi(30, "Colby Cheese", 34.0, 7, "Dairy"),
        ProductApi(31, "Chocolate Cake", 50.0, 5, "Bakery"),
        ProductApi(32, "White Bread", 15.0, 12, "Bakery"),
        ProductApi(33, "Croissant", 12.0, 7, "Bakery"),
        ProductApi(34, "Bagels", 18.0, 9, "Bakery"),
        ProductApi(35, "Donuts", 10.0, 15, "Bakery"),
        ProductApi(36, "Muffins", 14.0, 11, "Bakery"),
        ProductApi(37, "Cookies", 8.0, 20, "Bakery"),
        ProductApi(38, "Brownies", 16.0, 8, "Bakery"),
        ProductApi(39, "Cupcakes", 22.0, 6, "Bakery"),
        ProductApi(40, "French Baguette", 13.0, 10, "Bakery"),
        ProductApi(41, "Cinnamon Roll", 17.0, 8, "Bakery"),
        ProductApi(42, "Pita Bread", 11.0, 12, "Bakery"),
        ProductApi(43, "Sourdough", 19.0, 7, "Bakery"),
        ProductApi(44, "Banana Bread", 21.0, 5, "Bakery"),
        ProductApi(45, "Pumpkin Pie", 45.0, 3, "Bakery"),
        ProductApi(46, "Apple Pie", 42.0, 4, "Bakery"),
        ProductApi(47, "Cheesecake", 55.0, 2, "Bakery"),
        ProductApi(48, "Danish Pastry", 19.0, 6, "Bakery"),
        ProductApi(49, "Eclair", 24.0, 4, "Bakery"),
        ProductApi(50, "Pretzels", 9.0, 18, "Bakery"),
        ProductApi(51, "Biscotti", 12.0, 10, "Bakery"),
        ProductApi(52, "Scones", 16.0, 7, "Bakery"),
        ProductApi(53, "Cornbread", 14.0, 9, "Bakery"),
        ProductApi(54, "Garlic Bread", 18.0, 8, "Bakery"),
        ProductApi(55, "Pound Cake", 28.0, 5, "Bakery"),
        ProductApi(56, "Angel Food Cake", 32.0, 4, "Bakery"),
        ProductApi(57, "Red Velvet Cake", 48.0, 3, "Bakery"),
        ProductApi(58, "Carrot Cake", 38.0, 4, "Bakery"),
        ProductApi(59, "Tiramisu", 52.0, 2, "Bakery"),
        ProductApi(60, "Macarons", 25.0, 6, "Bakery"),
        ProductApi(61, "Orange Juice", 30.0, 10, "Drinks"),
        ProductApi(62, "Coca Cola", 18.0, 15, "Drinks"),
        ProductApi(63, "Apple Juice", 22.0, 9, "Drinks"),
        ProductApi(64, "Pepsi", 17.0, 14, "Drinks"),
        ProductApi(65, "Lemonade", 20.0, 8, "Drinks"),
        ProductApi(66, "Iced Tea", 16.0, 12, "Drinks"),
        ProductApi(67, "Coffee", 25.0, 20, "Drinks"),
        ProductApi(68, "Green Tea", 19.0, 15, "Drinks"),
        ProductApi(69, "Sparkling Water", 12.0, 18, "Drinks"),
        ProductApi(70, "Grape Juice", 24.0, 7, "Drinks"),
        ProductApi(71, "Pineapple Juice", 26.0, 6, "Drinks"),
        ProductApi(72, "Cranberry Juice", 28.0, 5, "Drinks"),
        ProductApi(73, "Tomato Juice", 21.0, 8, "Drinks"),
        ProductApi(74, "Energy Drink", 32.0, 10, "Drinks"),
        ProductApi(75, "Sports Drink", 23.0, 9, "Drinks"),
        ProductApi(76, "Coconut Water", 27.0, 7, "Drinks"),
        ProductApi(77, "Aloe Vera Drink", 29.0, 6, "Drinks"),
        ProductApi(78, "Soy Milk Drink", 22.0, 8, "Drinks"),
        ProductApi(79, "Hot Chocolate", 26.0, 11, "Drinks"),
        ProductApi(80, "Chai Latte", 31.0, 5, "Drinks"),
        ProductApi(81, "Matcha Latte", 34.0, 4, "Drinks"),
        ProductApi(82, "Bubble Tea", 38.0, 3, "Drinks"),
        ProductApi(83, "Smoothie", 33.0, 6, "Drinks"),
        ProductApi(84, "Milkshake", 35.0, 5, "Drinks"),
        ProductApi(85, "Mineral Water", 8.0, 25, "Drinks"),
        ProductApi(86, "Club Soda", 10.0, 20, "Drinks"),
        ProductApi(87, "Tonic Water", 14.0, 12, "Drinks"),
        ProductApi(88, "Ginger Ale", 16.0, 15, "Drinks"),
        ProductApi(89, "Root Beer", 19.0, 10, "Drinks"),
        ProductApi(90, "Dr Pepper", 18.0, 13, "Drinks"),
        ProductApi(91, "Apples", 12.0, 25, "Produce"),
        ProductApi(92, "Bananas", 8.0, 30, "Produce"),
        ProductApi(93, "Oranges", 15.0, 20, "Produce"),
        ProductApi(94, "Strawberries", 28.0, 12, "Produce"),
        ProductApi(95, "Grapes", 22.0, 18, "Produce"),
        ProductApi(96, "Tomatoes", 10.0, 22, "Produce"),
        ProductApi(97, "Potatoes", 9.0, 28, "Produce"),
        ProductApi(98, "Carrots", 7.0, 32, "Produce"),
        ProductApi(99, "Lettuce", 11.0, 15, "Produce"),
        ProductApi(100, "Onions", 6.0, 35, "Produce"),
        ProductApi(101, "Bell Peppers", 14.0, 16, "Produce"),
        ProductApi(102, "Cucumbers", 9.0, 20, "Produce"),
        ProductApi(103, "Avocados", 25.0, 10, "Produce"),
        ProductApi(104, "Lemons", 12.0, 18, "Produce"),
        ProductApi(105, "Limes", 10.0, 20, "Produce"),
        ProductApi(106, "Broccoli", 13.0, 14, "Produce"),
        ProductApi(107, "Cauliflower", 15.0, 12, "Produce"),
        ProductApi(108, "Spinach", 11.0, 16, "Produce"),
        ProductApi(109, "Mushrooms", 18.0, 13, "Produce"),
        ProductApi(110, "Celery", 8.0, 22, "Produce"),
        ProductApi(111, "Garlic", 5.0, 40, "Produce"),
        ProductApi(112, "Ginger", 7.0, 25, "Produce"),
        ProductApi(113, "Blueberries", 32.0, 8, "Produce"),
        ProductApi(114, "Raspberries", 35.0, 6, "Produce"),
        ProductApi(115, "Pineapple", 45.0, 5, "Produce"),
        ProductApi(116, "Watermelon", 38.0, 4, "Produce"),
        ProductApi(117, "Cantaloupe", 28.0, 6, "Produce"),
        ProductApi(118, "Honeydew", 26.0, 7, "Produce"),
        ProductApi(119, "Kiwi", 20.0, 12, "Produce"),
        ProductApi(120, "Mango", 30.0, 9, "Produce"),
        ProductApi(121, "Chicken Breast", 45.0, 8, "Meat"),
        ProductApi(122, "Ground Beef", 38.0, 10, "Meat"),
        ProductApi(123, "Pork Chops", 42.0, 7, "Meat"),
        ProductApi(124, "Salmon Fillet", 65.0, 5, "Meat"),
        ProductApi(125, "Bacon", 28.0, 12, "Meat"),
        ProductApi(126, "Sausages", 22.0, 15, "Meat"),
        ProductApi(127, "Turkey", 55.0, 4, "Meat"),
        ProductApi(128, "Tuna Steak", 48.0, 6, "Meat"),
        ProductApi(129, "Shrimp", 72.0, 3, "Meat"),
        ProductApi(130, "Lamb Chops", 68.0, 4, "Meat"),
        ProductApi(131, "Duck Breast", 58.0, 3, "Meat"),
        ProductApi(132, "Veal Cutlet", 62.0, 5, "Meat"),
        ProductApi(133, "Cod Fillet", 42.0, 8, "Meat"),
        ProductApi(134, "Scallops", 75.0, 2, "Meat"),
        ProductApi(135, "Crab Legs", 85.0, 2, "Meat"),
        ProductApi(136, "Potato Chips", 15.0, 20, "Snacks"),
        ProductApi(137, "Chocolate Bar", 12.0, 25, "Snacks"),
        ProductApi(138, "Popcorn", 8.0, 30, "Snacks"),
        ProductApi(139, "Trail Mix", 18.0, 15, "Snacks"),
        ProductApi(140, "Granola Bars", 14.0, 18, "Snacks"),
        ProductApi(141, "Crackers", 10.0, 22, "Snacks"),
        ProductApi(142, "Pretzels", 9.0, 25, "Snacks"),
        ProductApi(143, "Nuts Mix", 25.0, 12, "Snacks"),
        ProductApi(144, "Beef Jerky", 32.0, 8, "Snacks"),
        ProductApi(145, "Fruit Snacks", 11.0, 20, "Snacks"),
        ProductApi(146, "Rice Cakes", 7.0, 28, "Snacks"),
        ProductApi(147, "Protein Bars", 22.0, 14, "Snacks"),
        ProductApi(148, "Cheese Puffs", 13.0, 16, "Snacks"),
        ProductApi(149, "Tortilla Chips", 16.0, 18, "Snacks"),
        ProductApi(150, "Salsa Dip", 14.0, 15, "Snacks")
    )


private val validUsers = listOf(
    Triple(
        "Chong",
        "12345",
        UserDataResponse(
            "Chong Hernandez Octavio",
            "Vendedor",
            "Chong@gmail.com",
            20,
            false,
            "3328025556"
        )
    ),
    Triple(
        "Jose",
        "abcde",
        UserDataResponse(
            "Jose de Jesus Duran Barajas",
            "Principal",
            "jose@gmail.com",
            23,
            true,
            "3310518075"
        )
    ),
    Triple(
        "Dario",
        "pass2024",
        UserDataResponse(
            "Dario Josue Gutierrez Flores",
            "Cajero",
            "dario@gmail.com",
            20,
            false,
            "5540012233"
        )
    ),
    Triple(
        "Gabriel",
        "12345",
        UserDataResponse(
            "Gabriel Alejandro Ramirez Leon",
            "Vendedor",
            "gabriel@gmail.com",
            25,
            false,
            "3328025556"
        )
    ),
    Triple(
        "Ana",
        "abcde",
        UserDataResponse(
            "Ana Patricia Castillo Medina",
            "Principal",
            "Ana@gmail.com",
            22,
            true,
            "3310518075"
        )
    ),
    Triple(
        "Edgar",
        "pass2024",
        UserDataResponse(
            "Edgar Isaac Ayala Salgado",
            "Cajero",
            "edgar@gmail.com",
            21,
            false,
            "5540012233"
        )
    ),
)




fun getRegisterRequest(registerRequest: RegisterRequest): RegisterResponse {
    val randomValue = Random.nextInt(0, 100)
    return if (randomValue <= 50) {
        RegisterResponse(
            200, dataResponse = UserDataResponse(
                userName = registerRequest.userName,
                workstation = registerRequest.workstation,
                email = registerRequest.email,
                age = registerRequest.age,
                isPrincipal = registerRequest.isPrincipal,
                phone = registerRequest.phone
            )
        )
    } else {
        RegisterResponse(
            500, 500, "Error al guardar", dataResponse = UserDataResponse(
                "",
                "",
                "",
                0,
                false,
                ""
            )
        )
    }

}

fun getUserData(loginRequest: LoginRequest): LoginResponse {
    val match = validUsers.find { (user, pass, _) ->
        user.equals(loginRequest.user, ignoreCase = true) && pass == loginRequest.password
    }

    return if (match != null) {
        LoginResponse(
            status = 200,
            codeError = null,
            data = match.third
        )
    } else {
        LoginResponse(
            status = 401,
            codeError = 401,
            data = UserDataResponse("", "", "", 0, false, "")
        )
    }
}

fun getRecoveryPasswordRequest(email: String): Int {
    val validEmails = validUsers.map { it.third.email.lowercase() }

    return if (validEmails.contains(email.lowercase())) {
        200
    } else {
        404
    }
}

fun getAllCategoriesRequest(): ProductsResponse {
    val randomValue = Random.nextInt(0, 100)
            // return if (randomValue <= 50) {
    return if (true) {
        ProductsResponse(
            status = 200,
            null,
            data = products
        )
    }else{
        ProductsResponse(
            status = 500,
            R.string.error_fetch_products,
            data = emptyList()
        )
    }
}
