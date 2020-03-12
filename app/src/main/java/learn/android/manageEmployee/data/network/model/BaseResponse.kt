package learn.android.manageEmployee.data.network.model

/**
 * Created by Aswathy on 3/2/2020.
 */
class BaseResponse<T>() {
    var status: String = ""
    var data: T? = null
    var message : String =""
}