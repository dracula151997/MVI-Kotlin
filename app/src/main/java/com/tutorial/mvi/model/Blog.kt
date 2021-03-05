package com.tutorial.mvi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Blog(
        @Expose
        @SerializedName("pk")
        val pk: String? = null,
        @Expose
        @SerializedName("title")
        val title: String? = null,
        @Expose
        @SerializedName("body")
        val body: String? = null,

        @Expose
        @SerializedName("image")
        val image: String? = null,

        @Expose
        @SerializedName("category")
        val category: String? = null
) {
}