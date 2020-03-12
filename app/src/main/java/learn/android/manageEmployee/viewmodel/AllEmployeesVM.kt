package learn.android.manageEmployee.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.repository.DataResult
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo

/**
 * Created by Aswathy on 3/2/2020.
 */
class AllEmployeesVM(application: Application) : AndroidViewModel(application) {

    private val employeeDetailsRepo = EmployeeDetailsRepo(application)
    lateinit var allEmployeesDetails : LiveData<DataResult<List<EmployeeDetails>>>
    init {
        getAllEmployee()
    }

    fun getAllEmployee() {
        allEmployeesDetails = employeeDetailsRepo.result
        viewModelScope.launch (Dispatchers.IO){
            employeeDetailsRepo.getAllEmployees()
        }
    }
}


