package com.estudosKotlin.controller

import com.estudosKotlin.controller.dtos.PostRequestDto
import com.estudosKotlin.controller.dtos.PutRequestDto
import com.estudosKotlin.extension.toCustomerModel
import com.estudosKotlin.model.CustomerModel
import com.estudosKotlin.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("customers")
class CustomerController (
    val customerService: CustomerService
) {
    @GetMapping()
    fun getAllCustomers(@RequestParam name: String?): List<CustomerModel> {
        return customerService.getAllCustomers(name)
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostRequestDto){
        customerService.create(customer.toCustomerModel())
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerModel? {
        return customerService.getCustomer(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody customer: PutRequestDto){
       customerService.update(customer.toCustomerModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        customerService.delete(id)
    }


}