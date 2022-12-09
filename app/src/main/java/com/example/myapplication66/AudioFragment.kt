package com.example.myapplication66

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment

class AudioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                createLayout()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun createLayout() {
    var selectedListItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val focusRequester by remember {
        mutableStateOf(FocusRequester())
    }

    val focusRequester2 by remember {
        mutableStateOf(FocusRequester())
    }

    var focusedIndex by remember {
        mutableStateOf(0)
    }

    var textColor by remember {
        mutableStateOf(Color.White)
    }

    Column(

        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(.4f)
            .padding(end = 30.dp),
        horizontalAlignment = Alignment.End,

        ) {

        //Header
        Text(
            text = "Audio",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(20.dp)
        )
        LaunchedEffect(Unit){
            focusRequester.requestFocus()
        }

        //List
        LazyColumn(modifier = Modifier){
            itemsIndexed(mutableListOf("Audio1", "Audio2", "Audio3")) { index, item ->
                Row(modifier = Modifier
                    .focusRequester(if (selectedListItemIndex == index) focusRequester else focusRequester2)
                    .onFocusChanged {
                        if(it.isFocused){
                            focusedIndex = index
                        }
                        /*Log.d("NishantTAG", "$index, $item ${it.isFocused}")*/
                    }
                    .fillMaxWidth(.12f)
                    .clip(RoundedCornerShape(80))
                    .clickable {
                        selectedListItemIndex = index
                        focusedIndex = index
                    }
                    .background(if (focusedIndex == index) Color.Yellow else Color.Transparent)
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {

                    textColor = if (focusedIndex == index) Color.Black else Color.White

                    Text(
                        text = item,
                        color = textColor
                    )

                    if (selectedListItemIndex == index && focusedIndex == index) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }else if(selectedListItemIndex == index){
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }


                }

            }
        }


    }

}