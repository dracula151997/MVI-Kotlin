package com.tutorial.mvi.repository

import androidx.lifecycle.LiveData
import com.tutorial.mvi.api.MyRetrofitBuilder
import com.tutorial.mvi.model.Blog
import com.tutorial.mvi.model.User
import com.tutorial.mvi.ui.main.MainViewState
import com.tutorial.mvi.utils.ApiSuccessResponse
import com.tutorial.mvi.utils.DataState
import com.tutorial.mvi.utils.GenericApiResponse

object MainRepository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<List<Blog>, MainViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<Blog>>) {
                result.value = DataState(
                    message = null,
                    loading = false,
                    data = MainViewState(
                        blogPosts = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<Blog>>> {
                return MyRetrofitBuilder.apiService.getBlogs()
            }
        }.asLiveData()
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<User, MainViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {
                result.value = DataState(
                    message = null,
                    loading = false,
                    data = MainViewState(
                        user = response.body
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {
                return MyRetrofitBuilder.apiService.getUser(userId)
            }

        }.asLiveData()
    }
}