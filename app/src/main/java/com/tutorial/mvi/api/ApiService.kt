package com.tutorial.mvi.api

import androidx.lifecycle.LiveData
import com.tutorial.mvi.model.Blog
import com.tutorial.mvi.model.User
import com.tutorial.mvi.utils.GenericApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("placeholder/user/{userId}")
    fun getUser(
        @Path("userId") userId: String
    ): LiveData<GenericApiResponse<User>>

    @GET("placeholder/blogs")
    fun getBlogs(): LiveData<GenericApiResponse<List<Blog>>>
}