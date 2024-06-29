package com.example.appclubdeportivo.view_models

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.collection.emptyLongSet
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.room.PrimaryKey
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.data.CustomerDao
import com.example.appclubdeportivo.data.DocumentTypeDao
import com.example.appclubdeportivo.data.FeeDao
import com.example.appclubdeportivo.data.PersonDao
import com.example.appclubdeportivo.db_entities.Customer
import com.example.appclubdeportivo.db_entities.DocumentType
import com.example.appclubdeportivo.db_entities.Fee
import com.example.appclubdeportivo.db_entities.Person
import com.example.appclubdeportivo.view_entities.CustomerCard
import com.example.appclubdeportivo.view_entities.CustomerPerson
import com.itextpdf.kernel.colors.DeviceRgb
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.property.TextAlignment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class CustomerViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    private val customerDao: CustomerDao = appDatabase.customerDao()
    private val personDao: PersonDao = appDatabase.personDao()
    private val feeDao: FeeDao = appDatabase.feeDao()
    private val documentTypeDao: DocumentTypeDao = appDatabase.documentTypeDao()
    private val _customers = MutableLiveData<List<CustomerCard>>()
    val customers: LiveData<List<CustomerCard>> = _customers

    private val _selectedCustomer = MutableLiveData<CustomerPerson?>()
    val selectedCustomer: LiveData<CustomerPerson?> = _selectedCustomer

    init {
        loadCustomers()
    }

    private fun loadCustomers() {
        viewModelScope.launch {
            _customers.value = getAllCustomersFromDB()
        }
    }
    private suspend fun getAllCustomersFromDB(): List<CustomerCard> {
        return withContext(Dispatchers.IO) {
            customerDao.getAllCustomers()
        }
    }
    fun selectCustomer(customer: CustomerPerson) {
        _selectedCustomer.value = customer
    }
    fun getCustomerIdByDocumentId(documentId: String): Int {
        return runBlocking {
            withContext(Dispatchers.IO) {
                customerDao.getCustomerIdByDocumentId(documentId)
            }
        }
    }


    fun getCustomerPersonById(customerId: Int): LiveData<CustomerPerson> = liveData {
        val customer = withContext(Dispatchers.IO) {
            customerDao.getCustomerPersonById(customerId)
        }
        emit(customer)
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

    fun updateCustomer(customer: Customer, person: Person) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                personDao.updatePerson(person)
                customerDao.updateCustomer(customer)
                _customers.postValue(getAllCustomersFromDB())
            }
        }
    }

    fun logicalDeleteCustomers(customerIds: List<String>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                customerDao.logicalDeleteCustomers(customerIds)
            }
            loadCustomers()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addDefaultFee(customer: Customer, newCustomerId: Int) {
        val today = LocalDate.now()

        val formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatter2 = DateTimeFormatter.ofPattern("yyyyMM")
        val formattedDueDateToday = today.format(formatter1)
        val monthYear = today.format(formatter2)

        if(customer.membershipType == "Socio"){
            for (i in 1..5) {
                val dueDate = today.plusDays((30*i).toLong())
                val formattedNextMonth = dueDate.format(formatter1)
                val defaultFee = Fee(customerId = newCustomerId, amount = 2000, monthYear = monthYear.toInt()+i, dueDate = formattedNextMonth, status = "Pendiente")
                feeDao.insertFee(defaultFee)
            }
        } else {
            val defaultFee = Fee(customerId = newCustomerId, amount = 1000, monthYear = monthYear.toInt(), dueDate = formattedDueDateToday, status = "Pendiente")
            feeDao.insertFee(defaultFee)
        }
    }

    fun generateMembershipCard(context: Context, customer: Customer, person: Person): File {
        val file = File(context.getExternalFilesDir(null), "membership_card_${person.identityDocumentNumber}.pdf")
        val writer = PdfWriter(FileOutputStream(file))
        val pdf = PdfDocument(writer)
        val document = Document(pdf)

        val gradientColor1 = DeviceRgb(118, 171, 174)
        document.add(
            Paragraph("Carnet de Socio")
                .setFontSize(24f)
                .setFontColor(DeviceRgb(0, 0, 0))
                .setTextAlignment(TextAlignment.CENTER)
        )

        document.add(
            Paragraph("Nombre: ${person.firstName} ${person.lastName}")
                .setFontSize(16f)
                .setFontColor(DeviceRgb(0, 0, 0))
        )

        document.add(
            Paragraph("ID: ${person.identityDocumentNumber}")
                .setFontSize(16f)
                .setFontColor(DeviceRgb(0, 0, 0))
        )

        document.add(
            Paragraph("Tipo de Membresía: ${customer.membershipType}")
                .setFontSize(16f)
                .setFontColor(DeviceRgb(255, 255, 255))
                .setBackgroundColor(gradientColor1)
        )

        document.add(
            Paragraph("Fecha de Inicio: ${customer.startDate}")
                .setFontSize(14f)
                .setFontColor(DeviceRgb(128, 128, 128))
        )

        document.close()

        return file
    }
}
