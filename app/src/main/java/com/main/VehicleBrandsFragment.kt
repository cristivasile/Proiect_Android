package com.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.example.androidproject.databinding.VehiclesBrandsFragmentBinding
import com.main.adapters.BrandsRecyclerViewAdapter
import com.main.models.Brand

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class VehicleBrandsFragment : Fragment() {

    private var _binding: VehiclesBrandsFragmentBinding? = null
    private lateinit var brands: ArrayList<Brand>
    private lateinit var recyclerView: RecyclerView;
    private lateinit var recyclerAdapter: BrandsRecyclerViewAdapter;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = VehiclesBrandsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lookup the recyclerview in activity layout
        recyclerView = view.findViewById<View>(R.id.brands_list) as RecyclerView
        // Initialize contacts
        brands = Brand.createBrandsList()

        // Create adapter passing in the sample user data
        recyclerAdapter = BrandsRecyclerViewAdapter(brands)
        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = recyclerAdapter
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this.context)

        val searchBar = view.findViewById<View>(R.id.search_bar) as SearchView

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    recyclerAdapter.filterBrands(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    recyclerAdapter.filterBrands(newText)
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