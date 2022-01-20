package `in`.co.logicsoft.apicallimplementation.service

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("posts/{id}")
    suspend fun getDataItem(@Path("id") id: Int): Response<DataItem>
    @GET("posts/")
    suspend fun getDataItemList(@Query("userId") userId:Int):Response<List<DataItem>>
}