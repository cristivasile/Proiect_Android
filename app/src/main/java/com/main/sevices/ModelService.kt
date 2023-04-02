package com.main.sevices

import com.main.repositories.IModelRepository;
import com.main.models.Model

class ModelService(private val modelRepository: IModelRepository) {
    fun getDefaultModels(): ArrayList<Model> = modelRepository.getDefault()
    fun getModels(): ArrayList<Model> = modelRepository.getAll()
    fun setModels(models: ArrayList<Model>) = modelRepository.setModels(models)
}
