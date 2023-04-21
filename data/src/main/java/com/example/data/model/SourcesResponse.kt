package com.example.data.model

import com.google.gson.annotations.SerializedName

data class SourcesResponse(
    @SerializedName("sources")
    val sources: List<SourceApiModel>
)
