package com.example.greenlighassignment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.greenlighassignment.adapter.AreaAdapter
import com.example.greenlighassignment.R
import com.example.greenlighassignment.databinding.ActivityAreaBinding
import com.example.greenlighassignment.model.SalesArea
import com.example.greenlighassignment.viewmodel.MainViewModel

class AreaActivity : AppCompatActivity() {
    private var binding:ActivityAreaBinding? = null
    var mainViewModel : MainViewModel? = null
    var areaAdapter : AreaAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_area)

        setUpViewModel()
        readDb()
        searchText()
    }
    private fun readDb() {
        mainViewModel?.allSalesdata?.observe(this){
            if(it.isNotEmpty()) {
                Log.d("area","areaDB")
                setRV(it[0].modelData.ResponseData.sales_area)
            }else {
                callApiData()
            }
        }
    }

    private fun callApiData() {
        mainViewModel?.callApi()
        mainViewModel?.responseData?.observe(this) {it ->

            if (it.data?.ResponseStatus == 200) {
                setRV(it.data.ResponseData.sales_area)

            } else{
                Toast.makeText(this,"Api Failure", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRV(area: ArrayList<SalesArea>) {
        if(areaAdapter == null) {
            areaAdapter = AreaAdapter(this, area)
            binding?.rvArea?.adapter = areaAdapter
        }else{
            areaAdapter!!.notifyDataSetChanged()
        }
    }

    private fun setUpViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun searchText() {
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.d("newText1", query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.d("newText", newText)
                areaAdapter?.getFilter()?.filter(newText)
                return false
            }
        })
    }
    }


