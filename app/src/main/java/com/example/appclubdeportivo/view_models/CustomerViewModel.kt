package com.example.appclubdeportivo.view_models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.collection.emptyLongSet
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.data.CustomerDao
import com.example.appclubdeportivo.data.FeeDao
import com.example.appclubdeportivo.data.PersonDao
import com.example.appclubdeportivo.db_entities.Customer
import com.example.appclubdeportivo.db_entities.DocumentType
import com.example.appclubdeportivo.db_entities.Fee
import com.example.appclubdeportivo.db_entities.Person
import com.example.appclubdeportivo.view_entities.CustomerCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class CustomerViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    private val customerDao: CustomerDao = appDatabase.customerDao()
    private val personDao: PersonDao = appDatabase.personDao()
    private val feeDao: FeeDao = appDatabase.feeDao()
    val customers: LiveData<List<CustomerCard>> = liveData {
        emit(getAllCustomersFromDB())
    }


    private suspend fun getAllCustomersFromDB(): List<CustomerCard> {
        return withContext(Dispatchers.IO) {
            customerDao.getAllCustomers()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertCustomer(person: Person, customer: Customer) {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val personId = personDao.insertPerson(person)
                customer.personId = personId.toInt()
                customerDao.insertCustomer(customer)
                val newCustomerId = customerDao.getCustomerIdByDocumentId(person.identityDocumentNumber)
                addDefaultFee(customer, newCustomerId)
            }


        }
    }

    fun updateCustomer(customer: Customer): LiveData<Int> = liveData {
        val result = withContext(Dispatchers.IO) {
            customerDao.updateCustomer(customer)
        }
        emit(result)
    }

    fun logicalDeleteCustomers(customerIds: List<String>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                customerDao.logicalDeleteCustomers(customerIds)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addDefaultFee(customer: Customer, newCustomerId: Int) {
        val today = LocalDate.now()
        val dueDate = today.plusDays(30)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDueDateToday = today.format(formatter)
        val formattedNextMonth = dueDate.format(formatter)


        if(customer.membershipType == "Socio"){
            val defaultFee = Fee(customerId = newCustomerId, amount = 2000, month = Calendar.MONTH, dueDate = formattedNextMonth, status = "Impaga")
            feeDao.insertFee(defaultFee)
        } else {
            val defaultFee = Fee(customerId = newCustomerId, amount = 1000, month = Calendar.MONTH, dueDate = formattedDueDateToday, status = "Impaga")
            feeDao.insertFee(defaultFee)
        }
    }
}
