package learn.android.manageEmployee.viewmodel

import androidx.lifecycle.MutableLiveData
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.repository.DataResult
import learn.android.manageEmployee.data.repository.EmployeeDetailsRepo
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by Aswathy on 3/25/2020.
 */
class UpdateEmployeeVMTest {

    @MockK
    lateinit var employeeDetailsRepo: EmployeeDetailsRepo

    @InjectMockKs
    lateinit var updateEmployeeVM: UpdateEmployeeVM

    @MockK
    lateinit var employeeDetails: EmployeeDetails

    @MockK
    lateinit var employeeDetails1: EmployeeDetails


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun updateEmployeeDetails() {

        coEvery { employeeDetailsRepo.updateEmployee(any()) } returns Unit

        updateEmployeeVM.updateEmployeeDetails(employeeDetails)

        coVerify {employeeDetailsRepo.updateEmployee(employeeDetails)}
        confirmVerified()
    }
}