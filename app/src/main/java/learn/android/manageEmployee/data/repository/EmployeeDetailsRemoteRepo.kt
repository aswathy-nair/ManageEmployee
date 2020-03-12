package learn.android.manageEmployee.data.repository

import learn.android.manageEmployee.data.network.api.EmployeeApi
import learn.android.manageEmployee.data.network.core.NetworkCall
import learn.android.manageEmployee.data.network.model.BaseResponse
import learn.android.manageEmployee.data.network.model.EmployeeDeleteResponse
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.network.model.EmployeeUpdateResponse

/**
 * Created by Aswathy on 3/2/2020.
 */
class EmployeeDetailsRemoteRepo {
    private val employeeDetailsCall = NetworkCall<BaseResponse<List<EmployeeDetails>>>()
    private val employeeUpdateCall = NetworkCall<EmployeeUpdateResponse>()
    private val employeeDeleteCall = NetworkCall<EmployeeDeleteResponse>()

    private val employeeApiService = EmployeeApi().employeeApi

    fun getAllEmployees(): DataResult<List<EmployeeDetails>> {
        return employeeDetailsCall.doNetworkCall(employeeApiService.getAllEmployees())
    }

    fun updateEmployee(
        id: Int,
        employeeDetails: EmployeeDetails
    ) {

    }

    fun addEmployee(employeeDetails: EmployeeDetails){
    }

    fun deleteEmployee(id: Int){
    }
}