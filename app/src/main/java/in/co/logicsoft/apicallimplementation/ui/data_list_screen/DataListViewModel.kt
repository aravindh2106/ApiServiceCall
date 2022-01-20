package `in`.co.logicsoft.apicallimplementation.ui.data_list_screen

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.repository.DataListRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class DataListViewModel(private val repository: DataListRepository):ViewModel() {
    val myResponse: MutableLiveData<Response<List<DataItem>>> = MutableLiveData()


    fun getListDataItem(){
        viewModelScope.launch {
            val response = repository.getListItemData()
            myResponse.value = response
        }
    }
}