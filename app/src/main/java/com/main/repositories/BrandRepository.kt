package com.main.repositories

import com.example.androidproject.R
import com.main.models.Brand

interface IBrandRepository {
    fun getAll(): ArrayList<Brand>
    fun addBrands(brands : List<Brand>)
    fun setBrands(brands: List<Brand>)
    fun setSelectedBrand(brandName: String)
    fun getSelectedBrand(): Brand
    fun getDefault(): ArrayList<Brand>
}

class BrandRepository : IBrandRepository{

    private val _brands = arrayListOf<Brand>()
    private var _selectedBrand: Brand? = null

    override fun getAll(): ArrayList<Brand> {
        return _brands;
    }

    override fun addBrands(brands : List<Brand>) {
        _brands.addAll(brands)
    }

    override fun setBrands(brands: List<Brand>) {
        _brands.clear()
        _brands.addAll(brands)
    }

    override fun setSelectedBrand(brandName: String) {
        for (brand in _brands)
            if (brand.name.lowercase() == brandName.lowercase())
                _selectedBrand = brand

        throw Exception("No brand with the given name was found!")
    }

    override fun getSelectedBrand(): Brand {
        if(_selectedBrand == null)
            throw Exception("Selected brand was null!")

        return _selectedBrand as Brand
    }

    override fun getDefault(): ArrayList<Brand> {
        val brands = ArrayList<Brand>()

        brands.add(Brand("Audi", R.drawable.audi_logo))
        brands.add(Brand("Mercedes-Benz", R.drawable.mercedes_benz_logo))
        brands.add(Brand("BMW", R.drawable.bmw_logo))
        brands.add(Brand("Citroen", R.drawable.citroen_logo))
        brands.add(Brand("Peugeot", R.drawable.peugeot_logo))
        brands.add(Brand("Dacia", R.drawable.dacia_logo))
        brands.add(Brand("Ford", R.drawable.ford_logo))
        brands.add(Brand("Daewoo", R.drawable.daewoo_logo))
        brands.add(Brand("Fiat", R.drawable.fiat_automobiles_logo))
        brands.add(Brand("Ferrari", R.drawable.ferrari_logo))
        brands.add(Brand("Bugatti", R.drawable.bugatti_logo))
        brands.add(Brand("Lamborghini", R.drawable.lamborghini_logo))
        brands.add(Brand("Dodge", R.drawable.dodge_logo))
        brands.add(Brand("Chevrolet", R.drawable.chevrolet_logo))

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