package learn.android.manageEmployee.di

import dagger.Component
import learn.android.manageEmployee.view.activity.AllEmployeesActivity
import learn.android.manageEmployee.view.activity.EmployeeDetailActivity
import learn.android.manageEmployee.viewmodel.AllEmployeesVM
import learn.android.manageEmployee.viewmodel.UpdateEmployeeVM
import javax.inject.Singleton

/**
 * Created by Aswathy on 3/13/2020.
 */
@Singleton
@Component(
    modules = [NetworkModule::class, ApplicationModule::class, DatabaseModule::class,
        ViewModelModule::class]
)
interface ApplicationComponent {
    fun inject(allEmployeesActivity: AllEmployeesActivity)
    fun inject(employeeDetailActivity: EmployeeDetailActivity)
}