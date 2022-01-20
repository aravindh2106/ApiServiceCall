package `in`.co.logicsoft.apicallimplementation.ui.home_screen

import `in`.co.logicsoft.apicallimplementation.databinding.FragmentHomeBinding
import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.repository.HomeFragmentRepository
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val repository = HomeFragmentRepository()
    val viewModel: HomeViewModel by viewModels { HomeViewModelFactory(repository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.DataListBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDataListFragment()
            findNavController().navigate(action)
        }
        binding.singleDataBtn.setOnClickListener {
            viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    binding.singleDataTxt.text = response.body().toString()
                } else {
                    Log.d("response", response.errorBody().toString())

                }
            })
        }
        binding.updateBtn.setOnClickListener {
            viewModel.updateResponse.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    binding.singleDataTxt.text = response.body().toString()
                } else {
                    Log.d("response", response.errorBody().toString())
                }
            })
        }
        viewModel.createDataItem2(1, 1, "Aravindh", "Android developer")
        val dataItem = DataItem(1, 1, "Arun", "Android developer")
        viewModel.updateItem(2, dataItem)
    }
}