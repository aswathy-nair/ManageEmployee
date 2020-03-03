package learn.android.manageEmployee.data.repository

import androidx.lifecycle.LiveData
import learn.android.manageEmployee.data.network.api.EmployeeApi
import learn.android.manageEmployee.data.network.core.NetworkCall
import learn.android.manageEmployee.data.network.core.NetworkStates
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.network.model.EmployeeResponse

/**
 * Created by Aswathy on 3/2/2020.
 */
class EmployeeDetailsRepo {
    private val employeeDetailsCall = NetworkCall<EmployeeResponse>()
    private val employeeApiService = EmployeeApi().employeeApi

    fun getAllEmployees(): LiveData<NetworkStates<EmployeeResponse>> {
        return employeeDetailsCall.makeCall(employeeApiService.getAllEmployees())
    }
}