package learn.android.manageEmployee.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import learn.android.manageEmployee.data.network.core.NetworkStates
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.network.model.EmployeeResponse
import learn.android.manageEmployee.data.network.model.EmployeeUpdateResponse
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo

/**
 * Created by Aswathy on 3/3/2020.
 */
class UpdateEmployeeDetails : ViewModel() {
    private val employeeDetailsRepo = EmployeeDetailsRepo()

    fun updateEmployeeDetails(
        id: Int,
        employeeDetails: EmployeeDetails
    ): LiveData<NetworkStates<EmployeeUpdateResponse>> {
        return employeeDetailsRepo.updateEmployee(id, employeeDetails)
    }
}