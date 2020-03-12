package learn.android.manageEmployee.data.network.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Aswathy on 3/2/2020.
 */
@Entity(tableName = "employee")
data class EmployeeDetails(
    @ColumnInfo(name = "id") @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "employee_name")
    @SerializedName("employee_name")
    val employeeName: String,

    @ColumnInfo(name = "employee_salary")
    @SerializedName("employee_salary")
    val employeeSalary: Long,

    @ColumnInfo(name = "employee_age")
    @SerializedName("employee_age")
    val employeeAge: Int,

    @ColumnInfo(name = "profile_image")
    @SerializedName("profile_image")
    val profileImage: String

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readLong(),
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(employeeName)
        parcel.writeLong(employeeSalary)
        parcel.writeInt(employeeAge)
        parcel.writeString(profileImage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmployeeDetails> {
        override fun createFromParcel(parcel: Parcel): EmployeeDetails {
            return EmployeeDetails(parcel)
        }

        override fun newArray(size: Int): Array<EmployeeDetails?> {
            return arrayOfNulls(size)
        }
    }
}

class EmployeeUpdateResponse(val status: String, data: EmployeeDetails)
class EmployeeDeleteResponse(val status: String, message: String)