package `in`.co.logicsoft.apicallimplementation.ui.data_list_screen

import `in`.co.logicsoft.apicallimplementation.repository.DataListRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DataListViewModelFactory(private val repository: DataListRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataListViewModel(repository) as T
    }
}