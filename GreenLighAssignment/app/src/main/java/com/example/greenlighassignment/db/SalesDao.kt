package com.example.greenlighassignment.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SalesDao{

@Insert
suspend fun insertSales(entityClass: EntityClass)

@Query("Select * From sales_table  ORDER BY id ASC")
fun getAllSales(): LiveData<List<EntityClass>>
}