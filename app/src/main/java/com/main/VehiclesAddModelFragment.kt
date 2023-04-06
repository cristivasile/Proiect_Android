package com.main

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.FileProvider
import com.example.androidproject.BuildConfig
import com.example.androidproject.R
import com.google.android.material.textfield.TextInputEditText
import com.main.models.Model
import com.main.sevices.BrandService
import com.main.sevices.ModelService
import org.koin.android.ext.android.inject
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class VehiclesAddModelFragment : Fragment() {
    private lateinit var _addImageButton: Button
    private lateinit var _takeImageButton: Button
    private lateinit var _addModelButton: Button

    private lateinit var _brandNameInput: TextInputEditText
    private lateinit var _modelNameInput: TextInputEditText
    private lateinit var _bodyTypeInput: TextInputEditText
    private lateinit var _seatsInput: TextInputEditText
    private lateinit var _yearInput: TextInputEditText
    private lateinit var _descriptionInput: TextInputEditText
    private lateinit var _previewImageView: ImageView

    private var _selectedImage: Bitmap? = null
    private val _brandService : BrandService by inject()
    private val _modelService : ModelService by inject()

    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri?>
    private lateinit var requestPermissionAndOpenCameraLauncher: ActivityResultLauncher<String>

    @Suppress("DEPRECATION")
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

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //camera launcher
        takePictureLauncher = registerForActivityResult(TakePicture()) { outputUri ->
            if (outputUri != null) {
                val chosenImage: Bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, outputUri)
                _previewImageView.setImageBitmap(chosenImage)
                _selectedImage = chosenImage
            }
        }

        //camera permission launcher
        requestPermissionAndOpenCameraLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permission granted, launch the camera
                takePictureLauncher.launch(null)
            } else {
                // Permission denied, show a message
                Toast.makeText(activity, getString(R.string.camera_permission_required_message), Toast.LENGTH_SHORT).show()
            }
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.vehicles_add_model_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _addImageButton = view.findViewById(R.id.selectModelImageButton) as Button
        _takeImageButton = view.findViewById(R.id.takeModelImageButton) as Button
        _addModelButton = view.findViewById(R.id.addModelButton) as Button

        _modelNameInput = view.findViewById(R.id.addModelNameTextInput) as TextInputEditText
        _brandNameInput = view.findViewById(R.id.addModelBrandNameTextInput) as TextInputEditText
        _bodyTypeInput = view.findViewById(R.id.addModelBodyTypeTextInput) as TextInputEditText
        _seatsInput = view.findViewById(R.id.addModelNumberOfSeatsTextInput) as TextInputEditText
        _yearInput = view.findViewById(R.id.addModelStartYearTextInput) as TextInputEditText
        _descriptionInput = view.findViewById(R.id.addModelDescriptionTextInput) as TextInputEditText

        _previewImageView = view.findViewById(R.id.brandPreviewImage) as ImageView

        // addImageButton to open gallery picker
        _addImageButton.setOnClickListener{
            getContent.launch("image/*")
        }

        _takeImageButton.setOnClickListener {
            //check camera permission
            val permission = android.Manifest.permission.CAMERA

            if (ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED) {
                // Camera permission already granted
                takePictureLauncher.launch(null)
            } else {
                // Camera permission not granted, request it
                try {
                    requestPermissionAndOpenCameraLauncher.launch(permission)
                } catch (e: Exception) {
                    // Handle any exceptions that may occur
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                    Log.e("Err", "Failed to request camera permission", e)
                }
            }
        }

        // addBrand action
        _addModelButton.setOnClickListener{
            try{
                if (_brandNameInput.text.toString() == ""){
                    Toast.makeText(this.context, getString(R.string.add_brand_name_fail), Toast.LENGTH_SHORT).show()
                }
                else if (_brandService.getBrandByName(_brandNameInput.text.toString()) == null){
                    Toast.makeText(this.context, getString(R.string.add_model_invalid_brand_fail), Toast.LENGTH_SHORT).show()
                }
                else if (_modelNameInput.text.toString() == ""){
                    Toast.makeText(this.context, getString(R.string.add_model_name_fail), Toast.LENGTH_SHORT).show()
                }
                else if (_bodyTypeInput.text.toString() == ""){
                    Toast.makeText(this.context, getString(R.string.add_model_bodytype_fail), Toast.LENGTH_SHORT).show()
                }
                else if (_yearInput.text.toString() == ""){
                    Toast.makeText(this.context, getString(R.string.add_model_year_fail), Toast.LENGTH_SHORT).show()
                }
                else if (_seatsInput.text.toString() == ""){
                    Toast.makeText(this.context, getString(R.string.add_model_seats_fail), Toast.LENGTH_SHORT).show()
                }
                else if(_selectedImage == null){
                    Toast.makeText(this.context, getString(R.string.add_brand_image_fail), Toast.LENGTH_SHORT).show()
                }
                else{
                    _modelService.addModel(Model(_brandNameInput.text.toString(), _modelNameInput.text.toString(),
                    _bodyTypeInput.text.toString(), Integer.parseInt(_seatsInput.text.toString()),
                    Integer.parseInt(_yearInput.text.toString()), _descriptionInput.text.toString(),
                        _selectedImage!!
                    ))
                    Toast.makeText(this.context, getString(R.string.add_model_success), Toast.LENGTH_SHORT).show()
                }
            }
            catch (ex: Exception){
                Toast.makeText(this.context, ex.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    class TakePicture : ActivityResultContract<Uri?, Uri?>() {
        private var outputUri: Uri? = null

        override fun createIntent(context: Context, input: Uri?): Intent {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            outputUri = createImageFile(context)?.let { FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", it) }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri)
            return intent
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            return outputUri
        }

        private fun createImageFile(context: Context): File? {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "IMG_$timeStamp"
            val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            return File.createTempFile(fileName, ".jpg", storageDir)
        }
    }
}