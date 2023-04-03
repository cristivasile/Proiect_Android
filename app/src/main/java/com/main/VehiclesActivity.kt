package com.main

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidproject.R
import com.example.androidproject.databinding.VehiclesActivityBinding
import com.main.repositories.appModule
import com.main.sevices.BrandService
import com.main.sevices.ModelService
import org.koin.android.ext.android.inject
import org.koin.core.context.GlobalContext.startKoin

class VehiclesActivity : AppCompatActivity() {

    private lateinit var binding: VehiclesActivityBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private val brandService : BrandService by inject()
    private val modelService : ModelService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize koin
        startKoin {
            modules(appModule)
        }

        //initialize brands
        brandService.setBrands(brandService.getDefaultBrands())
        //initialize models
        modelService.setModels(modelService.getDefaultModels())

        binding = VehiclesActivityBinding
            .inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        //initialize nav drawer
        binding.apply{
            toggle = ActionBarDrawerToggle(this@VehiclesActivity, navDrawerLayout, R.string.open, R.string.close)
            navDrawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            //add menu icon to the action bar
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_main -> {
                        Log.d("Debug", "Main action clicked")
                    }
                    R.id.action_add_brand -> {
                        Log.d("Debug", "Add brand action clicked")
                    }
                    R.id.action_add_model -> {
                        Toast.makeText(this@VehiclesActivity, "First Item Clicked", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }

        }

    }

    //make options item open the navbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return toggle.onOptionsItemSelected(item)
    }
}