package com.tutorial.mvi.ui.main

sealed class MainStateEvent {
    class GetBlogPostsEvent : MainStateEvent()
    class GetUser(
        val userId: String
    ) : MainStateEvent()

    class None : MainStateEvent()
}