package `in`.co.logicsoft.apicallimplementation.repository

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.service.ServiceBuilder
import retrofit2.Response

class HomeFragmentRepository {
    suspend fun getSingleData():Response<DataItem>{
        return ServiceBuilder.api.getOneItem()
    }
    suspend fun pushItem(dataItem:DataItem):Response<DataItem>{
        return ServiceBuilder.api.pushItem(dataItem)
    }
    suspend fun pushItem2(userId:Int,id:Int,title:String,body:String):Response<DataItem>{
        return ServiceBuilder.api.pushItem2(userId, id, title, body)

    }
}
