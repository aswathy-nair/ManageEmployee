package learn.android.manageEmployee.view.activity

import android.app.Activity
import android.os.Bundle
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
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.viewmodel.UpdateEmployeeVM

class EmployeeDetailActivity : AppCompatActivity() {

    private lateinit var employeeDetails: EmployeeDetails

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

        employee_name.text = employeeDetails.employeeName
        employee_age.text = employeeDetails.employeeAge.toString()
        employee_salary.text = employeeDetails.employeeSalary.toString()
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

        employee_name_edit.setText(employeeDetails.employeeName)
        employee_age_edit.setText(employeeDetails.employeeAge.toString())
        employee_salary_edit.setText(employeeDetails.employeeSalary.toString())

        employee_details_save.setOnClickListener { saveUpdatedEmployeeDetails() }
    }

    private fun saveUpdatedEmployeeDetails() {
        val newEmployeeDetails = createNewEmployeeDetails()
        if (newEmployeeDetails == null) {
            Snackbar.make(
                employee_details_save,
                R.string.validation_error_message,
                Snackbar.LENGTH_LONG
            ).show()
        } else {
            updateNewEmployeeDetails(newEmployeeDetails)
        }

    }

    private fun updateNewEmployeeDetails(newEmployeeDetails: EmployeeDetails) {
        val updateEmployeeViewModel = ViewModelProvider(this).get(UpdateEmployeeVM::class.java)
        updateEmployeeViewModel.updateEmployeeDetails(newEmployeeDetails)
        gotoAllEmployees(newEmployeeDetails)
        finish()
    }

    private fun gotoAllEmployees(newEmployeeDetails: EmployeeDetails) {
        val intent = getIntent()
        intent.putExtra(EMPLOYEE_DETAILS, newEmployeeDetails)
        setResult(Activity.RESULT_OK, intent)
        finish()
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
                employeeDetails.profileImage
            )
            if (employeeEditedDetails != employeeDetails) return employeeEditedDetails
        }
        return null
    }
}
