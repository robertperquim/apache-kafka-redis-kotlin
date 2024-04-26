package com.estudosKotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class EstudosKotlinApplication

fun main(args: Array<String>) {
	runApplication<EstudosKotlinApplication>(*args)
}
