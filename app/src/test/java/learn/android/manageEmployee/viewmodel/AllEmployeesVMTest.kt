package learn.android.manageEmployee.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import learn.android.manageEmployee.data.repository.DataResult
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Aswathy on 3/19/2020.
 */

@RunWith(MockitoJUnitRunner::class)
class AllEmployeesVMTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var employeeDetailsRepo: EmployeeDetailsRepo


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllEmployee_fetchResult(){
        val allEmployeesVM = createEmployeeVM()
        verify(allEmployeesVM, times(1)).getAllEmployee()
        val result = allEmployeesVM.allEmployeesDetails.value
        Assert.assertEquals(DataResult.Status.LOADING, result?.status)
    }

    private fun createEmployeeVM(): AllEmployeesVM = AllEmployeesVM()
}