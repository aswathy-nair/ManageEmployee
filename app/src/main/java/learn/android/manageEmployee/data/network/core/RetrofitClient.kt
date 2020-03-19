package learn.android.manageEmployee.data.network.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Aswathy on 3/2/2020.
 */
object RetrofitClient {
    private const val CONNECTION_TIMEOUT: Long = 60
    private const val READ_TIMEOUT: Long = 60
    private const val WRITE_TIMEOUT: Long = 60

    //TODO: change url to a common place
    private const val baseUrl = "http://dummy.restapiexample.com/api/v1/"

    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    private val converterFactory: GsonConverterFactory = GsonConverterFactory.create()

     val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()

}