package learn.android.manageEmployee.data.repository

import learn.android.manageEmployee.data.network.api.EmployeeApi
import learn.android.manageEmployee.data.network.core.NetworkCall
import learn.android.manageEmployee.data.network.model.BaseResponse
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import javax.inject.Inject

/**
 * Created by Aswathy on 3/2/2020.
 */
class EmployeeDetailsRemoteRepo @Inject constructor(private val employeeApi: EmployeeApi) {
    private val employeeDetailsCall = NetworkCall<BaseResponse<List<EmployeeDetails>>>()

    fun getAllEmployees(): DataResult<List<EmployeeDetails>> {
        return employeeDetailsCall.doNetworkCall( employeeApi.getAllEmployees.getAllEmployees())
    }
}