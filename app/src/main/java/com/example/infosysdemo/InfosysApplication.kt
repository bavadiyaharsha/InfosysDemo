package com.example.infosysdemo

import android.app.Application
import android.provider.Settings
import com.example.infosysdemo.webservicecall.WebUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class InfosysApplication :Application(){
    companion object {
        var instance: InfosysApplication? = null

    }

    var retrofit: Retrofit? = null
    val requestQueue: Retrofit?
    // initialize the request queue, the queue instance will be created when it is accessed for the first time

        get() {
            if (retrofit == null) {
//set web service call constraint
                val builder = OkHttpClient.Builder().readTimeout(110, TimeUnit.SECONDS)
                    .connectTimeout(110, TimeUnit.SECONDS).writeTimeout(110, TimeUnit.SECONDS)
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(object : Interceptor {
                    @Throws(IOException::class)
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request: Request = chain.request().newBuilder()
                            .addHeader("device-type", "android")
                            .addHeader("app-version", "1.0")
                            .build()
                        return chain.proceed(request)
                    }
                })
                builder.addInterceptor(httpLoggingInterceptor)
                val okHttpClient = builder.build()

                retrofit = Retrofit.Builder()
                    .baseUrl(WebUrl.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()


            }
            return retrofit
        }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}