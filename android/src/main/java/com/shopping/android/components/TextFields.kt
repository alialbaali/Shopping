package com.shopping.android.components

import androidx.compose.animation.animate
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focusObserver
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.shopping.android.DefaultIcons
import com.shopping.common.Strings
import com.shopping.common.emailValidator
import com.shopping.common.passwordValidator
import com.shopping.common.textValidator

@OptIn(ExperimentalFocus::class)
@Composable
fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    asset: VectorAsset? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Next,
    valueValidator: (String) -> Boolean = ::textValidator,
) {

    var isErrorValue by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    val activeColor = animate(if (isFocused) MaterialTheme.colors.primary else Color.Transparent)
    val backgroundColor = animate(if (isFocused) MaterialTheme.colors.background else MaterialTheme.colors.surface)
    val elevation = animate(if (isFocused) 2.dp else 0.dp)

    TextField(
        value,
        onValueChange = {
            isErrorValue = valueValidator(it)
            onValueChange(it)
        },
        modifier
            .layoutId(placeholder)
            .background(MaterialTheme.colors.surface, MaterialTheme.shapes.small)
            .border(2.dp, activeColor, MaterialTheme.shapes.small)
            .drawShadow(elevation, MaterialTheme.shapes.small)
            .focusObserver { isFocused = it == FocusState.Active },
        shape = MaterialTheme.shapes.small,
        placeholder = { Text(placeholder, color = MaterialTheme.colors.onSurface.copy(alpha = 0.8F), fontWeight = FontWeight.SemiBold) },
        leadingIcon = { if (asset != null) Icon(asset, tint = MaterialTheme.colors.onSurface.copy(0.5F)) },
        trailingIcon = trailingIcon,
        backgroundColor = backgroundColor,
        keyboardType = keyboardType,
        visualTransformation = visualTransformation,
        onImeActionPerformed = { action, keyboard -> if (action == ImeAction.Done) keyboard?.hideSoftwareKeyboard() },
        activeColor = Color.Transparent,
        imeAction = imeAction,
        inactiveColor = Color.Transparent,
        isErrorValue = isErrorValue,
        errorColor = Color.Transparent,
        textStyle = TextStyle(MaterialTheme.colors.onSurface, fontWeight = FontWeight.SemiBold)
    )
}

@Composable
fun NameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
) {
    DefaultTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        asset = DefaultIcons.Person,
        imeAction = imeAction,
        placeholder = Strings.Name,
    )
}

@Composable
fun EmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
) {
    DefaultTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        asset = DefaultIcons.Email,
        imeAction = imeAction,
        placeholder = Strings.Email,
        keyboardType = KeyboardType.Email,
        valueValidator = ::emailValidator
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = Strings.Password,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    DefaultTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        keyboardType = KeyboardType.Password,
        asset = DefaultIcons.Lock,
        imeAction = imeAction,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        placeholder = placeholder,
        valueValidator = ::passwordValidator,
        trailingIcon = {
            Icon(
                asset = if (isPasswordVisible) DefaultIcons.Visibility else DefaultIcons.VisibilityOff,
                modifier = Modifier.clickable { isPasswordVisible = !isPasswordVisible },
                tint = MaterialTheme.colors.onSurface.copy(0.5F),
            )
        },
    )

}

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    DefaultTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        asset = DefaultIcons.Search,
        imeAction = ImeAction.Done,
        placeholder = Strings.Search,
    )
}