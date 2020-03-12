package learn.android.manageEmployee.viewmodel

import androidx.lifecycle.ViewModel
import learn.android.manageEmployee.data.repository.EmployeeDetailsRemoteRepo

/**
 * Created by Aswathy on 3/3/2020.
 */
class DeleteEmployeeDetails : ViewModel() {
    private val employeeDetailsRepo = EmployeeDetailsRemoteRepo()

    fun deleteEmployeeDetails(id: Int) {
    }
}