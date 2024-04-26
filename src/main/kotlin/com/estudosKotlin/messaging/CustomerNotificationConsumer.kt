package com.estudosKotlin.messaging



import com.estudosKotlin.model.CustomerModel
import com.estudosKotlin.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.time.Duration


@Component
class CustomerNotificationConsumer(
    private val kafkaConfig: KafkaConfig,
    private val env: Environment,
    private val customerRepository: CustomerRepository

): NotificationConsumer  {

    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    override fun startConsuming() {
        val consumer = kafkaConfig.createConsumer(env)
        val topics = mutableListOf("customer-notification-V1")
        consumer.subscribe(topics)

        while (true) {
            val records = consumer.poll(Duration.ofMillis(100))
            if (records.isEmpty) {

                break
            }
            for (record in records) {
                println("Consumindo mensagem: ${record.value()}")
                val customerModel: CustomerModel = objectMapper.readValue(record.value())

                customerRepository.create(customerModel)
            }

        }
}
}