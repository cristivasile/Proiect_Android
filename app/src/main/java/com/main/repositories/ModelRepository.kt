package com.main.repositories

import android.graphics.Bitmap
import com.main.models.Brand
import com.main.models.Model

interface IModelRepository {
    fun getAll(): ArrayList<Model>
    fun getModelsByBrand(brand: Brand): ArrayList<Model>
    fun addModels(models : List<Model>)
    fun setModels(models: List<Model>)
    fun getDefault(): ArrayList<Model>
}

class ModelRepository: IModelRepository {

    private val _models = arrayListOf<Model>()

    override fun getAll(): ArrayList<Model> {
        return _models;
    }

    override fun getModelsByBrand(brand: Brand): ArrayList<Model> {
        return ArrayList(_models.filter { model: Model
            -> model.brandName.lowercase() == brand.name.lowercase() })
    }

    override fun addModels(models: List<Model>) {
        _models.addAll(models)
    }

    override fun setModels(models: List<Model>) {
        _models.clear()
        _models.addAll(models)
    }

    override fun getDefault(): ArrayList<Model> {
        val models = ArrayList<Model>()

        models.add(Model("Audi", "TT 8N", "Coupe", nrOfSeats = 4, startYear = 1999,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Audi", "TT 8J", "Coupe", nrOfSeats = 4, startYear = 2006,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Audi", "TT 8S", "Coupe", nrOfSeats = 4, startYear = 2014,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Audi", "A4 B6", "Sedan", nrOfSeats = 5, startYear = 2000,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Audi", "A4 B6 Avant", "Wagon", nrOfSeats = 5, startYear = 2001,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Audi", "A4 B8", "Coupe", nrOfSeats = 5, startYear = 2007,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Dacia", "Logan", "Sedan", nrOfSeats = 5, startYear = 2005,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Daewoo", "Cielo", "Sedan", nrOfSeats = 5, startYear = 1994,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Daewoo", "Matiz", "Compact", nrOfSeats = 5, startYear = 1998,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Mercedes-Benz", "C Class W203", "Sedan", nrOfSeats = 5, startYear = 2001,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Mercedes-Benz", "S Class W223", "Limousine", nrOfSeats = 5, startYear = 2020,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Mercedes-Benz", "G Class W463", "Off-road", nrOfSeats = 5, startYear = 2012,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Mercedes-Benz", "GLE V167", "SUV", nrOfSeats = 7, startYear = 2019,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Citroen", "DS III Break", "Wagon", nrOfSeats = 5, startYear = 1972,
            "Sample description", Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));

        return models
    }

}