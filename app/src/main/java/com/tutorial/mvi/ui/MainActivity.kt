package com.tutorial.mvi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tutorial.mvi.R
import com.tutorial.mvi.ui.main.MainFragment
import com.tutorial.mvi.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMainFragment()
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, MainFragment(), "MainFragment")
            .commit();
    }
}