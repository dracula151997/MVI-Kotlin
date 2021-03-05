package com.tutorial.mvi.ui.main

import com.tutorial.mvi.model.Blog
import com.tutorial.mvi.model.User

data class MainViewState(
    var blogPosts: List<Blog>? = null,
    var user: User? = null
)