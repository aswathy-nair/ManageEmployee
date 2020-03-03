package learn.android.manageEmployee.data.network.core

/**
 * Created by Aswathy on 3/2/2020.
 */
import androidx.lifecycle.MutableLiveData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response

class NetworkCall<T> {
    private lateinit var call: Call<T>

    fun makeCall(call: Call<T>): MutableLiveData<NetworkStates<T>> {
        this.call = call
        val callBackKt = CallBackKt<T>()
        callBackKt.result.value = NetworkStates.loading(null)
        this.call.clone().enqueue(callBackKt)
        return callBackKt.result
    }

    class CallBackKt<T> : Callback<T> {
        var result: MutableLiveData<NetworkStates<T>> = MutableLiveData()

        override fun onFailure(call: Call<T>, t: Throwable) {
            result.value = NetworkStates.error(NetworkError())
            t.printStackTrace()
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful)
                result.value = NetworkStates.success(response.body())
            else {
                result.value =
                    NetworkStates.error(
                        ErrorHandle.parseError(response.errorBody())
                    )
            }
        }
    }

    fun cancel() {
        if (::call.isInitialized) {
            call.cancel()
        }
    }
}

object ErrorHandle {
    fun parseError(responseErrorBody: ResponseBody?): NetworkError {
        val converter =
            HttpClient.retrofit
                .responseBodyConverter<NetworkError>(
                    NetworkError::class.java,
                    arrayOfNulls<Annotation>(0)
                )

        var error: NetworkError? = null
        try {
            error = converter.convert(responseErrorBody!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return error ?: NetworkError()
    }
}
