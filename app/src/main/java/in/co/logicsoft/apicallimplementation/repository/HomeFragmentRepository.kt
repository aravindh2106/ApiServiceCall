package `in`.co.logicsoft.apicallimplementation.repository

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.service.ServiceBuilder
import retrofit2.Call
import retrofit2.Response

class HomeFragmentRepository {
    suspend fun getSingleData(id:Int):Response<DataItem>{
        return ServiceBuilder.api.getDataItem(id)
    }
}
