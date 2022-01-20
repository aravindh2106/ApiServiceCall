package `in`.co.logicsoft.apicallimplementation.repository

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.service.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class DataListRepository {
    suspend fun getListItemData(userId:Int): Response<List<DataItem>> {
        return ServiceBuilder.api.getDataItemList(userId)
    }
}