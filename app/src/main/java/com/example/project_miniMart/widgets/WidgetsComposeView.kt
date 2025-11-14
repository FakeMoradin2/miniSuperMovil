package com.example.minimartapp.ui.widgets

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.R
import com.example.project_miniMart.ui.theme.DpSizes.Dp8
import com.example.project_miniMart.ui.theme.Styles.primaryButtonStyle
import com.example.project_miniMart.ui.theme.Styles.roboto16Medium
import com.example.project_miniMart.ui.theme.Styles.secondaryButtonStyle
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoMediumSp16
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoRegularSp10
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoThinSp12
import com.example.project_miniMart.ui.flows.authFlow.screens.register.model.RegisterStates
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoMediumSp12
import com.example.project_miniMart.widgets.heder.HeaderComposeView
import com.example.project_miniMart.widgets.listCategories.ButtonPill
import com.example.project_miniMart.widgets.listCategories.ItemCategory


sealed class TypesButtons {
    data object Primary : TypesButtons()
    data object Secondary : TypesButtons()
}

@Composable
fun TopBarComposeView(
    title: String,
    onClickBack: (() -> Unit)? = null,
) {

    HeaderComposeView(modifier = Modifier.height(64.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (onClickBack != null) {
                IconButton(onClick = {
                    onClickBack.invoke()
                }) {
                    Icon(painter = painterResource(R.drawable.ic_back), null, tint = Color.White)
                }
            }
            Spacer(modifier = Modifier.width(Dp8))
            Text(title, style = roboto16Medium, color = Color.White)
        }
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
    isError: Boolean = false,
    textError: String = "",
    onValueChange: (String) -> Unit,
) {
    Column(modifier = modifier) {
        Text(label, style = textStyleRobotoMediumSp16)
        Spacer(modifier = Modifier.height(8.dp))

        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        TextField(
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            value = value,
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
                    val icon = if (passwordVisible)
                        painterResource(id = R.drawable.ic_visible_off)
                    else
                        painterResource(id = R.drawable.ic_visibility)

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
            isError = (isError && value.isNotEmpty()),
            supportingText = {
                if (isError && value.isNotEmpty()) {
                    Text(textError)
                }
            },
            shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
        )
    }
}


@Composable
fun ButtonComposeView(
    modifier: Modifier = Modifier,
    typesButtons: TypesButtons,
    isEnable: Boolean = true,
    title: String,
    @DrawableRes icon: Int? = null,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = { onClick.invoke() }, enabled = isEnable)
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(8.dp),
                color = getBorderButton(typesButtons)
            )
            .fillMaxWidth()
            .height(56.dp)
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

private fun getBorderButton(typesButtons: TypesButtons): Color {
    return when (typesButtons) {
        TypesButtons.Primary -> Color.Transparent
        TypesButtons.Secondary -> Color.LightGray
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
    }
}

private fun getStyleButton(typesButtons: TypesButtons): TextStyle {
    return when (typesButtons) {
        TypesButtons.Primary -> primaryButtonStyle
        TypesButtons.Secondary -> secondaryButtonStyle
    }
}


@Composable
fun DividerComposeView(text: String = "Need an account?") {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalDivider(thickness = 1.dp, modifier = Modifier.weight(1f))
        Text(text, style = textStyleRobotoThinSp12, modifier = Modifier.padding(horizontal = 2.dp))
        HorizontalDivider(thickness = 1.dp, modifier = Modifier.weight(1f))
    }
}

@Composable
fun LabelsValidations(state: RegisterStates) {
    val validations = listOf(
        stringResource(R.string.validate_length_minimum, 8) to state.isValidPasswordLength,
        stringResource(R.string.validate_uppercase) to state.isValidPasswordUpperCase,
        stringResource(R.string.validate_special_character) to state.isValidPasswordSpecialCharacter,
        stringResource(R.string.validate_three_numbers_in_sequence) to state.isValidPasswordThreeConsecutive,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        validations.forEach { (label, value) ->
            ValidationsRequest(label, value)
        }
    }
}

@Composable
private fun ValidationsRequest(title: String, status: Boolean) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(getImage(status)), null)
        Spacer(modifier = Modifier.width(4.dp))
        Text(title, style = textStyleRobotoRegularSp10)
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun getImage(status: Boolean): Int {
    return if (status) R.drawable.ic_success else R.drawable.ic_error
}

@Composable
fun CardInformation(
    color: Color = MaterialTheme.colorScheme.surface,
    text: String,
    description: String,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text,
                style = if (color == MaterialTheme.colorScheme.surface) secondaryButtonStyle else primaryButtonStyle
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(description, textAlign = TextAlign.Center)
        }
    }
}

enum class TypeAlert {
    SUCCESS, ERROR
}

@Composable
fun InfoDialog(
    onDismiss: () -> Unit,
    title: String,
    message: String,
    typeAlert: TypeAlert,
) {
    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = { onDismiss() },
        confirmButton = {
            ButtonPill(label = "Accept"){onDismiss()}
        },
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = getTypeAlert(typeAlert)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 8.dp
    )
}

private fun getTypeAlert(typeAlert: TypeAlert): Int {
    return when (typeAlert) {
        TypeAlert.SUCCESS -> R.drawable.ds_image_alert_success
        TypeAlert.ERROR -> R.drawable.ds_image_alert_warning
    }
}


@Composable
fun DottedLineComposeView(modifier: Modifier, colorLine: Color = Color(0xFF282828)) {
    Canvas(
        modifier = modifier
            .height(1.dp)
    ) {
        val lineWidth = 10f
        val gapWidth = 10f
        val totalWidth = size.width
        var startX = 0f

        while (startX < totalWidth) {
            drawLine(
                color = colorLine,
                start = Offset(startX, size.height / 2),
                end = Offset(startX + lineWidth, size.height / 2),
                strokeWidth = 1f
            )
            startX += lineWidth + gapWidth
        }
    }
}


@Composable
fun LabelButtonSheet(icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick, enabled = true)
            .background(Color(0x6AD9D9D9))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(label, style = textStyleRobotoMediumSp12)
    }
}

