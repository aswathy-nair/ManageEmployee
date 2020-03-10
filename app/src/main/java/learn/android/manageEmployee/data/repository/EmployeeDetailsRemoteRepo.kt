package learn.android.manageEmployee.data.repository

import androidx.lifecycle.LiveData
import learn.android.manageEmployee.data.network.api.EmployeeApi
import learn.android.manageEmployee.data.network.core.NetworkCall
import learn.android.manageEmployee.data.network.core.NetworkStates
import learn.android.manageEmployee.data.network.model.EmployeeDeleteResponse
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.network.model.EmployeeResponse
import learn.android.manageEmployee.data.network.model.EmployeeUpdateResponse

/**
 * Created by Aswathy on 3/2/2020.
 */
class EmployeeDetailsRemoteRepo :IEmployeeDetailsRepo {
    private val employeeDetailsCall = NetworkCall<EmployeeResponse>()
    private val employeeUpdateCall = NetworkCall<EmployeeUpdateResponse>()
    private val employeeDeleteCall = NetworkCall<EmployeeDeleteResponse>()

    private val employeeApiService = EmployeeApi().employeeApi

    override fun getAllEmployees(): LiveData<NetworkStates<EmployeeResponse>> {
        return employeeDetailsCall.makeCall(employeeApiService.getAllEmployees())
    }

    override fun updateEmployee(
        id: Int,
        employeeDetails: EmployeeDetails
    ): LiveData<NetworkStates<EmployeeUpdateResponse>> {
        return employeeUpdateCall.makeCall(
            employeeApiService.updateEmployeeDetails(
                id,
                employeeDetails
            )
        )
    }

    override fun addEmployee(employeeDetails: EmployeeDetails): LiveData<NetworkStates<EmployeeUpdateResponse>>{
        return employeeUpdateCall.makeCall(employeeApiService.addEmployee(employeeDetails))
    }

    override fun deleteEmployee(id: Int): LiveData<NetworkStates<EmployeeDeleteResponse>>{
        return employeeDeleteCall.makeCall(employeeApiService.deleteEmployee(id))
    }
}