package learn.android.manageEmployee.data.network.api

import learn.android.manageEmployee.data.network.model.BaseResponse
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.*
import javax.inject.Inject

/**
 * Created by Aswathy on 3/2/2020.
 */

class EmployeeApi @Inject constructor(retrofit: Retrofit) {

    val getAllEmployees: GetAllEmployees = retrofit.create(GetAllEmployees::class.java)
}

interface GetAllEmployees {

    @GET("employees")
    fun getAllEmployees(): Call<BaseResponse<List<EmployeeDetails>>>

    @PUT("update/{id}")
    fun updateEmployeeDetails(@Path("id") id: Int, @Body employeeDetails: EmployeeDetails):
            Call<BaseResponse<List<EmployeeDetails>>>

    @POST("create")
    fun addEmployee(@Body employeeDetails: EmployeeDetails): Call<BaseResponse<List<EmployeeDetails>>>

    @DELETE("delete/{id}")
    fun deleteEmployee(@Path("id") id: Int): Call<BaseResponse<List<EmployeeDetails>>>
}

