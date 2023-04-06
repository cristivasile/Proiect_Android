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

    private lateinit var _binding: VehiclesActivityBinding
    private lateinit var _toggle: ActionBarDrawerToggle
    private lateinit var _navController: NavController
    private val _brandService : BrandService by inject()
    private val _modelService : ModelService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize koin
        startKoin {
            modules(appModule)
        }

        //initialize brands
        _brandService.setBrands(_brandService.getDefaultBrands(this)) // pass context to
        //initialize models
        _modelService.setModels(_modelService.getDefaultModels())

        _binding = VehiclesActivityBinding
            .inflate(layoutInflater)
        setContentView(_binding.root)

        setSupportActionBar(_binding.toolbar)

        //initialize nav drawer
        _binding.apply{
            _toggle = ActionBarDrawerToggle(this@VehiclesActivity, navDrawerLayout, R.string.open, R.string.close)
            navDrawerLayout.addDrawerListener(_toggle)
            _toggle.syncState()

            //add menu icon to the action bar
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            _navController = findNavController(R.id.nav_host_fragment_content_main)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_main -> {
                        _navController.navigate(R.id.BrandsFragment)
                    }
                    R.id.action_add_brand -> {
                        _navController.navigate(R.id.AddBrandFragment)
                    }
                    R.id.action_add_model -> {
                        _navController.navigate(R.id.AddModelFragment)
                    }
                }
                true
            }

        }
    }

    //bind menu icon to set the nav drawer toggle
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return _toggle.onOptionsItemSelected(item)
    }
}