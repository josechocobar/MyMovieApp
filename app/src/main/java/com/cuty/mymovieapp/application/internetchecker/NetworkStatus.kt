package com.cuty.mymovieapp.application.internetchecker

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}