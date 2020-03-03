package learn.android.manageEmployee.data.network.core

/**
 * Created by Aswathy on 3/2/2020.
 */
class NetworkStates<T> private constructor(
    val status: Status,
    val data: T?,
    val resourceError: NetworkError?
) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T?): NetworkStates<T> {
            return NetworkStates(Status.SUCCESS, data, null)
        }

        fun <T> error(resourceError: NetworkError?): NetworkStates<T> {
            return NetworkStates(Status.ERROR, null, resourceError)
        }

        fun <T> loading(data: T?): NetworkStates<T> {
            return NetworkStates(Status.LOADING, data, null)
        }
    }
}
class NetworkError{
    var error: Error? = null
}