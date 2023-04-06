package com.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.databinding.VehiclesModelsFragmentBinding
import com.main.adapters.ModelsRecyclerViewAdapter
import com.main.sevices.BrandService
import com.main.sevices.ModelService
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class VehiclesModelsFragment : Fragment() {

    private var _binding: VehiclesModelsFragmentBinding? = null
    private lateinit var _backButton: Button
    private lateinit var _navController: NavController
    private lateinit var _recyclerView: RecyclerView
    private lateinit var _titleTextView: TextView
    private lateinit var _recyclerAdapter: ModelsRecyclerViewAdapter

    private val _brandService : BrandService by inject()
    private val _modelService : ModelService by inject()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = VehiclesModelsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set current title
        _titleTextView = view.findViewById<View>(R.id.currentBrandTextView) as TextView
        _titleTextView.text = buildString {
        append(_brandService.getSelectedBrand().name)
        append(" ")
        append(getString(R.string.models_string))
        append(":")
        }

        _navController = this.findNavController()
        _backButton = view.findViewById<View>(R.id.modelsBackButton) as Button

        _backButton.setOnClickListener{
            _navController.navigate(R.id.action_ModelsFragment_to_BrandsFragment)
        }

        _recyclerView = view.findViewById<View>(R.id.modelsList) as RecyclerView

        _navController = this.findNavController()

        // Create adapter passing in brands
        _recyclerAdapter = ModelsRecyclerViewAdapter(_brandService, _modelService, _navController)
        _recyclerView.adapter = _recyclerAdapter
        _recyclerView.layoutManager = LinearLayoutManager(this.context)

        val searchBar = view.findViewById<View>(R.id.modelsSearchBar) as SearchView

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    _recyclerAdapter.filterModels(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    _recyclerAdapter.filterModels(newText)
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = "View models"
    }
}