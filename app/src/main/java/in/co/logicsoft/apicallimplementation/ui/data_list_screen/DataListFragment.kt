package `in`.co.logicsoft.apicallimplementation.ui.data_list_screen

import `in`.co.logicsoft.apicallimplementation.databinding.FragmentDataListBinding
import `in`.co.logicsoft.apicallimplementation.epoxy.DataItemController
import `in`.co.logicsoft.apicallimplementation.repository.DataListRepository
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer


class DataListFragment : Fragment() {
    private lateinit var binding: FragmentDataListBinding
    private val repository = DataListRepository()
    val viewModel: DataListViewModel by viewModels { DataListViewModelFactory(repository) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListDataItem()
        subscribeUI()

    }
    private fun subscribeUI(){
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {response->
            if (response.isSuccessful){
                val controller = DataItemController()
                controller.setData(response.body())
            }

        })
    }
}