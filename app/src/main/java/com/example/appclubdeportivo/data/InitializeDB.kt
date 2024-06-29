package com.example.appclubdeportivo.data


import com.example.appclubdeportivo.db_entities.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun initialInserts(database: AppDatabase){

    insertInitialRoles(database)
    insertInitialUsers(database)
    insertInitialPersons(database)
    insertInitialCustomers(database)
    insertInitialEmployees(database)
    insertInitialSpecialties(database)
    insertInitialProfessors(database)
    insertInitialDoctors(database)
    insertInitialAccountStatus(database)
    insertInitialDocumentTypes(database)
    insertInitialActivities(database)
    insertInitialClasses(database)
    insertInitialAttendance(database)
    insertInitialRoutines(database)
    insertInitialPaymentMethods(database)
    insertInitialFees(database)
    insertInitialInvoices(database)
    insertInitialCustomerActivities(database)
}


fun insertInitialRoles(database: AppDatabase) {
    val roleDao = database.roleDao()

    val initialRoles = listOf(
        Role(description = "Administrador"),
        Role(description = "Médico"),
        Role(description = "Profesor")
    )

    initialRoles.forEach { role ->
        roleDao.insertRole(role)
    }
}

fun insertInitialUsers(database: AppDatabase) {
    val userDao = database.userDao()

    val initialUsers = listOf(
        User(userId = 0, username = "admin", password = "admin123", roleId = 1, isActive = true),
        User(userId = 0, username = "user1", password = "pass1", roleId = 2, isActive = true),
        User(userId = 0, username = "user2", password = "pass2", roleId = 3, isActive = true)
    )

    initialUsers.forEach { user ->
        userDao.insertUser(user)
    }
}

fun insertInitialPersons(database: AppDatabase) {
    val personDao = database.personDao()

    val initialPersons = listOf(
        Person(identityDocumentNumber = "123145", documentTypeId = 1, firstName = "Pepe", lastName = "Pepin",
       birthDate = "1970-03-12", gender = "M", telephone = "11111"),
        Person(identityDocumentNumber = "1234", documentTypeId = 1, firstName = "Demo", lastName = "Ejemplo",
            birthDate = "1980-04-12", gender = "F", telephone = "11111"),
        Person(identityDocumentNumber = "56789", documentTypeId = 1, firstName = "Juan", lastName = "Perez",
            birthDate = "1990-01-01", gender = "M", telephone = "11111"),
        Person(identityDocumentNumber = "98765", documentTypeId = 2, firstName = "Marta", lastName = "Lopez",
            birthDate = "1985-05-05", gender = "F", telephone = "11111"),
        Person(identityDocumentNumber = "11223", documentTypeId = 3, firstName = "Carlos", lastName = "Gomez",
            birthDate = "1975-10-10", gender = "M", telephone = "11111"),
        Person(identityDocumentNumber = "121213", documentTypeId = 1, firstName = "Matias", lastName = "Jerez",
            birthDate = "1985-05-06", gender = "M", telephone = "11111"),
        Person(identityDocumentNumber = "112111", documentTypeId = 4, firstName = "Mateo", lastName = "Valenzuela",
            birthDate = "1999-10-10", gender = "M", telephone = "11111")
    )

    initialPersons.forEach { person ->
        personDao.insertPerson(person)
    }
}

fun insertInitialCustomers(database: AppDatabase) {
    val customerDao = database.customerDao()

    val initialCustomers = listOf(
        Customer(personId = 5, membershipType = "Socio", accountStatusId = 1, hasPhysicalCheck = true, startDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())),
        Customer(personId = 6, membershipType = "No Socio", accountStatusId = 1, hasPhysicalCheck = true, startDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()))
    )

    initialCustomers.forEach { customer ->
        customerDao.insertCustomer(customer)
    }
}

fun insertInitialEmployees(database: AppDatabase) {
    val employeeDao = database.employeeDao()

    val initialEmployees = listOf(
        Employee(userId = 1, personId = 1, startHour = 8, endHour = 17, daysOfWeek = "Lu-Vier", hourlyRate = 2000, hireDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()), endDate = null),
        Employee(userId = 2, personId = 2, startHour = 9, endHour = 18, daysOfWeek = "Lu-Vier", hourlyRate = 1800, hireDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()), endDate = null),
        Employee(userId = 3, personId = 3, startHour = 8, endHour = 17, daysOfWeek = "Lu-Vier", hourlyRate = 2000, hireDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()), endDate = null),
        Employee(userId = 4, personId = 4, startHour = 9, endHour = 18, daysOfWeek = "Lu-Vier", hourlyRate = 1800, hireDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()), endDate = null)
    )

    initialEmployees.forEach { employee ->
        employeeDao.insertEmployee(employee)
    }
}

fun insertInitialSpecialties(database: AppDatabase) {
    val specialtyDao = database.specialtyDao()

    val initialSpecialties = listOf(
        Specialty(description = "Yoga"),
        Specialty(description = "Pilates"),
        Specialty(description = "Nutrición")
    )

    initialSpecialties.forEach { specialty ->
        specialtyDao.insertSpecialty(specialty)
    }
}

fun insertInitialProfessors(database: AppDatabase) {
    val professorDao = database.professorDao()
    val initialProfessors = listOf(
        Professor(employeeId = 3, specialtyId = 1, notes = "Instructor de yoga certificado"),
        Professor(employeeId = 2, specialtyId = 2, notes = "Instructor de pilates certificado")
    )

    initialProfessors.forEach { professor ->
        professorDao.insertProfessor(professor)
    }
}

fun insertInitialDoctors(database: AppDatabase) {
    val doctorDao = database.doctorDao()

    val initialDoctors = listOf(
        Doctor(employeeId = 1, specialtyId = 3, notes = "Nutrición"),
        Doctor(employeeId = 4, specialtyId = 3, notes = "Medicina deportiva")
    )

    initialDoctors.forEach { doctor ->
        doctorDao.insertDoctor(doctor)
    }
}

fun insertInitialAccountStatus(database: AppDatabase) {
    val accountStatusDao = database.accountStatusDao()

    val initialAccountStatus = listOf(
        AccountStatus(description = "Activa", details = "En regla"),
        AccountStatus(description = "Suspendida", details = "Suspendida por deuda")
    )

    initialAccountStatus.forEach { accountStatus ->
        accountStatusDao.insertAccountStatus(accountStatus)
    }
}

fun insertInitialActivities(database: AppDatabase) {
    val activityDao = database.activityDao()

    val initialActivities = listOf(
        Activity(type = "Nutrición", dayOfWeek = "Lunes", hourOfDay = 10, employeeId = 1, classFee = 15.0f),
        Activity(type = "Musculación", dayOfWeek = "Miércoles", hourOfDay = 18, employeeId = 2, classFee = 12.0f)
    )

    initialActivities.forEach { activity ->
        activityDao.insertActivity(activity)
    }
}
fun insertInitialClasses(database: AppDatabase) {
    val classDao = database.classDao()

    val initialClasses = listOf(
        Class(activityId = 1, dateTime = "2024-06-26 10:00:00"),
        Class(activityId = 2, dateTime = "2024-06-27 10:00:00")
    )

    initialClasses.forEach { classEntity ->
        classDao.insertClass(classEntity)
    }
}

fun insertInitialAttendance(database: AppDatabase) {
    val attendanceDao = database.attendanceDao()

    val initialAttendance = listOf(
        Attendance(classId = 1, customerId = 1),
        Attendance(classId = 2, customerId = 2)
    )

    initialAttendance.forEach { attendance ->
        attendanceDao.insertAttendance(attendance)
    }
}

fun insertInitialRoutines(database: AppDatabase) {
    val routineDao = database.routineDao()

    val initialRoutines = listOf(
        Routine(customerActivityId = 1, dayOfWeek = "Lunes", details = "Yoga", professorId = 1, expirationDate = null),
        Routine(customerActivityId = 2, dayOfWeek = "Miércoles", details = "Pilates", professorId = 2, expirationDate = null)
    )

    initialRoutines.forEach { routine ->
        routineDao.insertRoutine(routine)
    }
}

fun insertInitialPaymentMethods(database: AppDatabase) {
    val paymentMethodDao = database.paymentMethodDao()

    val initialPaymentMethods = listOf(
        PaymentMethod(description = "Tajeta", promotion = 1.0),
        PaymentMethod(description = "Efectivo", promotion = 0.8)

    )

    initialPaymentMethods.forEach { paymentMethod ->
        paymentMethodDao.insertPaymentMethod(paymentMethod)
    }
}

fun insertInitialFees(database: AppDatabase) {
    val feeDao = database.feeDao()

    val initialFees = listOf(
        Fee(customerId = 1, amount = 50, monthYear = 202401, dueDate = "2024-02-01", status = "Pendiente"),
        Fee(customerId = 2, amount = 40, monthYear = 202411, dueDate = "2024-12-01", status = "Pago")
    )

    initialFees.forEach { fee ->
        feeDao.insertFee(fee)
    }
}

fun insertInitialInvoices(database: AppDatabase) {
    val invoiceDao = database.invoiceDao()

    val initialInvoices = listOf(
        Invoice(amount = 50.0f, date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()), feeId = 1, paymentMethod = "Tarjeta"),
        Invoice(amount = 40.0f, date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()), feeId = 2, paymentMethod = "Efectivo")
    )

    initialInvoices.forEach { invoice ->
        invoiceDao.insertInvoice(invoice)
    }
}

fun insertInitialDocumentTypes(database: AppDatabase) {
    val documentTypeDao = database.documentTypeDao()

    val initialDocumentTypes = listOf(
        DocumentType(description = "DNI"),
        DocumentType(description = "Pasaporte"),
        DocumentType(description = "Licencia de conducir"),
        DocumentType(description = "DNI Extranejero")
    )

    initialDocumentTypes.forEach { documentType ->
        documentTypeDao.insertDocumentType(documentType)
    }
}
fun insertInitialCustomerActivities(database: AppDatabase) {
    val customerActivityDao = database.customerActivityDao()

    val initialCustomerActivities = listOf(
        CustomerActivity(customerId = 1, activityId = 1),
        CustomerActivity(customerId = 1, activityId = 2),
        CustomerActivity(customerId = 2, activityId = 1)

    )

    initialCustomerActivities.forEach { customerActivity ->
        customerActivityDao.insertCustomerActivity(customerActivity)
    }
}