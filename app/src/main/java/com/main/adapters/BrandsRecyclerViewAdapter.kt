package com.main.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.main.models.Brand
import com.main.sevices.BrandService
import java.util.*
import kotlin.collections.ArrayList

class BrandsRecyclerViewAdapter (private val _brandService: BrandService, private val _navController: NavController) : RecyclerView.Adapter<BrandsRecyclerViewAdapter.ViewHolder>() {

    private val _brands: ArrayList<Brand> = _brandService.getBrands()

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val brandTextView: TextView = itemView.findViewById(R.id.brand_name)
        val brandImageView: ImageView = itemView.findViewById(R.id.brand_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.vehicles_list_item_view, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return _brands.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val brand: Brand = _brands[position]
        // Set item views based on your views and data model
        val textView = holder.brandTextView
        textView.text = brand.name
        val imageView = holder.brandImageView
        imageView.setImageBitmap(brand.imageResource)

        holder.itemView.setOnClickListener {
            _brandService.setSelectedBrand(brand)
            _navController.navigate(R.id.action_BrandsFragment_to_ModelsFragment)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterBrands(filter: String) {
        val filteredBrands = _brandService.getBrands().filter { brand -> brand.name.lowercase().contains(filter.lowercase()) }

        _brands.clear()
        _brands.addAll(filteredBrands)
        notifyDataSetChanged()
    }
}