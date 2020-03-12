package learn.android.manageEmployee.data.network.api

import learn.android.manageEmployee.data.network.core.RetrofitClient
import learn.android.manageEmployee.data.network.model.*
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by Aswathy on 3/2/2020.
 */

class EmployeeApi() {
    val employeeApi = RetrofitClient.retrofit.create(GetAllEmployees::class.java)
}

interface GetAllEmployees {

    @GET("employees")
    fun getAllEmployees(): Call<BaseResponse<List<EmployeeDetails>>>

    @PUT("update/{id}")
    fun updateEmployeeDetails(@Path("id") id: Int, @Body employeeDetails: EmployeeDetails):
            Call<EmployeeUpdateResponse>

    @POST("create")
    fun addEmployee(@Body employeeDetails: EmployeeDetails): Call<EmployeeUpdateResponse>

    @DELETE("delete/{id}")
    fun deleteEmployee(@Path("id") id: Int): Call<EmployeeDeleteResponse>
}

