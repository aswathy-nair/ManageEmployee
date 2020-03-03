package learn.android.manageEmployee.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import learn.android.manageEmployee.data.network.core.NetworkStates
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.network.model.EmployeeResponse
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo

/**
 * Created by Aswathy on 3/2/2020.
 */
class AllEmployees : ViewModel() {
    private val employeeDetailsRepo = EmployeeDetailsRepo()

    fun getAllEmployee(): LiveData<NetworkStates<EmployeeResponse>> {
        return employeeDetailsRepo.getAllEmployees()
    }
}


