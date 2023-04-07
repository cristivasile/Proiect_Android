package com.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.androidproject.R
import com.main.sevices.ModelService
import org.koin.android.ext.android.inject

class VehiclesModelDetailsFragment : Fragment() {

    private lateinit var _backButton: Button
    private lateinit var _shareButton: ImageButton
    private lateinit var _navController: NavController
    private lateinit var _titleTextView: TextView
    private lateinit var _bodyTypeTextView: TextView
    private lateinit var _startYearTextView: TextView
    private lateinit var _seatNrTextView: TextView
    private lateinit var _descriptionTextView: TextView
    private lateinit var _modelImage: ImageView

    private val _modelService: ModelService by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.vehicles_model_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _navController = this.findNavController()
        _backButton = view.findViewById<View>(R.id.modelDetailsBackButton) as Button
        _shareButton = view.findViewById<View>(R.id.shareButton) as ImageButton

        _backButton.setOnClickListener{
            _navController.navigate(R.id.action_vehicleModelDetailsFragment_to_ModelsFragment)
        }

        //get selected model using the service
        val model = _modelService.getSelectedModel()

        _shareButton.setOnClickListener{
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"

            val content =  buildString {
                append(model.brandName)
                append(" ")
                append(model.modelName)
                append("\n")
                append(model.bodyType)
                append("\n")
                append(model.nrOfSeats)
                append("\n")
                append(model.startYear)
                append("\n")
                append(model.description)
            }

            shareIntent.putExtra(Intent.EXTRA_TEXT, content)
            startActivity(Intent.createChooser(shareIntent, "Share"))
        }

        _titleTextView = view.findViewById<View>(R.id.modelDetailsTitle) as TextView
        _bodyTypeTextView = view.findViewById<View>(R.id.modelDetailsBodyType) as TextView
        _startYearTextView = view.findViewById<View>(R.id.modelDetailsStartYear) as TextView
        _seatNrTextView = view.findViewById<View>(R.id.modelDetailsSeatNr) as TextView
        _descriptionTextView = view.findViewById<View>(R.id.modelDetailsDescription) as TextView
        _modelImage = view.findViewById<View>(R.id.modelImage) as ImageView

        _modelImage.setImageBitmap(model.image)

        _titleTextView.text = buildString {
            append(model.brandName)
            append(" ")
            append(model.modelName)
        }

        _bodyTypeTextView.text = buildString {
            append(getString(R.string.body_type_string))
            append(": ")
            append(model.bodyType)
        }

        _startYearTextView.text = buildString {
            append(getString(R.string.start_year_string))
            append(": ")
            append(model.startYear)
        }

        _seatNrTextView.text = buildString {
            append(getString(R.string.seat_number_string))
            append(": ")
            append(model.nrOfSeats)
        }

        _descriptionTextView.text = buildString {
            append(getString(R.string.description_string))
            append(": ")
            append(model.description)
        }
    }

    override fun onResume() {
        super.onResume()
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = getString(R.string.model_details_actionbar_title)
    }
}