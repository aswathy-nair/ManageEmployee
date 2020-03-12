package learn.android.manageEmployee.data.network.core

/**
 * Created by Aswathy on 3/2/2020.
 */
import learn.android.manageEmployee.data.network.model.BaseResponse
import learn.android.manageEmployee.data.repository.DataResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class NetworkCall<T> {
    private lateinit var call: Call<T>

    fun <R> doNetworkCall(call: Call<T>): DataResult<R> {
        lateinit var result: DataResult<R>

        this.call = call
        try {
            val response = this.call.clone().execute()
            result = processResponse(response)
        }catch (exception: Exception){
            result = DataResult.error(Error(exception.message))
            exception.printStackTrace()
        }

        return result
    }

    private fun <R> processResponse(response: Response<T>): DataResult<R> {
        return if (response.isSuccessful) {
            val data = response.body() as BaseResponse<R>
            DataResult.success(data.data)
        } else {
            DataResult.error(
                ErrorHandle.parseError(response.errorBody())
            )
        }
    }

    fun cancel() {
        if (::call.isInitialized) {
            call.cancel()
        }
    }
}

object ErrorHandle {
    fun parseError(responseErrorBody: ResponseBody?): Error {
        val converter =
            RetrofitClient.retrofit
                .responseBodyConverter<Error>(
                    Error::class.java,
                    arrayOfNulls<Annotation>(0)
                )

        var error: Error? = null
        try {
            error = converter.convert(responseErrorBody!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return error ?: Error()
    }
}
