package com.estudosKotlin.model

import java.io.Serializable

//o que e um data class
// representar dados de uma forma estruturada sem necessidade de definif tostring equals hashcode e outros metodos
// nao e necessario implementar geters e seters.


data class CustomerModel(

    var id: Int? = null,
    var name: String,
    var amount : Int
) : Serializable