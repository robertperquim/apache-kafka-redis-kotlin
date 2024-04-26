package com.estudosKotlin.controller.dtos

import java.io.Serializable

data class PostRequestDto(
    var name: String,
    var amount: Int
)