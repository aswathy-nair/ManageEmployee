package learn.android.manageEmployee.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import learn.android.manageEmployee.data.database.EmployeeDatabase
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.network.model.EmployeeUpdateResponse
import okhttp3.Dispatcher

/**
 * Created by Aswathy on 3/10/2020.
 */
class EmployeeDetailsRepo(context: Context) {

    private val employeeDao = EmployeeDatabase.getEmployeeDatabase(context).employeeDao()
    private val employeeRemoteRepo = EmployeeDetailsRemoteRepo()

    var result: MutableLiveData<DataResult<List<EmployeeDetails>>> = MutableLiveData()

    suspend fun getAllEmployees() {
        result.postValue (DataResult.loading())
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
        result.postValue (DataResult.loading())
        employeeDao.updateEmployee(employeeDetails)
    }
}