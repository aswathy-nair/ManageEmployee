package learn.android.manageEmployee.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import learn.android.manageEmployee.data.network.core.NetworkStates
import learn.android.manageEmployee.data.network.model.EmployeeResponse
import learn.android.manageEmployee.data.repository.EmployeeDetailsRemoteRepo
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo

/**
 * Created by Aswathy on 3/2/2020.
 */
class AllEmployees(application: Application) : AndroidViewModel(application) {
    private val employeeDetailsRepo = EmployeeDetailsRepo(application)

    fun getAllEmployee(): LiveData<NetworkStates<EmployeeResponse>> {
        var resultData: LiveData<NetworkStates<EmployeeResponse>> = MutableLiveData()
        viewModelScope.launch {
            var differedResponse = async {
                employeeDetailsRepo.getAllEmployees()
            }
            resultData = differedResponse.await()
        }
        return resultData
    }
}


