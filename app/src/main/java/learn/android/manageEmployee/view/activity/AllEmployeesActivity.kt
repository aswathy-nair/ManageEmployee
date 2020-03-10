package learn.android.manageEmployee.view.activity

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
import learn.android.manageEmployee.R
import learn.android.manageEmployee.data.network.core.NetworkStates
import learn.android.manageEmployee.data.network.model.EmployeeDetails
import learn.android.manageEmployee.data.network.model.EmployeeResponse
import learn.android.manageEmployee.view.adapter.AllEmployeeAdapter
import learn.android.manageEmployee.viewmodel.AllEmployees

const val EMPLOYEE_DETAILS = "EmployeeDetails"

class MainActivity : AppCompatActivity() {

    private lateinit var allEmployeeAdapter : AllEmployeeAdapter
    private val logTag = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_employees)
        setSupportActionBar(toolbar)

        allEmployeeAdapter = AllEmployeeAdapter(this, employeeClickListener)
        all_employee_view.adapter = allEmployeeAdapter
        all_employee_view.layoutManager = LinearLayoutManager(this)

        val allEmployeeViewModel = ViewModelProvider(this).get(AllEmployees::class.java)
        allEmployeeViewModel.getAllEmployee().observeForever { processResponse(it) }

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

    private fun processResponse(response: NetworkStates<EmployeeResponse>?) {
        when (response?.status) {
            NetworkStates.Status.LOADING -> {
                Log.d(logTag, "Loading")
            }
            NetworkStates.Status.SUCCESS -> {
                Log.d(logTag, "responeData: ${response.data}")
                allEmployeeAdapter.setEmployeeDetails(response.data!!.data)
            }
            NetworkStates.Status.ERROR -> {
                Log.d(logTag, "Error: ${response.resourceError}")
            }
        }
    }
    private val employeeClickListener = fun (employeeDetails: EmployeeDetails){
        val intent = Intent(this@MainActivity, EmployeeDetailActivity::class.java)
        intent.putExtra(EMPLOYEE_DETAILS, employeeDetails)
        startActivity(intent)
    }
}
