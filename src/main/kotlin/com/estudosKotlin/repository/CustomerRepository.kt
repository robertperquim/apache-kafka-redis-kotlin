package com.estudosKotlin.repository

import com.estudosKotlin.model.CustomerModel
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CustomerRepository (
    private val jdbcTemplate: JdbcTemplate
    
) {

    @Cacheable("itens",  key = "#name ?: 'all'")
    fun getAllCustomers(name: String?): List<CustomerModel> {
        println("testando cache")
        val query = if (name.isNullOrEmpty()) {
            "SELECT * FROM customers"
        } else {
            "SELECT * FROM customers WHERE name ILIKE '%$name%'"
        }

        return jdbcTemplate.query(query) { rs, _ ->
            CustomerModel(
                id = rs.getInt("id"),
                name = rs.getString("name"),
                amount = rs.getInt("amount")
            )
        }
    }

    @CacheEvict("itens", allEntries = true)
    fun create(customer: CustomerModel) {
        val query = "INSERT INTO customers (name, amount) VALUES (?, ?)"
        jdbcTemplate.update(query, customer.name, customer.amount)
    }

    @Cacheable("itensId")
    fun getCustomer(id: Int): CustomerModel? {
        val query = "SELECT * FROM customers WHERE id = ?"
        return jdbcTemplate.queryForObject(query, { rs, _ ->
            CustomerModel(
                id = rs.getInt("id"),
                name = rs.getString("name"),
                amount = rs.getInt("amount")
            )
        }, id)
    }
    @CacheEvict(value = ["itens", "itensId"], allEntries = true)
    fun update(customer: CustomerModel){
        val query = "UPDATE customers SET name = ?, amount= ? WHERE id = ?"
        jdbcTemplate.update(query, customer.name, customer.amount, customer.id)
    }

    @CacheEvict(value = ["itens", "itensId"], allEntries = true)
    fun delete(id : Int){
        val query = "DELETE FROM customers WHERE id = ?"
        jdbcTemplate.update(query, id)
    }

}