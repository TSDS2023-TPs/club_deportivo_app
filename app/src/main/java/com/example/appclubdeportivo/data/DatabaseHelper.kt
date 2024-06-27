package com.example.appclubdeportivo.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appclubdeportivo.db_entities.*

@Database(
    entities = [
        Role::class, User::class, DocumentType::class, Person::class,
        Employee::class, Specialty::class, Professor::class, Doctor::class,
        AccountStatus::class, Activity::class, Customer::class, Class::class,
        Attendance::class, Routine::class, PaymentMethod::class, Fee::class,
        Invoice::class, CustomerActivity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun roleDao():RoleDao
    abstract fun userDao(): UserDao
    abstract fun documentTypeDao(): DocumentTypeDao
    abstract fun personDao(): PersonDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun specialtyDao(): SpecialtyDao
    abstract fun professorDao(): ProfessorDao
    abstract fun doctorDao(): DoctorDao
    abstract fun accountStatusDao(): AccountStatusDao
    abstract fun activityDao(): ActivityDao
    abstract fun customerDao(): CustomerDao
    abstract fun classDao(): ClassDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun routineDao(): RoutineDao
    abstract fun paymentMethodDao(): PaymentMethodDao
    abstract fun feeDao(): FeeDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun customerActivityDao(): CustomerActivityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "proyecto_club_deportivo.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun databaseExists(context: Context): Boolean {
            val dbFile = context.getDatabasePath("proyecto_club_deportivo.db")
            return dbFile.exists()
        }
    }
}