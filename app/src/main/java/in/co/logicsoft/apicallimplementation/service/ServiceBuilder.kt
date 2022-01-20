package `in`.co.logicsoft.apicallimplementation.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    //create logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    //custom Intercepter for headers
    private val headerInterceptor = Interceptor { chain ->
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type","application/json")
            .addHeader("X-platform","Android")
            .build()
        val response = chain.proceed(request)
        response
    }

    //create okhttp client
    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)

    //create retrofit builder
    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okHttp.build())

    //create retrofit instance
    private val retrofit = builder.build()
    val api: ApiInterface = retrofit.create(ApiInterface::class.java)
}