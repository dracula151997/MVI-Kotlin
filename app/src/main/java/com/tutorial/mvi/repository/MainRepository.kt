package com.tutorial.mvi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.tutorial.mvi.api.MyRetrofitBuilder
import com.tutorial.mvi.ui.main.MainViewState
import com.tutorial.mvi.utils.ApiEmptyResponse
import com.tutorial.mvi.utils.ApiErrorResponse
import com.tutorial.mvi.utils.ApiSuccessResponse
import com.tutorial.mvi.utils.DataState

object MainRepository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return Transformations.switchMap(MyRetrofitBuilder.apiService.getBlogs()) { apiResponse ->
            object : LiveData<DataState<MainViewState>>() {
                override fun onActive() {
                    super.onActive()
                    value = when (apiResponse) {
                        is ApiSuccessResponse -> {
                            DataState.success(
                                message = null,
                                data = MainViewState(
                                    blogPosts = apiResponse.body
                                )
                            )
                        }

                        is ApiErrorResponse -> {
                            DataState.error(
                                message = apiResponse.error
                            )
                        }

                        is ApiEmptyResponse -> {
                            DataState.error(
                                message = "HTTP 204. Returned NOTHING!"
                            )
                        }
                    }
                }
            }
        }
    }

    fun getUser(userId: String): LiveData<DataState<MainViewState>> {
        return Transformations.switchMap(MyRetrofitBuilder.apiService.getUser(userId)) { response ->
            object : LiveData<DataState<MainViewState>>() {
                override fun onActive() {
                    super.onActive()
                    value = when (response) {
                        is ApiSuccessResponse -> {
                            DataState.success(
                                message = null,
                                data = MainViewState(
                                    user = response.body
                                )
                            )
                        }

                        is ApiErrorResponse -> {
                            DataState.error(
                                message = response.error
                            )
                        }

                        is ApiEmptyResponse -> {
                            DataState.error(
                                message = "HTTP 204. Returned NOTHING!"
                            )
                        }
                    }

                }
            }
        }
    }
}