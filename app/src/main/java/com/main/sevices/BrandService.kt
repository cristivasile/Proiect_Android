package com.main.sevices

import com.main.models.Brand
import com.main.repositories.IBrandRepository

class BrandService(private val brandRepository: IBrandRepository) {

    fun getDefaultBrands(): ArrayList<Brand> = brandRepository.getDefault()
    fun getBrands(): ArrayList<Brand> = brandRepository.getAll()
    fun setBrands(brands: ArrayList<Brand>) = brandRepository.setBrands(brands)
}