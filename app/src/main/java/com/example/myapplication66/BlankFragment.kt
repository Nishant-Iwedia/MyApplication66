package com.example.myapplication66

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setContent {
                val scrollState = rememberLazyListState()
                val scope = rememberCoroutineScope()
                var selectedIndex by remember {
                    mutableStateOf(-1)
                }

                LazyColumn(
                    state = scrollState,
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    itemsIndexed(items = (0..100).toList()) { index, item ->

                        Card(
                            backgroundColor = Color.DarkGray,
                            elevation = 10.dp,
                            contentColor = Color.White,
                            modifier = Modifier
                                .width(200.dp)
                                .height(130.dp)
                        ) {
                            Text(text = "Channel - $item",
                                Modifier
                                    .selectable(
                                        selected = selectedIndex == index,
                                        onClick = {
                                            selectedIndex = index
                                            Toast
                                                .makeText(requireContext(), "Clicked $selectedIndex", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    )
                                    .background(
                                        if (selectedIndex == index) Color.Green
                                        else Color.Transparent
                                    )
                                    .padding(8.dp))
                        }
                    }
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

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
    }
}