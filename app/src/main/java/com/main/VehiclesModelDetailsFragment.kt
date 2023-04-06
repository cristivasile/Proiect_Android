package com.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.androidproject.R
import com.main.sevices.ModelService
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 * Use the [VehiclesModelDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VehiclesModelDetailsFragment : Fragment() {

    private lateinit var _backButton: Button
    private lateinit var _navController: NavController
    private lateinit var _titleTextView: TextView

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

        _backButton.setOnClickListener{
            _navController.navigate(R.id.action_vehicleModelDetailsFragment_to_ModelsFragment)
        }

        val model = _modelService.getSelectedModel()

        _titleTextView =view.findViewById<View>(R.id.modelDetailsTitle) as TextView
        _titleTextView.text = buildString {
            append(model.brandName)
            append(" ")
            append(model.modelName)
        }
        
    }

    override fun onResume() {
        super.onResume()
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = getString(R.string.model_details_actionbar_title)
    }
}