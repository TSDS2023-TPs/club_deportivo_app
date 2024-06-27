package com.example.appclubdeportivo.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.data.CustomerDao
import com.example.appclubdeportivo.db_entities.Customer
import com.example.appclubdeportivo.view_entities.CustomerCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CustomerViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    private val customerDao: CustomerDao = appDatabase.customerDao()

    val customers: LiveData<List<CustomerCard>> = liveData {
        emit(getAllCustomersFromDB())
    }

    private suspend fun getAllCustomersFromDB(): List<CustomerCard> {
        return withContext(Dispatchers.IO) {
            customerDao.getAllCustomers()
        }
    }

    fun getCustomerByDocumentId(documentId: String): LiveData<CustomerCard> = liveData {
        emit(getCustomerByDocumentIdFromDB(documentId))
    }


    private suspend fun getCustomerByDocumentIdFromDB(documentId: String): CustomerCard {
        return withContext(Dispatchers.IO) {
            customerDao.getCustomerByDocumentId(documentId)
        }
    }

    fun insertCustomer(customer: Customer): LiveData<Long> = liveData {
        val result = withContext(Dispatchers.IO) {
            customerDao.insertCustomer(customer)
        }
        emit(result)
    }

    fun updateCustomer(customer: Customer): LiveData<Int> = liveData {
        val result = withContext(Dispatchers.IO) {
            customerDao.updateCustomer(customer)
        }
        emit(result)
    }

    fun deleteCustomer(customer: Customer): LiveData<Int> = liveData {
        val result = withContext(Dispatchers.IO) {
            customerDao.deleteCustomer(customer)
        }
        emit(result)
    }
}
