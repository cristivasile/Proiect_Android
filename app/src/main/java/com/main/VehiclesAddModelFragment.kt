package com.main

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidproject.R
import com.google.android.material.textfield.TextInputEditText
import com.main.models.Brand
import com.main.sevices.BrandService
import com.main.sevices.ModelService
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 * Use the [VehiclesAddModelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VehiclesAddModelFragment : Fragment() {
    private lateinit var _addImageButton: Button
    private lateinit var _addModelButton: Button

    private lateinit var _brandNameInput: TextInputEditText
    private lateinit var _previewImageView: ImageView

    private var _selectedImage: Bitmap? = null
    private val _brandService : BrandService by inject()
    private val _modelService : ModelService by inject()

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        try {
            if (uri != null){
                val chosenImage: Bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, uri)
                _previewImageView.setImageBitmap(chosenImage)
                _selectedImage = chosenImage
            }
        }
        catch (ex: Exception){
            Toast.makeText(this.context, ex.message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.vehicles_add_model_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _addImageButton = view.findViewById(R.id.selectImageButton) as Button
        _addModelButton = view.findViewById(R.id.addBrandButton) as Button
        _brandNameInput = view.findViewById(R.id.addBrandNameTextInput) as TextInputEditText
        _previewImageView = view.findViewById(R.id.brandPreviewImage) as ImageView

        // addImageButton to open gallery picker
        _addImageButton.setOnClickListener{
            getContent.launch("image/*")
        }

        // addBrand action
        _addBrandButton.setOnClickListener{

            if (_brandNameInput.text.toString() == ""){
                Toast.makeText(this.context, getString(R.string.add_brand_name_fail), Toast.LENGTH_SHORT).show()
            }
            else if(_selectedImage == null){
                Toast.makeText(this.context, getString(R.string.add_brand_image_fail), Toast.LENGTH_SHORT).show()
            }
            else{
                try{
                    _brandService.addBrand(Brand(_brandNameInput.text.toString(), _selectedImage!!))
                    Toast.makeText(this.context, getString(R.string.add_brand_success), Toast.LENGTH_SHORT).show()
                }
                catch (ex: Exception){
                    Toast.makeText(this.context, ex.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}