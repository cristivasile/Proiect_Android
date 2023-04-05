package com.main.sevices

import android.content.Context
import com.main.models.Brand
import com.main.repositories.IBrandRepository

class BrandService(private val brandRepository: IBrandRepository) {
    fun getDefaultBrands(context: Context): ArrayList<Brand> = brandRepository.getDefault(context)
    fun getBrands(): ArrayList<Brand> = brandRepository.getAll()
    fun setBrands(brands: ArrayList<Brand>) = brandRepository.setBrands(brands)
    fun addBrand(brand: Brand) = brandRepository.addBrand(brand)
}