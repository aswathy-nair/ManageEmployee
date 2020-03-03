package learn.android.manageEmployee.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_employee_detail.*
import kotlinx.android.synthetic.main.employee_detail.*
import kotlinx.android.synthetic.main.employee_detail_edit.*
import kotlinx.android.synthetic.main.employee_item.employee_name
import learn.android.manageEmployee.R
import learn.android.manageEmployee.data.network.model.EmployeeDetails

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
        if(newEmployeeDetails == null){
            Snackbar.make(employee_details_save, "Edited details are not valid", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }else{
            updateNewEmployeeDetails(newEmployeeDetails)
        }

    }

    private fun updateNewEmployeeDetails(newEmployeeDetails: EmployeeDetails) {

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
            if (employeeEditedDetails == employeeDetails) return employeeEditedDetails
        }
        return null
    }
}
