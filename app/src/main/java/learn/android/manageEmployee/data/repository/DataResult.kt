package learn.android.manageEmployee.data.repository

/**
 * Created by Aswathy on 3/10/2020.
 */
class DataResult<T> private constructor(
    val status: Status
) {
    var data: T? = null
    var error: Error? = null

    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <T> success(successData: T?): DataResult<T> = DataResult<T>(Status.SUCCESS).apply {
            data = successData
        }

        fun <T> error(errorData: Error?) = DataResult<T>(Status.ERROR).apply {
            error = errorData
        }

        fun <T> loading() = DataResult<T>(Status.LOADING)
    }
}