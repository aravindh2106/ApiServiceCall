package `in`.co.logicsoft.apicallimplementation.service

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("posts/1")
    suspend fun getOneItem(): Response<DataItem>
    @GET("posts")
    suspend fun getListItem():Response<List<DataItem>>

    @POST("posts")
    suspend fun pushItem(
        @Body dataItem: DataItem
    ): Response<DataItem>

    @FormUrlEncoded
    @POST("posts")
    suspend fun pushItem2(
        @Field("userId") userId:Int,
        @Field("id") id:Int,
        @Field("title") title:String,
        @Field("body") body:String
    ): Response<DataItem>
}