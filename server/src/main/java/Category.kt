package com.amazonaws.lambda.demo

data class Category @JvmOverloads constructor(
    var title: String = "",
    var description: String? = null,
    var id: String? = null)
