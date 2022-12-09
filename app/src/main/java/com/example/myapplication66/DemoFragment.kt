package com.example.myapplication66

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.rotary.onRotaryScrollEvent
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.fragment.app.Fragment
import kotlinx.coroutines.launch

class DemoFragment : Fragment() {

    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val focusRequester by remember {
                    mutableStateOf(FocusRequester())
                }
                LaunchedEffect(Unit){
                    focusRequester.requestFocus()
                }
                val scrollState = rememberLazyListState()
                val scope = rememberCoroutineScope()
                var selectedIndex by remember {
                    mutableStateOf(0)
                }

                LazyColumn(
                    modifier = Modifier
                        .padding(5.dp)
                        .background(Color.White)
                        .focusRequester(focusRequester)
                        .focusable(),
                    state = scrollState,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    itemsIndexed(items = (0..100).toList()) { index, item ->
                        
                        ListItemView(name = "Channel - $index",onFocused = { focused ->
                            scope.launch {
                                if (focused) {
                                    val visibleItemsInfo =
                                        scrollState.layoutInfo.visibleItemsInfo
                                    val visibleSet = visibleItemsInfo.map { it.index }.toSet()
                                    if (index == visibleItemsInfo.last().index) {
                                        scrollState.animateScrollToItem(index)
                                    } else if (visibleSet.contains(index) && index != 0) {
                                        scrollState.animateScrollToItem(index-1)
                                    }
                                }
                            }
                        }, onClick = {
                            Log.d("Nishant", "Clicked -  item $item")
                            scope.launch {
                                scrollState.scrollToItem(80).apply {

                                }
                            }
                        })
                        
                        
                        Text(text = "Hi $item",
                            Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = selectedIndex == item,
                                    onClick = {
                                        selectedIndex = item
                                    }
                                )
                                .background(
                                    if (selectedIndex == item) Color.Gray
                                    else Color.Transparent
                                )
                                .padding(8.dp))
                    }
                }
            }
        }
    }
}


/*
@Composable
fun ListItemView(
    name: String,
    onFocused: (Boolean) -> Unit = {},
    requester: FocusRequester = FocusRequester(),
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    var boxColor by remember { mutableStateOf(Color.White) }
    var focused by remember { mutableStateOf(true) }
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    Box(
        Modifier
            .focusRequester(requester)
            .onFocusChanged {
                boxColor = if (it.isFocused) Color.Green else Color.Gray
                focused = it.isFocused
                onFocused(it.isFocused)
            }
            .focusable()
            .background(boxColor)
            .zIndex(if (focused) 1f else 0f)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(30.dp),
            color = Color.White,
            style = MaterialTheme.typography.subtitle2
        )

        Text(text = name,
            Modifier
                .padding(30.dp)
                .selectable(
                    selected = selectedIndex == item,
                    onClick = {
                        selectedIndex = item
                    }
                )
                .background(
                    if (selectedIndex == item) Color.Gray
                    else Color.Transparent
                )
                .padding(8.dp))


    }
}*/
