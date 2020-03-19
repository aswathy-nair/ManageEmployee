package learn.android.manageEmployee.viewmodel

import androidx.lifecycle.ViewModel
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo

/**
 * Created by Aswathy on 3/3/2020.
 */
class DeleteEmployeeDetails : ViewModel() {
    lateinit var employeeDetailsRepo: EmployeeDetailsRepo
}