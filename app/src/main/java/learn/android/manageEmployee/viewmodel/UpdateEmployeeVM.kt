package learn.android.manageEmployee.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import learn.android.manageEmployee.ManageEmployeeApplication
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo
import javax.inject.Inject

/**
 * Created by Aswathy on 3/3/2020.
 */
class UpdateEmployeeVM @Inject constructor(private val employeeDetailsRepo: EmployeeDetailsRepo) :
    ViewModel() {

    fun updateEmployeeDetails(employeeDetails: EmployeeDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            employeeDetailsRepo.updateEmployee(employeeDetails)
        }
    }
}