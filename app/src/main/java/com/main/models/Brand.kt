package com.main.models

class Brand (val name: String, val imagePath: String){
    companion object {
        fun createBrandsList() : ArrayList<Brand> {

            val brands = ArrayList<Brand>()

            brands.add(Brand("Audi", ""))
            brands.add(Brand("Mercedes-Benz", ""))
            brands.add(Brand("BMW", ""))
            brands.add(Brand("Citroen", ""))
            brands.add(Brand("Peugeot", ""))
            brands.add(Brand("Dacia", ""))
            brands.add(Brand("Ford", ""))
            brands.add(Brand("Daewoo", ""))

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