package com.estudosKotlin.messaging.database

import org.springframework.context.annotation.Configuration
import java.sql.SQLException
import javax.sql.DataSource

@Configuration
class CheckConnection(
    private val dataSource: DataSource
) {

    fun connection (): Boolean{
        return try {
            dataSource.connection.use { connection ->
                !connection.isClosed
             }
        }catch (e:SQLException){
            false
        }
    }
}