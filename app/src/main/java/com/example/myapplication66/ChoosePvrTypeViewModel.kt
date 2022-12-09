package com.example.myapplication66

import android.util.Log
import androidx.lifecycle.ViewModel

class ChoosePvrTypeViewModel : ViewModel(){

    fun onAction(action: ChoosePvrTypeAction){
        when(action){
            ChoosePvrTypeAction.Now -> {
                Log.d("Nishant", "onAction: Now")
            }
            ChoosePvrTypeAction.AfterEvent -> {
                Log.d("Nishant", "onAction: After Event")
            }
            ChoosePvrTypeAction.Manually -> {
                Log.d("Nishant", "onAction: Manually")
            }
        }
    }

}