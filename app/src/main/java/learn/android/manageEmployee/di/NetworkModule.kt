package learn.android.manageEmployee.di

import dagger.Module
import dagger.Provides
import learn.android.manageEmployee.data.network.api.EmployeeApi
import learn.android.manageEmployee.data.network.core.RetrofitClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Aswathy on 3/13/2020.
 */

@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit = RetrofitClient.retrofit

    @Provides
    fun provideEmployeeApi(retrofit: Retrofit): EmployeeApi = EmployeeApi(retrofit)


}