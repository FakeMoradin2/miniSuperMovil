package com.example.minimartapp.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.minimartapp.R
import com.example.minimartapp.ui.theme.DpSizes.dp4
import com.example.minimartapp.ui.theme.DpSizes.dp8
import com.example.minimartapp.ui.theme.Styles.PrimaryButtonStyle
import com.example.minimartapp.ui.theme.Styles.SecondaryButtonStyle
import com.example.minimartapp.ui.theme.Styles.TersearyButtonStyle
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoMediumSp12
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoMediumsp16
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoRegularSp10
import com.example.minimartapp.ui.theme.Styles.textStyleRobotoSp12


sealed class TypesButtons {
    data object Primary : TypesButtons()
    data object Secondary : TypesButtons()

    data object Terseary : TypesButtons()
}

@Composable
fun ProductCardComposeView(
    modifier: Modifier = Modifier,
    productName: String,
    price: String,
    stock: Int,
    icon: Int?,
    onAddClick: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .width(10.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Imagen del producto
            if (icon != null) {
                AsyncImage(
                    model = icon,
                    contentDescription = productName,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    contentScale = ContentScale.Fit
                )
            } else {

                androidx.compose.foundation.Image(
                    painter = painterResource(R.drawable.ic_logo),
                    contentDescription = productName,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    contentScale = ContentScale.Fit
                )
            }
            // Nombre del producto
            Text(
                text = productName,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            // Precio
            Text(
                text = "$$price",
                fontSize = 13.sp,
                color = Color(0xFF00897B)
            )

            // Stock
            Text(
                text = "Stock: $stock",
                fontSize = 12.sp,
                color = when {
                    stock == 0 -> Color(0xFFD50000)        // Rojo - Sin stock
                    stock <= 5 -> Color(0xFFFF9800)        // Amarillo/Naranja - Stock bajo (1-5)
                    else -> Color(0xFF00C853)              // Verde - Stock normal (6+)
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Botón "Add"

            ButtonCategorieComposeView(
                typesButtons = TypesButtons.Primary,
                isEnable = stock > 0,
                title = "Add",
                onClick = {
                }
            )
        }
    }
}



@Composable
fun ButtonCategorieComposeView(
    typesButtons: TypesButtons,
    isEnable: Boolean = true,
    title: String,
    @DrawableRes icon: Int? = null,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp)) // forma ovalada
            .border(
                width = 1.dp,
                color = getBorderButton(typesButtons),
                shape = RoundedCornerShape(50.dp)
            )
            .background(
                getBackgroundButton(typesButtons, isEnable),
                shape = RoundedCornerShape(50.dp)
            )
            .clickable(onClick = { onClick.invoke() }, enabled = isEnable)
            .padding(horizontal = 30.dp, vertical = 8.dp) // controla el alto/ancho
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                        .padding(end = 6.dp)
                )
            }
            Text(
                text = title,
                fontSize = 12.sp, style = getStyleButton(typesButtons),
                maxLines = 1
            )
        }
    }
}


//-----------------------------------------------------------------------------------------------------------------

@Composable
fun TopBarComposeView(
    titulo: String, onClickBack: (() -> Unit)? = null, //parametro y a su vez una funcion
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                dp8
            ), verticalAlignment = Alignment.CenterVertically
    ) {

        if (onClickBack != null) {
            IconButton(onClick = {
                onClickBack.invoke()
            }) {
                Image(painter = painterResource(R.drawable.ic_back), null)
            }
        }
        Spacer(modifier = Modifier.width(dp4))

        Text(titulo, style = textStyleRobotoMediumSp12)
    }
}


@Composable
fun InputTextFieldComposeView(
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier,
    label: String,
    placeholder: String,
    isPassword: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Text(label, style = textStyleRobotoMediumsp16)
        Spacer(modifier = Modifier.height(8.dp))

        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        TextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            value = value,
            maxLines = 1,
            onValueChange = { onValueChange(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder) },
            visualTransformation = when {
                !isPassword -> VisualTransformation.None
                passwordVisible -> VisualTransformation.None
                else -> PasswordVisualTransformation()
            },
            trailingIcon = {
                if (isPassword) {
                    val icon = if (passwordVisible) painterResource(id = R.drawable.ic_visible_off)
                    else painterResource(id = R.drawable.ic_visibility)

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            painter = icon,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                }
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFF64B5F6),
                unfocusedContainerColor = Color(0xFFFCFCFC),
                focusedContainerColor = Color(0xFFFCFCFC)
            ),
            shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
        )
    }
}


@Composable
fun ButtonComposeView(
    typesButtons: TypesButtons,
    isEnable: Boolean = true,
    title: String,
    @DrawableRes icon: Int? = null,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .clickable(onClick = { onClick.invoke() }, enabled = isEnable)
            .height(56.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = getBorderButton(typesButtons)
            )
            .background(
                getBackgroundButton(typesButtons, isEnable),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Image(painter = painterResource(icon), null)
                Spacer(modifier = Modifier.width(24.dp))
            }
            Text(title, style = getStyleButton(typesButtons))
        }
    }
}

@Composable
fun ButtonMenuComposeView(
    typesButtons: TypesButtons,
    isEnable: Boolean = true,
    title: String,
    @DrawableRes icon: Int? = null,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .clickable(onClick = { onClick.invoke() }, enabled = isEnable)
            .height(40.dp)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(10.dp),
                color = getBorderButton(typesButtons)
            )
            .background(
                getBackgroundButton(typesButtons, isEnable),
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start // Cambiado a Start
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
            }
            Text(
                text = title,
                style = getStyleButton(typesButtons),
                modifier = Modifier.weight(1f), // Para que el texto ocupe el espacio disponible
                textAlign = TextAlign.Start // Alineación de texto al inicio
            )
        }
    }
}


private fun getBackgroundButton(typesButtons: TypesButtons, isEnable: Boolean): Color {
    return when (typesButtons) {
        TypesButtons.Primary -> {
            if (isEnable) Color(0xFF64B5F6)
            else Color.LightGray
        }

        TypesButtons.Secondary -> {
            if (isEnable) Color.White
            else Color(0x2DE5E5E5)
        }

        TypesButtons.Terseary -> {
            if (isEnable) Color(0x1A000000)
            else Color.White
        }
    }
}

private fun getStyleButton(typesButtons: TypesButtons): TextStyle {
    return when (typesButtons) {
        TypesButtons.Primary -> PrimaryButtonStyle
        TypesButtons.Secondary -> SecondaryButtonStyle
        TypesButtons.Terseary -> TersearyButtonStyle
    }
}

private fun getBorderButton(typesButtons: TypesButtons): Color {
    return when (typesButtons) {
        TypesButtons.Primary -> Color.Transparent
        TypesButtons.Secondary -> Color.LightGray
        TypesButtons.Terseary -> Color.Transparent
    }
}

@Composable
fun PasswordValidationComposeView(isValid: Boolean) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(R.drawable.ic_check),
            null,
            tint = getValidPasswordColor(isValid)
        )
        Text(
            "At least 8 characters",
            style = textStyleRobotoRegularSp10,
            color = getValidPasswordColor(isValid)
        )
    }
}

private fun getValidPasswordColor(isValid: Boolean): Color {
    return if (isValid) Color(0xFF64B5F6)
    else Color(0XFF4A4A4A)
}

@Composable
fun DividerComposeView(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        HorizontalDivider(thickness = 1.dp, modifier = Modifier.weight(1f))
        Text(
            "Or Continue With",
            style = textStyleRobotoSp12,
            modifier = Modifier.padding(horizontal = 2.dp)
        )
        HorizontalDivider(thickness = 1.dp, modifier = Modifier.weight(1f))
    }
}




