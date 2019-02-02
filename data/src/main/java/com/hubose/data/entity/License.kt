package com.hubose.data.entity

import com.squareup.moshi.Json

data class License(
    @Json(name = "key")
    val key: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "spdx_id")
    val spdxId: String,
    @Json(name = "url")
    val url: String
)