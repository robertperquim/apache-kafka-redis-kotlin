package com.estudosKotlin.messaging

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.core.env.Environment
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.stereotype.Component
import java.util.*

@Component
class KafkaConfig {

    fun createConsumer(
        env: Environment
    ): KafkaConsumer<String, String> {

        val props = Properties()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = env.getProperty("kafka.port", "localhost:9092")
        props[ConsumerConfig.GROUP_ID_CONFIG] = env.getProperty("kafka.customer.notification-group.id", "group_id")
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        props[JsonDeserializer.TRUSTED_PACKAGES] = env.getProperty("spring.kafka.consumer.properties.spring.json.trusted.packages")
        return KafkaConsumer(props)
    }
}