package learn.android.manageEmployee.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.network.model.EmployeeUpdateResponse
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo

/**
 * Created by Aswathy on 3/3/2020.
 */
class UpdateEmployeeVM(application: Application) : AndroidViewModel(application) {
    private val employeeDetailsRepo = EmployeeDetailsRepo(application)

    fun updateEmployeeDetails(employeeDetails: EmployeeDetails){
        viewModelScope.launch (Dispatchers.IO){
             employeeDetailsRepo.updateEmployee(employeeDetails)
        }
    }
}