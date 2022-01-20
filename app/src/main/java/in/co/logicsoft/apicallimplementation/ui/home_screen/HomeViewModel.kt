package `in`.co.logicsoft.apicallimplementation.ui.home_screen

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.repository.HomeFragmentRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val repository: HomeFragmentRepository) : ViewModel() {
    val myResponse: MutableLiveData<Response<DataItem>> = MutableLiveData()
    val updateResponse: MutableLiveData<Response<DataItem>> = MutableLiveData()
    fun getSingleItem() {
        viewModelScope.launch {
            val response = repository.getSingleData()
            myResponse.value = response
        }
    }

    fun createDataItem(dataItem: DataItem) {
        viewModelScope.launch {
            val response = repository.createItem(dataItem)
            myResponse.value = response
        }

    }

    fun createDataItem2(userId: Int, id: Int, title: String, body: String) {
        viewModelScope.launch {
            val response = repository.createItem2(userId, id, title, body)
            myResponse.value = response
        }

    }
    fun updateItem(id: Int, dataItem: DataItem) {
        viewModelScope.launch(Dispatchers.Main) {
            val response =repository.updateItem(id,dataItem)
            updateResponse.value =response
        }
    }
}