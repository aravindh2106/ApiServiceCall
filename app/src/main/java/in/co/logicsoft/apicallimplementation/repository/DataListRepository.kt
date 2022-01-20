package `in`.co.logicsoft.apicallimplementation.repository

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.service.ServiceBuilder
import retrofit2.Response

class DataListRepository {
    suspend fun getListItemData(): Response<List<DataItem>> {
        return ServiceBuilder.api.getListItem()
    }
}