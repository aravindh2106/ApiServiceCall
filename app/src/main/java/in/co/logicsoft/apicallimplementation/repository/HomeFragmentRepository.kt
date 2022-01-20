package `in`.co.logicsoft.apicallimplementation.repository

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.service.ServiceBuilder
import retrofit2.Response

class HomeFragmentRepository {
    suspend fun getSingleData():Response<DataItem>{
        return ServiceBuilder.api.getOneItem()
    }
    suspend fun createItem(dataItem:DataItem):Response<DataItem>{
        return ServiceBuilder.api.createData(dataItem)
    }
    suspend fun createItem2(userId:Int,id:Int,title:String,body:String):Response<DataItem>{
        return ServiceBuilder.api.createData2(userId, id, title, body)

    }
    suspend fun updateItem(id:Int,dataItem:DataItem):Response<DataItem>{
        return ServiceBuilder.api.updateItem(id, dataItem)
    }
}
