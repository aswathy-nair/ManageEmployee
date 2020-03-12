package learn.android.manageEmployee.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import learn.android.manageEmployee.data.network.model.EmployeeDetails

/**
 * Created by Aswathy on 3/10/2020.
 */
@Database(entities = [EmployeeDetails::class], version = 2, exportSchema = false)
abstract class EmployeeDatabase : RoomDatabase() {


    companion object {

        private const val DATABASE_NAME = "employee_db"

        @Volatile
        private var INSTANCE: EmployeeDatabase? = null

        fun getEmployeeDatabase(context: Context): EmployeeDatabase {
            return INSTANCE ?: synchronized(this) {
                val dbInstance =
                    Room.databaseBuilder(context, EmployeeDatabase::class.java, DATABASE_NAME)
                        .build()
                INSTANCE = dbInstance
                dbInstance
            }
        }
    }

    abstract fun employeeDao(): EmployeeDao

}