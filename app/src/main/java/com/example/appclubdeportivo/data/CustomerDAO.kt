// ClienteDao.kt
package com.example.appclubdeportivo.data

import com.example.appclubdeportivo.db_entities.Customer

interface CustomerDAO {
    fun insertCustomer(cliente: Customer): Long
    fun updateCustomer(cliente: Customer): Int
    fun deleteCustomer(idCliente: Int): Int
    fun getAllCustomers(): List<Customer>
}
