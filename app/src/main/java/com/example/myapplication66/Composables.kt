package com.example.myapplication66

import android.app.FragmentManager
import android.app.FragmentTransaction
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun GetSpace(space: Any) {
    when (space) {
        is Int -> Spacer(modifier = Modifier.height(space.dp))
        is Float -> Spacer(modifier = Modifier.height(space.dp))
        is Double -> Spacer(modifier = Modifier.height(space.dp))
    }
}

@Composable
fun CreateButton(
    text: String,
    onClickFunc: () -> Unit,
    modifier: Modifier = Modifier,
    requestFocus: Boolean = false
) {

    var backgroundColor by remember {
        mutableStateOf(R.color.color_not_selected)
    }
    var fontColor by remember {
        mutableStateOf(Color.White)
    }

    val focusRequester by remember {
        mutableStateOf(FocusRequester())
    }

    var isFocus by remember {
        mutableStateOf(false)
    }

    Button(
        onClick = onClickFunc,
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = backgroundColor),
            contentColor = fontColor
        ),
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                if (it.isFocused) {
                    backgroundColor = R.color.color_selector
                    fontColor = Color.Black
                } else {
                    backgroundColor = R.color.color_not_selected
                    fontColor = Color.White
                }
            }.then(modifier)
    ) {
        Text(text = text)
    }

}