package com.example.assignment.model.repo


import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object BaseRetrofit {

    internal lateinit var apiService: ApiServices
    fun getApiService(): ApiServices {
        if (!BaseRetrofit::apiService.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(RequestType.BASE_UEL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(gethttpClient())
                .build()
            apiService = retrofit.create(ApiServices::class.java)
        }
        return apiService
    }

    private fun gethttpClient(): OkHttpClient {
        val userName = "!S0ciEty2021@!"
        val password = "!S0ciEty2021@!"
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(30, TimeUnit.MINUTES)
            .writeTimeout(30, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.MINUTES)
        builder.interceptors().add(
            RetrofitHeaderInterceptor(
                userName,
                password
            )
        )
        //Add logging for debug builds

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.interceptors().add(httpLoggingInterceptor)
//        if (BuildConfig.DEBUG) {
//            val httpLoggingInterceptor = HttpLoggingInterceptor()
//            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//            builder.interceptors().add(httpLoggingInterceptor)
//        }
        return builder.build()
    }

    private class RetrofitHeaderInterceptor(
        userName: String,
        password: String
    ) :
        Interceptor {

//        var temp =
//            MySharedPreferences.mMySharedPreferences?.getStringValue(SharedpreferencesFlags.TOKEN)

//        private val mAuth: String = Credentials.basic(userName, password)

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
            var response = chain.proceed(request.build())
            var tryCount = 0
            val headerCode = response.code
            while (!response.isSuccessful && tryCount < 1) {
                tryCount++
                response = chain.proceed(request.build())
            }
            Log.e("intercept", "Response header code - $headerCode")
            return response
        }
    }

}