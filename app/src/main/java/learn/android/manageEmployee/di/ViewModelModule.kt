package learn.android.manageEmployee.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import learn.android.manageEmployee.viewmodel.AllEmployeesVM
import learn.android.manageEmployee.viewmodel.UpdateEmployeeVM
import learn.android.manageEmployee.viewmodel.ViewModelFactory

/**
 * Created by Aswathy on 3/25/2020.
 */
@Module
class ViewModelModule {

    @Provides
    fun provideAllEmployeeVM(allEmployeesVM: AllEmployeesVM): ViewModelFactory<AllEmployeesVM> =
        ViewModelFactory(allEmployeesVM)

    @Provides
    fun provideUpdateEmployeeVM(updateEmployeeVM: UpdateEmployeeVM): ViewModelFactory<UpdateEmployeeVM> =
        ViewModelFactory(updateEmployeeVM)

    /*private fun <T : ViewModel> viewModelFactory(viewModelInstance: T) =
        object : ViewModelProvider.Factory {

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return viewModelInstance as T
            }
        }*/
}