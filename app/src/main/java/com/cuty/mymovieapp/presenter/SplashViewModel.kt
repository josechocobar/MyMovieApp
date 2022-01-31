package com.cuty.mymovieapp.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class SplashViewModel :ViewModel(){

    @ExperimentalCoroutinesApi
    val isLoading = MutableStateFlow(true)
    init {
        viewModelScope.launch {
            delay(3000)
            isLoading.value = false
        }
    }
}