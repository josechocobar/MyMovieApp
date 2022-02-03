package com.cuty.mymovieapp.utils

sealed class MyState {
    object Fetched : MyState()
    object Error : MyState()
}