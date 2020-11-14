package com.i.paging.models

import com.google.gson.annotations.SerializedName

/**
 * Data class to hold repo responses from searchRepo API calls.
 */
data class RepoSearchResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<Repository> = emptyList(),
    val nextPage: Int? = null
)