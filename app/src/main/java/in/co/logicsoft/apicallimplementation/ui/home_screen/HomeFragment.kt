package `in`.co.logicsoft.apicallimplementation.ui.home_screen

import `in`.co.logicsoft.apicallimplementation.databinding.FragmentHomeBinding
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
        //   viewModel.getSingleItem()
        //  val myDataItem = DataItem(2,2,"Aravindh","Android developer")
        viewModel.pushDataItem2(2, 2, "Aravindh", "Android developer")
        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.d("response", response.body().toString())
                Log.d("response", response.code().toString())
                Log.d("response", response.message())
                /*val stringBuilder = StringBuilder()
                 val responseBody = response.body()!!
                 stringBuilder.append(responseBody.id)
                 stringBuilder.append("\n")
                 stringBuilder.append(responseBody.body)
                 stringBuilder.append("\n")
                 binding.singleDataTxt.text = stringBuilder*/
                binding.singleDataTxt.text = response.body().toString()
            } else {
                Log.d("response", response.errorBody().toString())

            }
        })

    }
}