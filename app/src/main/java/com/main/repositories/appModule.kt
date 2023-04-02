package com.main.repositories

import com.main.sevices.BrandService
import com.main.sevices.ModelService
import org.koin.dsl.module

val appModule = module {
    single<IBrandRepository> { BrandRepository() }
    single { BrandService(get()) }

    single<IModelRepository> { ModelRepository() }
    single { ModelService(get()) }
}