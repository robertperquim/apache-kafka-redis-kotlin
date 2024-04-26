package com.estudosKotlin.messaging


import com.estudosKotlin.controller.dtos.PostRequestDto
import com.estudosKotlin.model.CustomerModel
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class CustomerNotificationProducer (
        var kafkaTemplate: KafkaTemplate<Any,CustomerModel>,
        @Value("\${kafka.customer.notification-output}")
        var topic : String
){

    fun sendMessaging (customer: CustomerModel){
        kafkaTemplate.send(topic, customer)
    }
}