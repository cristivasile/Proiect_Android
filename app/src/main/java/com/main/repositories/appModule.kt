package com.main.repositories

import com.main.sevices.BrandService
import org.koin.dsl.module

val appModule = module {
    single<IBrandRepository> { BrandRepository() }
    single { BrandService(get())}
}