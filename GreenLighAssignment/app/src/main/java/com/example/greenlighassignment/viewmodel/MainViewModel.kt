package com.example.greenlighassignment.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.greenlighassignment.db.EntityClass

import com.example.greenlighassignment.model.ModelData
import com.example.greenlighassignment.remote.NetworkResponse
import com.example.greenlighassignment.repository.Repository
import com.example.greenlighassignment.db.SalesDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var repository: Repository
    val responseData: MutableLiveData<NetworkResponse<ModelData>> = MutableLiveData()
    var allSalesdata : LiveData<List<EntityClass>>


    init{
        val dao = SalesDB.getDatabase(application).salesDao()
        repository = Repository(dao)
        allSalesdata = (repository.allSalesData)
    }


    fun callApi() = viewModelScope.launch {
        val response = repository.callApiData()
        responseData.postValue(handleNetworkResponse(response))
    }

    private fun handleNetworkResponse(response: Response<ModelData>): NetworkResponse<ModelData> {
        if (response.isSuccessful) {
            if (response.code() == 200) {
                response.body()?.let { it ->
                    offlineCache(it)
                    return NetworkResponse.Success(it)
                }
            }
        }
        return NetworkResponse.Error(response.message())


    }

    private fun offlineCache(modelData: ModelData) {
        val entityClass = EntityClass(modelData)
        insertData(entityClass)
    }

    fun insertData(entityClass: EntityClass) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(entityClass)
    }
}


