package com.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
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
    private lateinit var navController: NavController
    private val brandService : BrandService by inject()
    private val modelService : ModelService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize koin
        startKoin {
            modules(appModule)
        }

        //initialize brands
        brandService.setBrands(brandService.getDefaultBrands(this))
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

            navController = findNavController(R.id.nav_host_fragment_content_main)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_main -> {
                        navController.navigate(R.id.BrandsFragment)
                    }
                    R.id.action_add_brand -> {
                        navController.navigate(R.id.AddBrandFragment)
                    }
                    R.id.action_add_model -> {
                        navController.navigate(R.id.AddModelFragment)
                    }
                }
                true
            }

        }
    }

    //bind menu icon to set the nav drawer toggle
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return toggle.onOptionsItemSelected(item)
    }
}