package com.example.appclubdeportivo.view_models

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.*
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.data.FeeDao
import com.example.appclubdeportivo.data.InvoiceDao
import com.example.appclubdeportivo.db_entities.Invoice
import com.example.appclubdeportivo.view_entities.Payment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream

class PaymentViewModel(private val appDatabase: AppDatabase) : ViewModel() {

    private val feeDao: FeeDao = appDatabase.feeDao()
    private val invoiceDao: InvoiceDao = appDatabase.invoiceDao()

    private val _paymentCards = MutableLiveData<List<Payment>>()
    val paymentCards: LiveData<List<Payment>> = _paymentCards

    private val _selectedPayments = MutableStateFlow<Set<Int>>(emptySet())
    val selectedPayments: StateFlow<Set<Int>> = _selectedPayments

    private val _totalAmount = MutableStateFlow(0.0)
    val totalAmount: StateFlow<Double> = _totalAmount

    private val _pdfUri = MutableStateFlow<Uri?>(null)
    val pdfUri: StateFlow<Uri?> = _pdfUri

    init {
        loadPaymentCards()
    }
    fun togglePaymentSelection(payment: Payment) {
        _selectedPayments.value = _selectedPayments.value.toMutableSet().apply {
            if (contains(payment.feeId)) {
                remove(payment.feeId)
            } else {
                add(payment.feeId)
            }
        }
        updateTotalAmount()
    }

    private fun updateTotalAmount() {
        viewModelScope.launch {
            val total = _paymentCards.value?.filter { it.feeId in _selectedPayments.value }
                ?.sumOf { it.amount.replace("$", "").toDouble() } ?: 0.0
            _totalAmount.value = total
        }
    }

    private fun loadPaymentCards() {
        viewModelScope.launch {
            _paymentCards.value = getPendingPaymentsFromDB()
        }
    }

    private suspend fun getPendingPaymentsFromDB(): List<Payment> {
        return withContext(Dispatchers.IO) {
            feeDao.getPendingPayments()
        }
    }


    fun registerPayment(context: Context, emitTicket: Boolean) {
        viewModelScope.launch {
            val selectedPaymentsList = _paymentCards.value?.filter { it.feeId in _selectedPayments.value } ?: emptyList()

            withContext(Dispatchers.IO) {
                selectedPaymentsList.forEach { payment ->
                    val invoiceId = insertInvoice(payment)
                    feeDao.updateFeeFromPayment(payment.feeId)

                    if (emitTicket) {
                        val pdfContent = generatePDFContent(payment, invoiceId)
                        _pdfUri.value = Uri.parse("content://com.example.appclubdeportivo.fileprovider/temp/Factura_${payment.customerId}_${System.currentTimeMillis()}.pdf")
                        context.contentResolver.openOutputStream(_pdfUri.value!!)?.use { outputStream ->
                            outputStream.write(pdfContent)
                        }
                    }
                }
            }

            _selectedPayments.value = emptySet()
            updateTotalAmount()
            loadPaymentCards()
        }
    }
    private fun insertInvoice(payment: Payment): Long {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val paymentDate = formatter.format(Date())

        return invoiceDao.insertInvoice(Invoice(
            amount = payment.amount.replace("$", "").toFloat(),
            date = paymentDate,
            feeId = payment.feeId,
            paymentMethod = payment.paymentMethod
        ))
    }
    private fun generatePDFContent(payment: Payment, invoiceId: Long): ByteArray {
        val outputStream = ByteArrayOutputStream()
        PdfWriter(outputStream).use { writer ->
            PdfDocument(writer).use { pdf ->
                Document(pdf).use { document ->
                    document.add(Paragraph("Factura"))
                    document.add(Paragraph("ID de Factura: $invoiceId"))
                    document.add(Paragraph("Cliente: ${payment.customerId}"))
                    document.add(Paragraph("Mes de Cuota: ${payment.monthYear}"))
                    document.add(Paragraph("Fecha de Pago: ${SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())}"))
                    document.add(Paragraph("Monto: ${payment.amount}"))
                }
            }
        }
        return outputStream.toByteArray()
    }
}
