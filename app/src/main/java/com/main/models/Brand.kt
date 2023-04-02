package com.main.models

import com.example.androidproject.R

class Brand(val name: String, val imageResource: Int){
    companion object {
        fun createBrandsList() : ArrayList<Brand> {

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
}