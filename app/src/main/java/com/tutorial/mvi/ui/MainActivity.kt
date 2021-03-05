package com.tutorial.mvi.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.tutorial.mvi.R
import com.tutorial.mvi.ui.main.MainFragment
import com.tutorial.mvi.ui.main.MainViewModel
import com.tutorial.mvi.utils.DataState

class MainActivity : AppCompatActivity(), DataStateListener {
    lateinit var mainViewModel: MainViewModel
    private lateinit var progress_bar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMainFragment()
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        progress_bar = findViewById(R.id.progress_bar)
    }

    private fun showMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, MainFragment(), "MainFragment")
            .commit();
    }

    override fun onDataStateChanged(dataState: DataState<*>?) {
        handleDataStateChange(dataState)
    }

    private fun handleDataStateChange(dataState: DataState<*>?) {
        dataState?.let {

            it.message?.let { error ->
                showToast(error)
            }

            it.loading.let { isLoading ->
                showProgressBar(isLoading)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressBar(isVisible: Boolean) {
        if (isVisible)
            progress_bar.visibility = View.VISIBLE
        else
            progress_bar.visibility = View.GONE

    }
}