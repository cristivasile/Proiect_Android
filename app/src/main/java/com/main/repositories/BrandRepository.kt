package com.main.repositories

import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.drawable.toDrawable
import com.example.androidproject.R
import com.main.models.Brand

interface IBrandRepository {
    fun addBrand(brand : Brand, sort: Boolean = false)
    fun addBrands(brands : List<Brand>)
    fun getAll(): ArrayList<Brand>
    fun getBrandByName(name: String): Brand?
    fun getDefault(context: Context): ArrayList<Brand>
    fun getSelectedBrand(): Brand
    fun setBrands(brands: List<Brand>)
    fun setSelectedBrand(brandName: String)
}

class BrandRepository : IBrandRepository{

    private val _brands = arrayListOf<Brand>()
    private var _selectedBrand: Brand? = null

    override fun getAll(): ArrayList<Brand> {
        return ArrayList(_brands);
    }

    override fun getBrandByName(name: String): Brand? {
        for (brand in _brands)
            if(brand.name.lowercase() == name.lowercase())
                return brand
        return null
    }

    override fun addBrand(brand : Brand, sort: Boolean) {
        val filteredBrands = _brands.filter { x -> x.name.lowercase() == brand.name.lowercase() }

        if (filteredBrands.isNotEmpty())
            throw Exception("Brand already exists")

        _brands.add(brand);

        if (sort){
            val sortedBrands = _brands.sortedWith { brand1, brand2 ->
                when {
                    brand1.name > brand2.name -> 1
                    brand1.name < brand2.name -> -1
                    else -> 0
                }
            }
            _brands.clear()
            _brands.addAll(sortedBrands)
        }
    }

    override fun addBrands(brands : List<Brand>) {
        for (brand in brands)
            addBrand(brand, true)
    }

    override fun setBrands(brands: List<Brand>) {
        _brands.clear()
        addBrands(brands)
    }

    override fun setSelectedBrand(brandName: String) {
        for (brand in _brands)
            if (brand.name.lowercase() == brandName.lowercase()) {
                _selectedBrand = brand
                return
            }

        throw Exception("No brand with the given name was found!")
    }

    override fun getSelectedBrand(): Brand {
        if(_selectedBrand == null)
            throw Exception("Selected brand was null!")

        return _selectedBrand as Brand
    }

    override fun getDefault(context: Context): ArrayList<Brand> {
        val brands = ArrayList<Brand>()

        brands.add(Brand("Audi",
            BitmapFactory.decodeResource(context.resources, R.drawable.audi_logo)))
        brands.add(Brand("Mercedes-Benz",
            BitmapFactory.decodeResource(context.resources, R.drawable.mercedes_benz_logo)))
        brands.add(Brand("BMW",
            BitmapFactory.decodeResource(context.resources, R.drawable.bmw_logo)))
        brands.add(Brand("Citroen",
            BitmapFactory.decodeResource(context.resources, R.drawable.citroen_logo)))
        brands.add(Brand("Peugeot",
            BitmapFactory.decodeResource(context.resources, R.drawable.peugeot_logo)))
        brands.add(Brand("Dacia",
            BitmapFactory.decodeResource(context.resources, R.drawable.dacia_logo)))
        brands.add(Brand("Ford",
            BitmapFactory.decodeResource(context.resources, R.drawable.ford_logo)))
        brands.add(Brand("Daewoo",
            BitmapFactory.decodeResource(context.resources, R.drawable.daewoo_logo)))
        brands.add(Brand("Fiat",
            BitmapFactory.decodeResource(context.resources, R.drawable.fiat_automobiles_logo)))
        brands.add(Brand("Ferrari",
            BitmapFactory.decodeResource(context.resources, R.drawable.ferrari_logo)))
        brands.add(Brand("Bugatti",
            BitmapFactory.decodeResource(context.resources, R.drawable.bugatti_logo)))
        brands.add(Brand("Lamborghini",
            BitmapFactory.decodeResource(context.resources, R.drawable.lamborghini_logo)))
        brands.add(Brand("Dodge",
            BitmapFactory.decodeResource(context.resources, R.drawable.dodge_logo)))
        brands.add(Brand("Chevrolet",
            BitmapFactory.decodeResource(context.resources, R.drawable.chevrolet_logo)))

        //sort alphabetically
        val sortedBrands = brands.sortedWith { brand1, brand2 ->
            when {
                brand1.name > brand2.name -> 1
                brand1.name < brand2.name -> -1
                else -> 0
            }
        }

        return ArrayList(sortedBrands)
    }
}