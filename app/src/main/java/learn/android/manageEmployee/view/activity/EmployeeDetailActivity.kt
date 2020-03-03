package learn.android.manageEmployee.view.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_employee_detail.*
import kotlinx.android.synthetic.main.employee_detail.*
import kotlinx.android.synthetic.main.employee_detail_edit.*
import kotlinx.android.synthetic.main.employee_item.employee_name
import learn.android.manageEmployee.R
import learn.android.manageEmployee.data.network.core.NetworkStates
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.network.model.EmployeeResponse
import learn.android.manageEmployee.data.network.model.EmployeeUpdateResponse
import learn.android.manageEmployee.viewmodel.AllEmployees
import learn.android.manageEmployee.viewmodel.UpdateEmployeeDetails

class EmployeeDetailActivity : AppCompatActivity() {

    private lateinit var employeeDetails: EmployeeDetails
    private val logTag = EmployeeDetailActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_detail)
        setSupportActionBar(toolbar)
        val receivedEmployeeData = intent.getParcelableExtra<EmployeeDetails>(EMPLOYEE_DETAILS)
        receivedEmployeeData?.let { employeeDetails = receivedEmployeeData }

        updateEmployeeDetails()

    }

    private fun updateEmployeeDetails() {
        if (!::employeeDetails.isInitialized) return

        employee_name.text = employeeDetails.employee_name
        employee_age.text = employeeDetails.employee_age.toString()
        employee_salary.text = employeeDetails.employee_salary.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_employee_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                switchToEditMode()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun switchToEditMode() {
        employee_detail.visibility = View.GONE
        employee_detail_edit.visibility = View.VISIBLE
        updateEmployeeEditDetails()
    }

    private fun updateEmployeeEditDetails() {
        if (!::employeeDetails.isInitialized) return

        employee_name_edit.setText(employeeDetails.employee_name)
        employee_age_edit.setText(employeeDetails.employee_age.toString())
        employee_salary_edit.setText(employeeDetails.employee_salary.toString())

        employee_details_save.setOnClickListener { saveUpdatedEmployeeDetails() }
    }

    private fun saveUpdatedEmployeeDetails() {
        val newEmployeeDetails = createNewEmployeeDetails()
        if (newEmployeeDetails == null) {
            Snackbar.make(
                employee_details_save,
                R.string.validation_error_message,
                Snackbar.LENGTH_LONG
            )
                .setAction("Action", null).show()
        } else {
            updateNewEmployeeDetails(newEmployeeDetails)
        }

    }

    private fun updateNewEmployeeDetails(newEmployeeDetails: EmployeeDetails) {
        val updateEmployeeViewModel = ViewModelProvider(this).get(UpdateEmployeeDetails::class.java)
        updateEmployeeViewModel.updateEmployeeDetails(newEmployeeDetails.id, newEmployeeDetails)
            .observeForever { processResponse(it) }
    }

    private fun createNewEmployeeDetails(): EmployeeDetails? {
        val employeeName = employee_name_edit.text.toString()
        val employeeAge = employee_age_edit.text.toString().toIntOrNull()
        val employeeSalary = employee_salary_edit.text.toString().toLongOrNull()

        if (!employeeName.isBlank() && employeeAge != null && employeeSalary != null) {
            val employeeEditedDetails = EmployeeDetails(
                employeeDetails.id,
                employeeName,
                employeeSalary,
                employeeAge,
                employeeDetails.profile_image
            )
            if (employeeEditedDetails != employeeDetails) return employeeEditedDetails
        }
        return null
    }
    private fun processResponse(response: NetworkStates<EmployeeUpdateResponse>?) {
        when (response?.status) {
            NetworkStates.Status.LOADING -> {
                Log.d(logTag, "Loading")
            }
            NetworkStates.Status.SUCCESS -> {
                Log.d(logTag, "responeData: ${response.data}")
                finish()
            }
            NetworkStates.Status.ERROR -> {
                Log.d(logTag, "Error: ${response.resourceError}")
            }
        }
    }
}
