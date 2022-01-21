package `in`.co.logicsoft.apicallimplementation.ui.data_list_screen

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.repository.DataListRepository
import `in`.co.logicsoft.apicallimplementation.utils.Resource
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class DataListViewModel(private val repository: DataListRepository):ViewModel() {
    val dataListResponse: MutableLiveData<Resource<List<DataItem>>> = MutableLiveData()
    fun getListDataItem(){
        viewModelScope.launch {
            dataListResponse.value = Resource.Loading()
            val response = repository.getListItemData()
            dataListResponse.value = handleListDataResponse(response)
        }
    }
    private fun handleListDataResponse(response: Response<List<DataItem>>): Resource<List<DataItem>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(null, response.message())
    }
}