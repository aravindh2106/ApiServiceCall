package `in`.co.logicsoft.apicallimplementation.ui.home_screen

import `in`.co.logicsoft.apicallimplementation.databinding.FragmentHomeBinding
import `in`.co.logicsoft.apicallimplementation.model.DataItem
import `in`.co.logicsoft.apicallimplementation.repository.HomeFragmentRepository
import `in`.co.logicsoft.apicallimplementation.utils.Resource
import android.app.Service
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val repository = HomeFragmentRepository()
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(
            requireActivity().application,
            repository
        )
    }

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
        viewModel.createDataItem2(1, 1, "Aravindh", "Android developer")
        binding.createDataBtn.setOnClickListener {
            viewModel.createResponse.observe(viewLifecycleOwner, Observer { createResponse ->
                when (createResponse) {
                    is Resource.Success -> {
                        createResponse.data?.let {
                            binding.singleDataTxt.text = it.toString()
                        }
                    }
                    is Resource.Error -> {
                        createResponse.message?.let {message->
                            Toast.makeText(requireActivity(),"error:$message",Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            })
        }
        val dataItem = DataItem(1, 1, "Arun", "Android developer")
        viewModel.updateItem(2, dataItem)
        binding.updateBtn.setOnClickListener {
            viewModel.updateResponse.observe(viewLifecycleOwner, Observer { updateResponse ->
                when (updateResponse) {
                    is Resource.Success -> {
                        updateResponse.data?.let {
                            binding.singleDataTxt.text = it.toString()
                        }
                    }
                    is Resource.Error -> {
                       updateResponse.message?.let {message->
                          Toast.makeText(requireActivity(),"error:$message",Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }
    }
}
