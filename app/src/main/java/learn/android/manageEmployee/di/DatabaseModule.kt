package learn.android.manageEmployee.di

import android.content.Context
import dagger.Module
import dagger.Provides
import learn.android.manageEmployee.data.database.EmployeeDatabase
import javax.inject.Singleton

/**
 * Created by Aswathy on 3/19/2020.
 */
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesEmployeeDatabase(context: Context): EmployeeDatabase =
        EmployeeDatabase.getEmployeeDatabase(context)

    @Provides
    fun providesEmployeeDao(employeeDatabase: EmployeeDatabase) = employeeDatabase.employeeDao()
}