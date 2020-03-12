package learn.android.manageEmployee.viewmodel

import androidx.lifecycle.ViewModel
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.repository.EmployeeDetailsRemoteRepo

/**
 * Created by Aswathy on 3/3/2020.
 */
class AddEmployeeDetails : ViewModel() {
    private val employeeDetailsRepo = EmployeeDetailsRemoteRepo()

    fun addEmployeeDetails(employeeDetails: EmployeeDetails){
    }
}