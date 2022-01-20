package `in`.co.logicsoft.apicallimplementation.epoxy

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.rowItem
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.TypedEpoxyController
import retrofit2.Response

class DataItemController():TypedEpoxyController<List<DataItem>>() {
    override fun buildModels(data: List<DataItem>?) {
        data?.forEachIndexed { index, dataItem ->
            rowItem {
                id("$index")
                userId(dataItem.userId.toString())
                ids(dataItem.id.toString())
                title(dataItem.title)
                body(dataItem.body)
            }
        }
    }

}