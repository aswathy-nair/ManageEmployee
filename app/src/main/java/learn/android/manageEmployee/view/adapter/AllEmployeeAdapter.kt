package learn.android.manageEmployee.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.employee_item.view.*
import learn.android.manageEmployee.R
import learn.android.manageEmployee.data.network.model.EmployeeDetails

/**
 * Created by Aswathy on 3/2/2020.
 */
class AllEmployeeAdapter(
    context: Context,
    val employeeClickListener: (EmployeeDetails) -> Unit
): RecyclerView.Adapter<AllEmployeeAdapter.EmployeeViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var employeeDetails = emptyList<EmployeeDetails>()

    class EmployeeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView = inflater.inflate(R.layout.employee_item, parent, false)
        return EmployeeViewHolder(itemView)
    }

    override fun getItemCount(): Int  = employeeDetails.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.itemView.employee_name.text = employeeDetails[position].employee_name
        holder.itemView.setOnClickListener{employeeClickListener(employeeDetails[position])}
    }
    fun setEmployeeDetails(employeeDetails: List<EmployeeDetails>) {
        this.employeeDetails = employeeDetails
        notifyDataSetChanged()
    }
}