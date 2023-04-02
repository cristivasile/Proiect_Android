package com.main.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.R
import com.main.models.Brand
import com.main.sevices.BrandService
import java.util.*
import kotlin.collections.ArrayList
import org.koin.java.KoinJavaComponent.inject

class BrandsRecyclerViewAdapter (private val mBrands: ArrayList<Brand>, private val brandService: BrandService) : RecyclerView.Adapter<BrandsRecyclerViewAdapter.ViewHolder>() {

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
        val contactView = inflater.inflate(R.layout.vehicles_brand_item_view, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return mBrands.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val brand: Brand = mBrands[position]
        // Set item views based on your views and data model
        val textView = holder.brandTextView
        textView.text = brand.name
        val imageView = holder.brandImageView
        imageView.setImageResource(brand.imageResource)
    }

    @SuppressLint("NotifyDataSetChanged")
    public fun filterBrands(filter: String) {
        val filteredBrands = ArrayList<Brand>()

        for(brand in brandService.getBrands()){
            if(brand.name.lowercase().contains(filter.lowercase()))
                filteredBrands.add(brand)
        }

        mBrands.clear()
        mBrands.addAll(filteredBrands)
        notifyDataSetChanged()
    }
}