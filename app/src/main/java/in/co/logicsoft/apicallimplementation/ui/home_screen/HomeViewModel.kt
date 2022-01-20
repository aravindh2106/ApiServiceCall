package `in`.co.logicsoft.apicallimplementation.ui.home_screen

import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.repository.HomeFragmentRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(private val repository:HomeFragmentRepository):ViewModel() {
val myResponse:MutableLiveData<Response<DataItem>> = MutableLiveData()
    fun getSingleItem(){
    viewModelScope.launch {
        val response = repository.getSingleData()
        myResponse.value = response
    }
}
   fun pushdataItem(dataItem: DataItem){
       viewModelScope.launch {
           val response = repository.pushItem(dataItem)
           myResponse.value = response
       }

    }
    fun pushDataItem2(userId:Int,id:Int,title:String,body:String){
        viewModelScope.launch {
            val response = repository.pushItem2(userId, id, title, body)
            myResponse.value = response
        }
    }
}