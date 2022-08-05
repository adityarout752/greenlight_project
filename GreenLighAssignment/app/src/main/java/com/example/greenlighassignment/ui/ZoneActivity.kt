package com.example.greenlighassignment.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.greenlighassignment.R
import com.example.greenlighassignment.adapter.RVAdapterZone
import com.example.greenlighassignment.databinding.ActivityZoneBinding
import com.example.greenlighassignment.model.SalesRegion
import com.example.greenlighassignment.viewmodel.MainViewModel

class ZoneActivity : AppCompatActivity(){
    private var binding:ActivityZoneBinding? = null
     var mainViewModel : MainViewModel? = null
    var zoneAdapter : RVAdapterZone? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_zone)
        setUpViewModel()
        readDb()
    }

    private fun readDb() {
        mainViewModel?.allSalesdata?.observe(this){
            Log.d("fromDB","$it")
            if(it.isNotEmpty()) {
                setRV(it[0].modelData.ResponseData.sales_region)
                Log.d("fromDB","DB")
            }else {
                Log.d("fromDB","API")
                callApiData()
            }
        }
    }

    private fun callApiData() {

        mainViewModel?.callApi()
     mainViewModel?.responseData?.observe(this) {it ->

         if (it.data?.ResponseStatus == 200) {
             setRV(it.data.ResponseData.sales_region)


         } else{
             Toast.makeText(this,"Api Failure",Toast.LENGTH_SHORT).show()
         }
     }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRV(salesRegion: ArrayList<SalesRegion>) {
        if(zoneAdapter == null) {
             zoneAdapter = RVAdapterZone(this, salesRegion)
            binding?.rvZone?.adapter = zoneAdapter
        }else{
            zoneAdapter!!.notifyDataSetChanged()
        }
    }

    private fun setUpViewModel() {

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }
}