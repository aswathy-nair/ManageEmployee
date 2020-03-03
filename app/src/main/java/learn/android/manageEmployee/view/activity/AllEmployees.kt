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

const val EMPLOYEE_DETAIL_REQUEST_CODE = 1
const val EMPLOYEE_DETAILS = "EmployeeDetails"

class MainActivity : AppCompatActivity() {

    lateinit var allEmployeeAdapter : AllEmployeeAdapter
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
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_delete -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun processResponse(response: NetworkStates<EmployeeResponse>?) {
        when (response?.status) {
            NetworkStates.Status.LOADING -> {
                Log.d("", "Loading")
            }
            NetworkStates.Status.SUCCESS -> {
                Log.d("VM", "responeData: ${response.data}")
                allEmployeeAdapter.setEmployeeDetails(response.data!!.data)
            }
            NetworkStates.Status.ERROR -> {
                Log.d("VM", "Error: ${response.resourceError}")
            }
        }
    }
    val employeeClickListener = fun (employeeDetails: EmployeeDetails){
        val intent = Intent(this@MainActivity, EmployeeDetailActivity::class.java)
        intent.putExtra(EMPLOYEE_DETAILS, employeeDetails)
        startActivityForResult(intent, EMPLOYEE_DETAIL_REQUEST_CODE)
    }
}
