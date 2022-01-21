package com.cuty.mymovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cuty.mymovieapp.presenter.SplashViewModel


class MainActivity : AppCompatActivity() {




    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //installSplashScreen().apply {
        //    setKeepVisibleCondition{
        //        viewmodel.isLoading.value
        //    }
        //}
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController




    }
}