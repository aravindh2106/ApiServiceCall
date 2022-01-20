package `in`.co.logicsoft.apicallimplementation.ui.data_list_screen

import `in`.co.logicsoft.apicallimplementation.databinding.FragmentDataListBinding
import `in`.co.logicsoft.apicallimplementation.epoxy.DataItemController
import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.repository.DataListRepository
import `in`.co.logicsoft.apicallimplementation.ui.home_screen.HomeFragmentDirections
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager


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
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        viewModel.myResponse.observe(viewLifecycleOwner, { response->
            if (response.isSuccessful){
                Log.d("AP", response.body().toString())
                val controller = DataItemController()
                controller.setData(response.body())
                binding.recyclerview.adapter = controller.adapter
            }
        })
    }

}