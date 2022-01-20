package `in`.co.logicsoft.apicallimplementation.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    //create logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    //create okhttp client
    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)
        .connectTimeout(30,TimeUnit.SECONDS)
        .writeTimeout(30,TimeUnit.SECONDS)
        .readTimeout(30,TimeUnit.SECONDS)
    //create retrofit builder
    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())

        .client(okHttp.build())
    //create retrofit instance
    private val retrofit = builder.build()
/*
   fun <T> buildService(serviceType:Class<T>):T{
       return retrofit.create(serviceType)
   }*/
    val api:ApiInterface = retrofit.create(ApiInterface::class.java)
}