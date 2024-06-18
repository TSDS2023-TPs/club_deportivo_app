package com.example.appclubdeportivo.data

import android.content.ContentValues
import android.content.Context
import com.example.appclubdeportivo.db_entities.Customer

class CustomerDAOImp(private val context: Context) : CustomerDAO {

    private val dbHelper = DatabaseHelper(context)

    override fun insertCustomer(customer: Customer): Long {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put("person_id", customer.personId)
            put("membership_type", customer.membershipType)
            put("activity_id", customer.activityId)
            put("account_status_id", customer.accountStatusId)
            put("fit", customer.fit)
            put("start_date", customer.startDate)
            put("end_date", customer.endDate)
        }
        return db.insert("customer", null, contentValues)
    }

    override fun updateCustomer(customer: Customer): Int {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put("person_id", customer.personId)
            put("membership_type", customer.membershipType)
            put("activity_id", customer.activityId)
            put("account_status_id", customer.accountStatusId)
            put("fit", customer.fit)
            put("start_date", customer.startDate)
            put("end_date", customer.endDate)
        }
        return db.update("customer", contentValues, "customer_id = ?", arrayOf(customer.customerId.toString()))
    }

    override fun deleteCustomer(customerId: Int): Int {
        val db = dbHelper.writableDatabase
        return db.delete("customer", "customer_id = ?", arrayOf(customerId.toString()))
    }

    override fun getAllCustomers(): List<Customer> {
        val customers = mutableListOf<Customer>()
        val db = dbHelper.readableDatabase
        val cursor = db.query("customer", null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val customer = Customer(
                    customerId = getInt(getColumnIndexOrThrow("customer_id")),
                    personId = getInt(getColumnIndexOrThrow("person_id")),
                    membershipType = getString(getColumnIndexOrThrow("membership_type")),
                    activityId = getInt(getColumnIndexOrThrow("activity_id")),
                    accountStatusId = getInt(getColumnIndexOrThrow("account_status_id")),
                    fit = getInt(getColumnIndexOrThrow("fit")) > 0,
                    startDate = getString(getColumnIndexOrThrow("start_date")),
                    endDate = getString(getColumnIndexOrThrow("end_date"))
                )
                customers.add(customer)
            }
        }
        cursor.close()
        return customers
    }
}
