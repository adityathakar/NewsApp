package com.example.data.model

import com.google.gson.annotations.SerializedName

data class SourceApiModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
