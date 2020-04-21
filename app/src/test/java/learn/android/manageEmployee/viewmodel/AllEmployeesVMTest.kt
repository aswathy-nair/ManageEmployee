package learn.android.manageEmployee.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import learn.android.manageEmployee.data.repository.DataResult
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


/**
 * Created by Aswathy on 3/19/2020.
 */

@RunWith(JUnit4::class)
class AllEmployeesVMTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var employeeDetailsRepo: EmployeeDetailsRepo


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }


    @Test
     fun getAllEmployee_fetchResult(){
        every { employeeDetailsRepo.result} returns MutableLiveData(DataResult.loading())
        coEvery { employeeDetailsRepo.getAllEmployees() } returns Unit

        val allEmployeesVM = createEmployeeVM()

        coVerify {employeeDetailsRepo.getAllEmployees()}
        confirmVerified()

        val result = allEmployeesVM.allEmployeesDetails.value
        Assert.assertEquals(DataResult.Status.LOADING, result?.status)
    }

    private fun createEmployeeVM(): AllEmployeesVM = AllEmployeesVM(employeeDetailsRepo)
}