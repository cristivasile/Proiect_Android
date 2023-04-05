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
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 * Use the [VehiclesAddBrandFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VehiclesAddBrandFragment : Fragment() {
    private lateinit var addImageButton: Button
    private lateinit var addBrandButton: Button

    private lateinit var brandNameInput: TextInputEditText
    private lateinit var previewImageView: ImageView

    private var selectedImage: Bitmap? = null

    private val brandService : BrandService by inject()

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        try {
            if (uri != null){
                val chosenImage: Bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, uri)
                previewImageView.setImageBitmap(chosenImage)
                selectedImage = chosenImage
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
        return inflater.inflate(R.layout.vehicles_add_brand_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addImageButton = view.findViewById(R.id.selectImageButton) as Button
        addBrandButton = view.findViewById(R.id.addBrandButton) as Button
        brandNameInput = view.findViewById(R.id.addBrandNameTextInput) as TextInputEditText
        previewImageView = view.findViewById(R.id.brandPreviewImage) as ImageView

        // addImageButton to open gallery picker
        addImageButton.setOnClickListener{
            getContent.launch("image/*")
        }

        // addBrand action
        addBrandButton.setOnClickListener{

            if (brandNameInput.text.toString() == ""){
                Toast.makeText(this.context, getString(R.string.add_brand_name_fail), Toast.LENGTH_SHORT).show()
            }
            else if(selectedImage == null){
                Toast.makeText(this.context, getString(R.string.add_brand_image_fail), Toast.LENGTH_SHORT).show()
            }
            else{
                try{
                    brandService.addBrand(Brand(brandNameInput.text.toString(), selectedImage!!))
                    Toast.makeText(this.context, getString(R.string.add_brand_success), Toast.LENGTH_SHORT).show()
                }
                catch (ex: Exception){
                    Toast.makeText(this.context, ex.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}