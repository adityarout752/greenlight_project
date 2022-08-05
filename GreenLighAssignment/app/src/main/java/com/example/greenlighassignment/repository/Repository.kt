package com.example.greenlighassignment.repository

import androidx.lifecycle.LiveData
import com.example.greenlighassignment.db.EntityClass
import com.example.greenlighassignment.db.SalesDao
import com.example.greenlighassignment.remote.RetrofitInstance

class Repository(val salesDao: SalesDao) {

    suspend fun callApiData() = RetrofitInstance.api.callApi()

    val allSalesData: LiveData<List<EntityClass>> = salesDao.getAllSales()


    suspend fun insert(entityClass: EntityClass) {
       salesDao.insertSales(entityClass)
    }

}