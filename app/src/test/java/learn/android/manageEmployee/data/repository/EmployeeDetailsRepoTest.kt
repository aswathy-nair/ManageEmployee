package learn.android.manageEmployee.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import learn.android.manageEmployee.data.database.EmployeeDao
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.InjectMocks

/**
 * Created by Aswathy on 4/10/2020.
 */
class EmployeeDetailsRepoTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    lateinit var employeeDetailsRepo: EmployeeDetailsRepo

    @MockK
    lateinit var employeeDao: EmployeeDao

    @MockK
    lateinit var employeeRemoteRepo: EmployeeDetailsRemoteRepo


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        employeeDetailsRepo = EmployeeDetailsRepo(employeeDao, employeeRemoteRepo)

    }

    @Test
    fun getAllEmployeesWhenEmployeesInDatabase() {
        val employeeDetails = EmployeeDetails(1, "Test Employee", 2000, 30,"")
        val employeeDetailsList = mutableListOf(employeeDetails)

        coEvery { employeeDao.getAllEmployees() } returns employeeDetailsList
        runBlocking { employeeDetailsRepo.getAllEmployees() }
        val result = employeeDetailsRepo.result.value
        assertEquals(DataResult.Status.SUCCESS, result?.status)
    }

    @Test
    fun getAllEmployeesWhenEmployeesNotInDatabase() {
        val employeeDetails = EmployeeDetails(1, "Test Employee", 2000, 30,"")
        val employeeDetailsList = mutableListOf<EmployeeDetails>()
        val responseList = mutableListOf(employeeDetails)

        coEvery { employeeDao.getAllEmployees() } returns employeeDetailsList
        every { employeeRemoteRepo.getAllEmployees() } returns DataResult.success(responseList)
        coEvery {  employeeDao.addAllEmployees(any()) } just Runs

        runBlocking { employeeDetailsRepo.getAllEmployees() }

        verify { employeeRemoteRepo.getAllEmployees() }
        coVerify {  employeeDao.addAllEmployees(responseList) }
        val result = employeeDetailsRepo.result.value
        assertEquals(DataResult.Status.SUCCESS, result?.status)
    }
}