package com.example.greenlighassignment.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.greenlighassignment.model.ModelData

@Entity(tableName = "sales_table")
class EntityClass(
    var modelData: ModelData
) {
    @PrimaryKey(autoGenerate = false)
    var id:Int =0
}