package learn.android.manageEmployee.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_all_employees.*
import kotlinx.android.synthetic.main.all_employees.*
import learn.android.manageEmployee.ManageEmployeeApplication
import learn.android.manageEmployee.R
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.repository.DataResult
import learn.android.manageEmployee.view.adapter.AllEmployeeAdapter
import learn.android.manageEmployee.viewmodel.AllEmployeesVM
import learn.android.manageEmployee.viewmodel.ViewModelFactory
import javax.inject.Inject

const val EMPLOYEE_DETAILS = "EmployeeDetails"
const val DETAILS_REQUESTCODE = 1000

class AllEmployeesActivity : AppCompatActivity() {

    private lateinit var allEmployeeAdapter: AllEmployeeAdapter
    private val logTag = AllEmployeesActivity::class.java.simpleName

    lateinit var allEmployeeViewModel: AllEmployeesVM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<AllEmployeesVM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_employees)
        setSupportActionBar(toolbar)

        ManageEmployeeApplication.appComponent.inject(this)


        allEmployeeAdapter = AllEmployeeAdapter(this, employeeClickListener)
        all_employee_view.adapter = allEmployeeAdapter
        all_employee_view.layoutManager = LinearLayoutManager(this)


        allEmployeeViewModel = ViewModelProvider(this, viewModelFactory).get(AllEmployeesVM::class.java)
        allEmployeeViewModel.allEmployeesDetails.observeForever { processResponse(it) }

        add_employee.setOnClickListener { view ->
            Snackbar.make(view, "TODO", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun processResponse(response: DataResult<List<EmployeeDetails>>?) {
        when (response?.status) {
            DataResult.Status.LOADING -> {
                Log.d(logTag, "Loading")
            }
            DataResult.Status.SUCCESS -> {
                Log.d(logTag, "responeData: ${response.data}")
                allEmployeeAdapter.setEmployeeDetails(response.data!!)
            }
            DataResult.Status.ERROR -> {
                Log.d(logTag, "Error: ${response.error?.message}")
            }
        }
    }

    private val employeeClickListener = fun(employeeDetails: EmployeeDetails) {
        val intent = Intent(this@AllEmployeesActivity, EmployeeDetailActivity::class.java)
        intent.putExtra(EMPLOYEE_DETAILS, employeeDetails)
        startActivityForResult(intent, DETAILS_REQUESTCODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DETAILS_REQUESTCODE && resultCode == Activity.RESULT_OK) {
            allEmployeeViewModel.getAllEmployee()
        }
    }
}
