package learn.android.manageEmployee.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import learn.android.manageEmployee.data.network.model.EmployeeDetails

/**
 * Created by Aswathy on 3/9/2020.
 */
@Dao
interface EmployeeDao {

    @Insert(onConflict = REPLACE)
    suspend fun addAllEmployees(allEmployees: List<EmployeeDetails>)

    @Insert(onConflict = REPLACE)
    fun addEmployee(employee: EmployeeDetails)

    @Update
    suspend fun updateEmployee(employee: EmployeeDetails)

    @Delete
    fun deleteEmployee(employee: List<EmployeeDetails>)

    @Query("SELECT * from employee")
    suspend fun getAllEmployees(): List<EmployeeDetails>

}