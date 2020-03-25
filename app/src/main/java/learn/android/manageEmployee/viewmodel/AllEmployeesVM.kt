package learn.android.manageEmployee.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.repository.DataResult
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo
import javax.inject.Inject

/**
 * Created by Aswathy on 3/2/2020.
 */
class AllEmployeesVM @Inject constructor(private val employeeDetailsRepo: EmployeeDetailsRepo) :
    ViewModel() {

    lateinit var allEmployeesDetails: LiveData<DataResult<List<EmployeeDetails>>>

    init {
        getAllEmployee()
    }

    fun getAllEmployee() {
        allEmployeesDetails = employeeDetailsRepo.result
        viewModelScope.launch(Dispatchers.IO) {
            employeeDetailsRepo.getAllEmployees()
        }

    }
}


