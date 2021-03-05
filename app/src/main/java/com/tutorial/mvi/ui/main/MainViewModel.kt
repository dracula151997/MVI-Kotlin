package com.tutorial.mvi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tutorial.mvi.model.Blog
import com.tutorial.mvi.model.User
import com.tutorial.mvi.repository.MainRepository
import com.tutorial.mvi.utils.AbsentLiveData
import com.tutorial.mvi.utils.DataState

class MainViewModel : ViewModel() {
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()
    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData();

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<DataState<MainViewState>> =
        Transformations.switchMap(_stateEvent) { event ->
            handleStateEvent(event)
        }

    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        return when (stateEvent) {
            is MainStateEvent.GetUser -> {
                MainRepository.getUser(stateEvent.userId)
            }
            is MainStateEvent.GetBlogPostsEvent -> {
                return MainRepository.getBlogPosts()
            }

            is MainStateEvent.None -> {
                AbsentLiveData.create()
            }
        }
    }

    fun setBlogListData(blogPosts: List<Blog>) {
        val update = getCurrentViewStateOrNew()
        update.blogPosts = blogPosts
        _viewState.value = update
    }

    fun setUser(user: User) {
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    fun setStateEvent(event: MainStateEvent) {
        _stateEvent.value = event
    }

    private fun getCurrentViewStateOrNew(): MainViewState {
        return viewState.value ?: MainViewState()
    }
}