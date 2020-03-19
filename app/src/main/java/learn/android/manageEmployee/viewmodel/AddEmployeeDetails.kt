package learn.android.manageEmployee.viewmodel

import androidx.lifecycle.ViewModel
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.repository.EmployeeDetailsRemoteRepo
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo

/**
 * Created by Aswathy on 3/3/2020.
 */
class AddEmployeeDetails : ViewModel() {

    lateinit var employeeDetailsRepo: EmployeeDetailsRepo

    fun addEmployeeDetails(employeeDetails: EmployeeDetails){
    }
}