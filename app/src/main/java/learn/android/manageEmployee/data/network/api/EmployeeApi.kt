package learn.android.manageEmployee.data.network.api

import learn.android.manageEmployee.data.network.core.HttpClient
import learn.android.manageEmployee.data.network.model.EmployeeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT

/**
 * Created by Aswathy on 3/2/2020.
 */

class EmployeeApi() {
    val employeeApi = HttpClient.retrofit.create(GetAllEmployees::class.java)
}

interface GetAllEmployees {

    @GET("employees")
    fun getAllEmployees(): Call<EmployeeResponse>

    @PUT("update/{id}")
    fun updateEmployeeDetails(id: Int)
}

