package learn.android.manageEmployee.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import learn.android.manageEmployee.ManageEmployeeApplication
import learn.android.manageEmployee.data.database.EmployeeDatabase
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Aswathy on 3/18/2020.
 */

@Module
class ApplicationModule(private val application: ManageEmployeeApplication) {

    @Provides
    fun provideApplicationContext(): Context = application.applicationContext
}