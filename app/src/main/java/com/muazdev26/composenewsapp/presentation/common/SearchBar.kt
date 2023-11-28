@file:OptIn(ExperimentalMaterial3Api::class)

package com.muazdev26.composenewsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.muazdev26.composenewsapp.R
import com.muazdev26.composenewsapp.presentation.Dimensions.IconSize
import com.muazdev26.composenewsapp.presentation.Dimensions.MediumPadding
import com.muazdev26.composenewsapp.presentation.ui.theme.ComposeNewsAppCleanArchTheme

@Composable
fun Searchbar(
    modifier: Modifier,
    text: String,
    readOnly: Boolean,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onClick: (() -> Unit)? = null
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    val isClicked = interactionSource.collectIsPressedAsState().value
    LaunchedEffect(key1 = isClicked) {
        if (isClicked) {
            onClick?.invoke()
        }
    }

    Box(modifier = modifier.padding(MediumPadding)) {
        TextField(
            value = text, modifier = Modifier
                .fillMaxWidth()
                .searchBarBorder(),
            onValueChange = { onValueChange(it) },
            readOnly = readOnly,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search), contentDescription = "",
                    modifier = modifier.size(IconSize)
                )
            },
            placeholder = {
                Text(
                    stringResource(id = R.string.search_here),
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.placeholder)
                )
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.input_background),
                textColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick()
                }
            ),
            textStyle = MaterialTheme.typography.bodySmall,
            interactionSource = interactionSource
        )
    }

}

fun Modifier.searchBarBorder() = composed {
    if (!isSystemInDarkTheme()) {
        border(width = 1.dp, color = Color.Black, shape = MaterialTheme.shapes.medium)
    } else this
}

@Preview(showSystemUi = true)
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    ComposeNewsAppCleanArchTheme {
        Searchbar(
            modifier = Modifier,
            text = "",
            readOnly = false,
            onValueChange = {},
            onSearchClick = { })
    }
}