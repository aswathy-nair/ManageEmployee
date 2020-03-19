package learn.android.manageEmployee.data.repository

import androidx.lifecycle.MutableLiveData
import learn.android.manageEmployee.data.database.EmployeeDao
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import javax.inject.Inject

/**
 * Created by Aswathy on 3/10/2020.
 */
class EmployeeDetailsRepo @Inject constructor(
    private val employeeDao: EmployeeDao,
    private val employeeRemoteRepo: EmployeeDetailsRemoteRepo
) {

    var result: MutableLiveData<DataResult<List<EmployeeDetails>>> = MutableLiveData()

    suspend fun getAllEmployees() {
        result.postValue(DataResult.loading())
        val employeeDetails = employeeDao.getAllEmployees()
        if (employeeDetails.isNullOrEmpty()) {
            val response = employeeRemoteRepo.getAllEmployees()
            result.postValue(response)
            if (response.status == DataResult.Status.SUCCESS) {
                employeeDao.addAllEmployees(response.data!!)
            }
        } else {
            result.postValue(DataResult.success(employeeDetails))
        }
    }

    suspend fun updateEmployee(employeeDetails: EmployeeDetails) {
        result.postValue(DataResult.loading())
        employeeDao.updateEmployee(employeeDetails)
    }
}