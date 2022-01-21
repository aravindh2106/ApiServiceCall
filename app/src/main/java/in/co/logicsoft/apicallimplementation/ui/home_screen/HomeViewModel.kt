package `in`.co.logicsoft.apicallimplementation.ui.home_screen

import `in`.co.logicsoft.apicallimplementation.DataApplication
import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.repository.HomeFragmentRepository
import `in`.co.logicsoft.apicallimplementation.utils.Resource
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class HomeViewModel(val app: Application, private val repository: HomeFragmentRepository) :
    AndroidViewModel(app) {
    val createResponse: MutableLiveData<Resource<DataItem>> = MutableLiveData()
    val updateResponse: MutableLiveData<Resource<DataItem>> = MutableLiveData()

    fun getSingleItem() {
        viewModelScope.launch {
            val response = repository.getSingleData()
            // myResponse.value = response
        }
    }

    fun createDataItem(dataItem: DataItem) {
        viewModelScope.launch {
            val response = repository.createItem(dataItem)
            //   myResponse.value = response
        }
    }

    fun createDataItem2(userId: Int, id: Int, title: String, body: String) {
        viewModelScope.launch {
           safeListDataItemCall(userId, id, title, body)
        }
    }

    private fun handleCreateDataResponse(response: Response<DataItem>): Resource<DataItem> {
        if (response.isSuccessful) {
            response.body()?.let { createResponse ->
                return Resource.Success(createResponse)
            }
        }
        return Resource.Error(null, response.message())
    }

    fun updateItem(id: Int, dataItem: DataItem) {
        viewModelScope.launch {
          safeUpdateDataItemCall(id,dataItem)
        }
    }

    private fun handleUpdateDataResponse(response: Response<DataItem>): Resource<DataItem> {
        if (response.isSuccessful) {
            response.body()?.let { updateResponse ->
                return Resource.Success(updateResponse)
            }
        }
        return Resource.Error(null, response.message())
    }

    private suspend fun safeListDataItemCall(userId: Int, id: Int, title: String, body: String) {
        createResponse.value = Resource.Loading()
        try {
            if (checkInternetConnection()) {
                val response = repository.createItem2(userId, id, title, body)
                createResponse.value = handleCreateDataResponse(response)
            } else {
                createResponse.value = Resource.Error(null, "No Internet Connection")
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> createResponse.value = Resource.Error(null, "Network failure")
                else -> createResponse.value = Resource.Error(null, "Conversion Error")
            }

        }
    }
    private suspend fun safeUpdateDataItemCall(id: Int, dataItem: DataItem) {
        updateResponse.value = Resource.Loading()
        try {
            if (checkInternetConnection()) {
                val response = repository.updateItem(id, dataItem)
                updateResponse.value = handleCreateDataResponse(response)
            } else {
               updateResponse.value = Resource.Error(null, "No Internet Connection")
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> updateResponse.value = Resource.Error(null, "Network failure")
                else -> updateResponse.value = Resource.Error(null, "Conversion Error")
            }
        }
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager = getApplication<DataApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
    }
}