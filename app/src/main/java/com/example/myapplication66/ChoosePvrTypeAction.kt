package com.example.myapplication66

sealed class ChoosePvrTypeAction{
    object Now : ChoosePvrTypeAction()
    object AfterEvent : ChoosePvrTypeAction()
    object Manually : ChoosePvrTypeAction()
}
