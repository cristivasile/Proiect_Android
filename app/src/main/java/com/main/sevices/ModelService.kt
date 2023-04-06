package com.main.sevices

import com.main.models.Brand
import com.main.repositories.IModelRepository;
import com.main.models.Model

class ModelService(private val modelRepository: IModelRepository) {
    fun addModel(model: Model) = modelRepository.addModel(model, true)
    fun getDefaultModels(): ArrayList<Model> = modelRepository.getDefault()
    fun getModelsByBrand(brand: Brand): ArrayList<Model> = modelRepository.getModelsByBrand(brand)
    fun setModels(models: ArrayList<Model>) = modelRepository.setModels(models)
}
