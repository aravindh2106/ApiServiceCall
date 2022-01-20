package `in`.co.logicsoft.apicallimplementation.ui.home_screen

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.repository.HomeFragmentRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HomeViewModel(private val repository: HomeFragmentRepository) : ViewModel() {
    val myResponse: MutableLiveData<Response<DataItem>> = MutableLiveData()
    fun getSingleItem(id: Int) {
        viewModelScope.launch {
            val response = repository.getSingleData(id)
            myResponse.value = response
        }
    }
}