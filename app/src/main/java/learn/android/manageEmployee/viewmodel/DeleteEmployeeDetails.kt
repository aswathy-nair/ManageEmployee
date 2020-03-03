package learn.android.manageEmployee.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import learn.android.manageEmployee.data.network.core.NetworkStates
import learn.android.manageEmployee.data.network.model.EmployeeDeleteResponse
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo

/**
 * Created by Aswathy on 3/3/2020.
 */
class DeleteEmployeeDetails : ViewModel() {
    private val employeeDetailsRepo = EmployeeDetailsRepo()

    fun deleteEmployeeDetails(id: Int): LiveData<NetworkStates<EmployeeDeleteResponse>> {
        return employeeDetailsRepo.deleteEmployee(id)
    }
}