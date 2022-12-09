package com.example.myapplication66

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment

class ChoosePvrTypeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ChoosePvrTypeViewModel()
        return ComposeView(requireContext()).apply {
            setContent {
                CreateLayout(onAction = viewModel::onAction)
            }
        }
    }
}

@Composable
fun CreateLayout(
    onAction: (ChoosePvrTypeAction) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(1f)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Start Recording",
                color = Color.White,
                fontSize = 14.sp,
                lineHeight = 12.sp
            )
            GetSpace(space = 28)
            CreateButton(text = "Now", onClickFunc = {
                onAction(ChoosePvrTypeAction.Now)
            }, requestFocus = true)
            GetSpace(space = 6.3)
            CreateButton(text = "After current show", onClickFunc = {
                onAction(ChoosePvrTypeAction.AfterEvent)
            })
            GetSpace(space = 6.3)
            CreateButton(text = "Manually", onClickFunc = {
                onAction(ChoosePvrTypeAction.Manually)
            })
        }
    }
}