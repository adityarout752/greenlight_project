package com.example.greenlighassignment.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.greenlighassignment.R
import com.example.greenlighassignment.adapter.RVAdapterRegion
import com.example.greenlighassignment.databinding.ActivityRegionBinding
import com.example.greenlighassignment.model.SalesCountry
import com.example.greenlighassignment.viewmodel.MainViewModel

class RegionActivity : AppCompatActivity() {
    private var binding: ActivityRegionBinding? = null

    var mainViewModel: MainViewModel? = null
    var rezionAdapter: RVAdapterRegion? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_region)
        setUpViewModel()
        callApiData()
    }

    private fun callApiData() {

        mainViewModel?.callApi()
        mainViewModel?.responseData?.observe(this) { it ->

            if (it.data?.ResponseStatus == 200) {

                setRV(it.data.ResponseData.sales_country)

            } else {
                Toast.makeText(this, "Api Failure", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRV(salesRegion: ArrayList<SalesCountry>) {
        if(rezionAdapter == null) {
            rezionAdapter = RVAdapterRegion(this, salesRegion)
            binding?.rvRegion?.adapter = rezionAdapter
        }else {
            rezionAdapter!!.notifyDataSetChanged()
        }

    }

    private fun setUpViewModel() {

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG","onSTOP")
    }
    override fun onResume() {
        super.onResume()
        Log.d("TAG","onresume")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG","ONDESTROY")
    }
}