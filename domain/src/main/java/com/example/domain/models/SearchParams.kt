package com.example.domain.models

data class SearchParams(
    val q:String,
    val _in:String? = null,
    val from:String? = null,
    val to:String? = null,
    val sortby:String? = null,
)
