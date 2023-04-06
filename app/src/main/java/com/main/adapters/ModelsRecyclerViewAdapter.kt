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
import com.main.sevices.BrandService
import com.main.sevices.ModelService
import com.main.models.Model

class ModelsRecyclerViewAdapter (private val _brandService: BrandService, private val _modelService: ModelService, private val _navController: NavController) : RecyclerView.Adapter<ModelsRecyclerViewAdapter.ViewHolder>() {

    private val _models: ArrayList<Model> = _modelService.getModelsByBrand(_brandService.getSelectedBrand())

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
        return _models.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val model: Model = _models[position]
        // Set item views based on your views and data model
        val textView = holder.brandTextView
        textView.text = model.modelName
        val imageView = holder.brandImageView
        imageView.setImageBitmap(model.image)

        holder.itemView.setOnClickListener {
            _modelService.setSelectedModel(model)
            _navController.navigate(R.id.action_ModelsFragment_to_vehicleModelDetailsFragment)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterModels(filter: String) {
        val filteredModels =_modelService.getModelsByBrand(_brandService.getSelectedBrand())
            .filter { model -> model.modelName.lowercase().contains(filter.lowercase()) }

        _models.clear()
        _models.addAll(filteredModels)
        notifyDataSetChanged()
    }
}
