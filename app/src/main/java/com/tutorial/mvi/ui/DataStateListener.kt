package com.tutorial.mvi.ui

import com.tutorial.mvi.utils.DataState

interface DataStateListener {
    fun onDataStateChanged(dataState: DataState<*>?)
}