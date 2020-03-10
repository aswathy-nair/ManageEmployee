package learn.android.manageEmployee.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import learn.android.manageEmployee.data.network.core.NetworkStates
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.network.model.EmployeeUpdateResponse
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo

/**
 * Created by Aswathy on 3/3/2020.
 */
class UpdateEmployeeDetails(application: Application) : AndroidViewModel(application) {
    private val employeeDetailsRepo = EmployeeDetailsRepo(application)

    fun updateEmployeeDetails(
        id: Int,
        employeeDetails: EmployeeDetails
    ): LiveData<NetworkStates<EmployeeUpdateResponse>> {
        return employeeDetailsRepo.updateEmployee(id, employeeDetails)
    }
}