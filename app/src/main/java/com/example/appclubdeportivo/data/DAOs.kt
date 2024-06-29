package com.example.appclubdeportivo.data

import androidx.room.*
import com.example.appclubdeportivo.db_entities.*
import com.example.appclubdeportivo.view_entities.CustomerCard
import com.example.appclubdeportivo.view_entities.Payment
import com.example.appclubdeportivo.view_entities.CustomerPerson

@Dao
interface RoleDao {
    @Query("SELECT * FROM role")
    fun getAllRoles(): List<Role>

    @Insert
    fun insertRole(role: Role): Long

    @Update
    fun updateRole(role: Role): Int

    @Delete
    fun deleteRole(role: Role): Int
}

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User): Int

    @Delete
    fun deleteUser(user: User): Int

    @Query("SELECT * FROM user WHERE username = :username AND password = :password AND isActive = 1")
    fun login(username: String, password: String): User?

    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?
}

@Dao
interface DocumentTypeDao {
    @Query("SELECT * FROM documenttype")
    fun getAllDocumentTypes(): List<DocumentType>

    @Insert
    fun insertDocumentType(documentType: DocumentType): Long

    @Update
    fun updateDocumentType(documentType: DocumentType): Int

    @Delete
    fun deleteDocumentType(documentType: DocumentType): Int

    @Query("SELECT * FROM documenttype WHERE documentTypeId = :documentTypeId")
    fun getDocumentTypeById(documentTypeId: Int): DocumentType
}

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAllPersons(): List<Person>

    @Query("SELECT * FROM person WHERE personId = :personId")
    fun getPersonById(personId: Int): Person
    @Insert
    fun insertPerson(person: Person): Long

    @Update
    fun updatePerson(person: Person): Int

    @Delete
    fun deletePerson(person: Person): Int
}

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employee")
    fun getAllEmployees(): List<Employee>

    @Insert
    fun insertEmployee(employee: Employee): Long

    @Update
    fun updateEmployee(employee: Employee): Int

    @Delete
    fun deleteEmployee(employee: Employee): Int
}

@Dao
interface SpecialtyDao {
    @Query("SELECT * FROM specialty")
    fun getAllSpecialties(): List<Specialty>

    @Insert
    fun insertSpecialty(specialty: Specialty): Long

    @Update
    fun updateSpecialty(specialty: Specialty): Int

    @Delete
    fun deleteSpecialty(specialty: Specialty): Int
}

@Dao
interface ProfessorDao {
    @Query("SELECT * FROM professor")
    fun getAllProfessors(): List<Professor>

    @Insert
    fun insertProfessor(professor: Professor): Long

    @Update
    fun updateProfessor(professor: Professor): Int

    @Delete
    fun deleteProfessor(professor: Professor): Int
}

@Dao
interface DoctorDao {
    @Query("SELECT * FROM doctor")
    fun getAllDoctors(): List<Doctor>

    @Insert
    fun insertDoctor(doctor: Doctor): Long

    @Update
    fun updateDoctor(doctor: Doctor): Int

    @Delete
    fun deleteDoctor(doctor: Doctor): Int
}

@Dao
interface AccountStatusDao {
    @Query("SELECT * FROM accountstatus")
    fun getAllAccountStatuses(): List<AccountStatus>

    @Insert
    fun insertAccountStatus(accountStatus: AccountStatus): Long

    @Update
    fun updateAccountStatus(accountStatus: AccountStatus): Int

    @Delete
    fun deleteAccountStatus(accountStatus: AccountStatus): Int
}

@Dao
interface ActivityDao {
    @Query("SELECT * FROM activity")
    fun getAllActivities(): List<Activity>

    @Insert
    fun insertActivity(activity: Activity): Long

    @Update
    fun updateActivity(activity: Activity): Int

    @Delete
    fun deleteActivity(activity: Activity): Int
}

@Dao
interface CustomerDao {

    @Query("SELECT c.customerId as id, p.firstName ||  ' ' ||  p.lastName as name, f.dueDate as expiredDate, f.amount as amount," +
            " c.membershipType, f.status as feeStatus " +
            " FROM customer c " +
            "INNER JOIN person p ON c.personId = p.personId " +
            "INNER JOIN fee f ON f.customerId = c.customerId" +
            " WHERE c.accountStatusId = 1")
    fun getAllCustomers(): List<CustomerCard>

    @Query("SELECT c.customerId as id, p.firstName ||  ' ' ||  p.lastName as name, f.dueDate as expiredDate, f.amount as amount," +
            " c.membershipType, f.status as feeStatus " +
            " FROM customer c " +
            "INNER JOIN person p ON c.personId = p.personId " +
            "INNER JOIN fee f ON f.customerId = c.customerId" +
            " WHERE c.accountStatusId = 1 and f.status = 'Pendiente'")
    fun getCustomersWithUnpaidFees(): List<CustomerCard>
    @Insert
    fun insertCustomer(customer: Customer): Long

    @Update
    fun updateCustomer(customer: Customer): Int

    @Delete
    fun deleteCustomer(customer: Customer): Int

    @Query("SELECT c.customerId as id" +
            " FROM customer c " +
            "INNER JOIN person p ON c.personId = p.personId " +
            "WHERE p.personId = (SELECT personId FROM person WHERE identityDocumentNumber = :documentId)")
    fun getCustomerIdByDocumentId(documentId: String): Int

    @Query("SELECT c.customerId, p.personId, p.firstName , p.lastName, p.documentTypeId as documentType, p.identityDocumentNumber as documentId," +
            "p.birthDate, p.telephone, p.gender, p.heightCm as height, p.weightKg as weight, c.hasPhysicalCheck as physicalCheck, c.membershipType" +
            " FROM customer c " +
            "INNER JOIN person p ON c.personId = p.personId " +
            " WHERE c.accountStatusId = 1 and c.customerId = :customerId")
    fun getCustomerPersonById(customerId: Int): CustomerPerson

    @Query("SELECT *" +
            " FROM customer c " +
            " WHERE c.accountStatusId = 1 and c.customerId = :customerId")
    fun getCustomerById(customerId: Int): Customer
    @Query("UPDATE customer SET accountStatusId = 0 WHERE customerId IN (:customerIds)")
    fun logicalDeleteCustomers(customerIds: List<String>)
}

@Dao
interface ClassDao {
    @Query("SELECT * FROM class")
    fun getAllClasses(): List<Class>

    @Insert
    fun insertClass(class1: Class): Long

    @Update
    fun updateClass(class1: Class): Int

    @Delete
    fun deleteClass(class1: Class): Int
}

@Dao
interface AttendanceDao {
    @Query("SELECT * FROM attendance")
    fun getAllAttendances(): List<Attendance>

    @Insert
    fun insertAttendance(attendance: Attendance): Long

    @Delete
    fun deleteAttendance(attendance: Attendance): Int
}

@Dao
interface RoutineDao {
    @Query("SELECT * FROM routine")
    fun getAllRoutines(): List<Routine>

    @Insert
    fun insertRoutine(routine: Routine): Long

    @Update
    fun updateRoutine(routine: Routine): Int

    @Delete
    fun deleteRoutine(routine: Routine): Int
}

@Dao
interface PaymentMethodDao {
    @Query("SELECT * FROM paymentmethod")
    fun getAllPaymentMethods(): List<PaymentMethod>

    @Insert
    fun insertPaymentMethod(paymentMethod: PaymentMethod): Long

    @Update
    fun updatePaymentMethod(paymentMethod: PaymentMethod): Int

    @Delete
    fun deletePaymentMethod(paymentMethod: PaymentMethod): Int
}

@Dao
interface FeeDao {
    @Query("SELECT * FROM fee")
    fun getAllFees(): List<Fee>

    @Query("select c.customerId, monthYear, dueDate, null, amount, feeId, '' as paymentMethod from (select * from Customer where accountStatusId = 1) c\n" +
            "inner join (select * from Fee where status = \"Pendiente\") f on\n" +
            "c.customerId = f.customerId\n" +
            "where dueDate < datetime('Now', 'LocalTime', '+6 Month');")
    fun getPendingPayments(): List<Payment>

    @Query("update fee set status = 'Pagado' where feeId = :feeId")
    fun updateFeeFromPayment(feeId: Int): Int
    @Insert
    fun insertFee(fee: Fee): Long

    @Update
    fun updateFee(fee: Fee): Int

    @Delete
    fun deleteFee(fee: Fee): Int
}

@Dao
interface InvoiceDao {
    @Query("SELECT * FROM invoice")
    fun getAllInvoices(): List<Invoice>

    @Insert
    fun insertInvoice(invoice: Invoice): Long

    @Update
    fun updateInvoice(invoice: Invoice): Int

    @Delete
    fun deleteInvoice(invoice: Invoice): Int
}
@Dao
interface CustomerActivityDao {
    @Query("SELECT * FROM customeractivity")
    fun getAllCustomerActivities(): List<CustomerActivity>

    @Insert
    fun insertCustomerActivity(customerActivity: CustomerActivity): Long

    @Update
    fun updateCustomerActivity(customerActivity: CustomerActivity): Int

    @Delete
    fun deleteCustomerActivity(customerActivity: CustomerActivity): Int
}