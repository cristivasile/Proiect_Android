package com.main.models

import android.graphics.Bitmap
import com.example.androidproject.R

class Model(val brand: String, model: String, bodyType: String, nrOfSeats: Int, startYear: Int
    , image: Bitmap)
{
    fun createModelsList() : ArrayList<Model> {
        val models = ArrayList<Model>()

        models.add(Model("Audi", "TT 8N", "Coupe", nrOfSeats = 4, startYear = 1999,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Audi", "TT 8J", "Coupe", nrOfSeats = 4, startYear = 2006,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Audi", "TT 8S", "Coupe", nrOfSeats = 4, startYear = 2014,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Audi", "A4 B6", "Sedan", nrOfSeats = 5, startYear = 2000,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Audi", "A4 B6 Avant", "Wagon", nrOfSeats = 5, startYear = 2001,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Audi", "A4 B8", "Coupe", nrOfSeats = 5, startYear = 2007,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Dacia", "Logan", "Sedan", nrOfSeats = 5, startYear = 2005,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Daewoo", "Cielo", "Sedan", nrOfSeats = 5, startYear = 1994,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Daewoo", "Matiz", "Compact", nrOfSeats = 5, startYear = 1998,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Mercedes-Benz", "C Class W203", "Sedan", nrOfSeats = 5, startYear = 2001,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Mercedes-Benz", "S Class W223", "Limousine", nrOfSeats = 5, startYear = 2020,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Mercedes-Benz", "G Class W463", "Off-road", nrOfSeats = 5, startYear = 2012,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Mercedes-Benz", "GLE V167", "SUV", nrOfSeats = 7, startYear = 2019,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));
        models.add(Model("Citroen", "DS III Break", "Wagon", nrOfSeats = 5, startYear = 1972,
            Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)));

        return models
    }
}