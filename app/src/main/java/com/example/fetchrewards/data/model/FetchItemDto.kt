package com.example.fetchrewards.data.model

import com.google.gson.annotations.SerializedName

data class FetchItemDto(
    @SerializedName("id") val id: Int,
    @SerializedName("listId") val listId: Int,
    @SerializedName("name") val name: String?
)
