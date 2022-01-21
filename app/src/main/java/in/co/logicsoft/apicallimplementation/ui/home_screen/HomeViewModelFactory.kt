package `in`.co.logicsoft.apicallimplementation.ui.home_screen

import `in`.co.logicsoft.apicallimplementation.repository.HomeFragmentRepository
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory(val application:Application,private val repo:HomeFragmentRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(application,repo) as T
    }
}