package learn.android.manageEmployee.data.network.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Aswathy on 3/2/2020.
 */
data class EmployeeDetails(
    val id: Int,
    val employee_name: String,
    val employee_salary: Long,
    val employee_age: Int,
    val profile_image: String
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
        parcel.writeString(employee_name)
        parcel.writeLong(employee_salary)
        parcel.writeInt(employee_age)
        parcel.writeString(profile_image)
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

class EmployeeResponse():BaseResponse<EmployeeDetails>()