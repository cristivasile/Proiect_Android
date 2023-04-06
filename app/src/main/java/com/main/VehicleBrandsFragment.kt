package com.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.databinding.VehiclesBrandsFragmentBinding
import com.main.adapters.BrandsRecyclerViewAdapter
import com.main.sevices.BrandService
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class VehicleBrandsFragment : Fragment() {

    private var _binding: VehiclesBrandsFragmentBinding? = null
    private val _brandService : BrandService by inject()
    private lateinit var _recyclerView: RecyclerView
    private lateinit var _recyclerAdapter: BrandsRecyclerViewAdapter
    private lateinit var _navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = VehiclesBrandsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _recyclerView = view.findViewById<View>(R.id.brandsList) as RecyclerView

        _navController = this.findNavController()

        // Create adapter passing in brands
        _recyclerAdapter = BrandsRecyclerViewAdapter(_brandService, _navController)
        _recyclerView.adapter = _recyclerAdapter
        _recyclerView.layoutManager = LinearLayoutManager(this.context)

        val searchBar = view.findViewById<View>(R.id.brandsSearchBar) as SearchView

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    _recyclerAdapter.filterBrands(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    _recyclerAdapter.filterBrands(newText)
                }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}