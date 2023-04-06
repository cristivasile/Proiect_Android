package com.main.sevices

import android.content.Context
import com.main.models.Brand
import com.main.repositories.IBrandRepository

class BrandService(private val brandRepository: IBrandRepository) {
    fun addBrand(brand: Brand) = brandRepository.addBrand(brand, true)
    fun getBrands(): ArrayList<Brand> = brandRepository.getAll()
    fun getBrandByName(name: String): Brand? = brandRepository.getBrandByName(name)
    fun getSelectedBrand() : Brand = brandRepository.getSelectedBrand()
    fun getDefaultBrands(context: Context): ArrayList<Brand> = brandRepository.getDefault(context)
    fun setBrands(brands: ArrayList<Brand>) = brandRepository.setBrands(brands)
    fun setSelectedBrand(brand: Brand) = brandRepository.setSelectedBrand(brand.name    )
}