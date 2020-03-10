package learn.android.manageEmployee.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import learn.android.manageEmployee.data.database.EmployeeDatabase
import learn.android.manageEmployee.data.network.core.NetworkStates
import learn.android.manageEmployee.data.network.model.EmployeeDeleteResponse
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.network.model.EmployeeResponse
import learn.android.manageEmployee.data.network.model.EmployeeUpdateResponse

/**
 * Created by Aswathy on 3/10/2020.
 */
class EmployeeDetailsRepo(context: Context) {

    private val logTag = EmployeeDetailsRepo::class.java.simpleName
    private val employeeDao = EmployeeDatabase.getEmployeeDatabase(context).employeeDao()

    var result: MutableLiveData<NetworkStates<EmployeeResponse>> = MutableLiveData()

    fun getAllEmployees(): LiveData<NetworkStates<EmployeeResponse>> {
        val employeeDetails = employeeDao.getAllEmployees()
        employeeDetails.observeForever { processDBResponse(it) }
        return result
    }

    private fun processDBResponse(employeeDetails: List<EmployeeDetails>) {
        if (employeeDetails.isNullOrEmpty()) {
            val response = EmployeeDetailsRemoteRepo().getAllEmployees()
            response.observeForever { processResponse(it) }
        } else {
            val response = EmployeeResponse()
            response.status = "Success"
            response.data = employeeDetails
            result.value = NetworkStates.success(response)
        }
    }

    private fun processResponse(response: NetworkStates<EmployeeResponse>?) {
        result.value = response
        when (response?.status) {
            NetworkStates.Status.LOADING -> {
                Log.d(logTag, "Loading")
            }
            NetworkStates.Status.SUCCESS -> {
                Log.d(logTag, "responeData: ${response.data}")
                GlobalScope.launch(Dispatchers.IO) {
                    employeeDao.addAllEmployees(response.data!!.data)
                }
            }
            NetworkStates.Status.ERROR -> {
                Log.d(logTag, "Error: ${response.resourceError}")
            }
        }
    }

    fun updateEmployee(
        id: Int,
        employeeDetails: EmployeeDetails
    ): LiveData<NetworkStates<EmployeeUpdateResponse>> {
        return MutableLiveData<NetworkStates<EmployeeUpdateResponse>>()
    }
}

interface IEmployeeDetailsRepo {
    fun getAllEmployees(): LiveData<NetworkStates<EmployeeResponse>>
    fun updateEmployee(
        id: Int,
        employeeDetails: EmployeeDetails
    ): LiveData<NetworkStates<EmployeeUpdateResponse>>

    fun addEmployee(employeeDetails: EmployeeDetails): LiveData<NetworkStates<EmployeeUpdateResponse>>
    fun deleteEmployee(id: Int): LiveData<NetworkStates<EmployeeDeleteResponse>>
}