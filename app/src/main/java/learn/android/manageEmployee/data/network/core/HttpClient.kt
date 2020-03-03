package learn.android.manageEmployee.data.network.core

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Aswathy on 3/2/2020.
 */
object HttpClient{
    private val okHttpClient: OkHttpClient
    var retrofit: Retrofit

    init {
        okHttpClient = createOkHttpClient()
        retrofit = createRetrofit(okHttpClient)
    }
    private fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }
    private fun createRetrofit(client: OkHttpClient) : Retrofit {
        //TODO: change url to a common place
        val baseUrl = "http://dummy.restapiexample.com/api/v1/"

         return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}