package com.example.greenlighassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.greenlighassignment.databinding.AdapterItemLayoutZoneBinding
import com.example.greenlighassignment.model.SalesRegion
import com.example.greenlighassignment.ui.AreaActivity

class RVAdapterZone(val context: Context, val salesZone: ArrayList<SalesRegion>) :
    RecyclerView.Adapter<RVAdapterZone.MyViewHolder>() {

    inner class MyViewHolder(var binding: ViewDataBinding?):RecyclerView.ViewHolder(binding!!.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = AdapterItemLayoutZoneBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding as AdapterItemLayoutZoneBinding
        val salesZone = salesZone[position]

        binding.tvData.text = salesZone.region


    binding.root.setOnClickListener {
        val intent = Intent(context, AreaActivity::class.java)
        context.startActivity(intent)
    }


    }

    override fun getItemCount(): Int {
        return salesZone.size
    }

}