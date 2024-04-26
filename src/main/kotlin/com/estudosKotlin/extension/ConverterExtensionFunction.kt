package com.estudosKotlin.extension

import com.estudosKotlin.controller.dtos.PostRequestDto
import com.estudosKotlin.controller.dtos.PutRequestDto
import com.estudosKotlin.model.CustomerModel

//Extension function

fun PostRequestDto.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, amount = this.amount)
}

fun PutRequestDto.toCustomerModel(id: Int): CustomerModel {
    return CustomerModel(id = id,  name = this.name, amount = this.amount)
}