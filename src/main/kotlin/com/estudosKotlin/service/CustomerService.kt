package com.estudosKotlin.service

import com.estudosKotlin.controller.dtos.PostRequestDto
import com.estudosKotlin.extension.toCustomerModel
import com.estudosKotlin.messaging.CustomerNotificationConsumer
import com.estudosKotlin.messaging.CustomerNotificationProducer
import com.estudosKotlin.messaging.NotificationConsumer
import com.estudosKotlin.model.CustomerModel
import com.estudosKotlin.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    val customerNotificationProducer: CustomerNotificationProducer,
    private val notificationConsumer: NotificationConsumer
) {

    fun getAllCustomers(name: String?): List<CustomerModel> {
        return customerRepository.getAllCustomers(name)
    }

    fun create(customer: CustomerModel) {

        customerNotificationProducer.sendMessaging(customer)

        notificationConsumer.startConsuming()

    }

    fun getCustomer(id: Int): CustomerModel? {
        return customerRepository.getCustomer(id)
    }

    fun update(customer: CustomerModel) {
        customerRepository.update(customer)
    }

    fun delete(id: Int) {
        customerRepository.delete(id)
    }

    fun save(customerModel: CustomerModel) {
        customerRepository.create(customerModel)
    }
}