package com.example.greenlighassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.greenlighassignment.databinding.AdapterItemLayoutRegionBinding
import com.example.greenlighassignment.model.SalesCountry
import com.example.greenlighassignment.ui.AreaActivity

class RVAdapterRegion(val context: Context, val salesZone: ArrayList<SalesCountry>) :
    RecyclerView.Adapter<RVAdapterRegion.MyViewHolder>() {

    inner class MyViewHolder(var binding: ViewDataBinding?):RecyclerView.ViewHolder(binding!!.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = AdapterItemLayoutRegionBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding as AdapterItemLayoutRegionBinding
        val salesZone = salesZone[position]

        binding.tvData.text = salesZone.country






    }

    override fun getItemCount(): Int {
        return salesZone.size
    }

}