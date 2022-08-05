package com.example.greenlighassignment.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.greenlighassignment.databinding.AdapterItemLayoutRegionBinding
import com.example.greenlighassignment.model.SalesArea
import com.example.greenlighassignment.ui.AreaActivity
import com.example.greenlighassignment.ui.RegionActivity
import java.util.*

class AreaAdapter(val context: Context, val areaList: ArrayList<SalesArea>) :
    RecyclerView.Adapter<AreaAdapter.MyViewHolder>() {


    inner class MyViewHolder(var binding: ViewDataBinding?):RecyclerView.ViewHolder(binding!!.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = AdapterItemLayoutRegionBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding as AdapterItemLayoutRegionBinding
        val area = areaList[position]


        binding.tvData.text = area.area
        binding.root.setOnClickListener {
            val intent = Intent(context, RegionActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return areaList.size
    }

    fun getFilter(): Filter? {
        return Searched_Filter
    }

    private val Searched_Filter: Filter = object : Filter() {
        protected override fun performFiltering(constraint: CharSequence?): FilterResults? {
            val filteredList: ArrayList<SalesArea> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(areaList)
            } else {
                val filterPattern =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in areaList) {
                    if (item.area.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            areaList.clear()
            areaList.addAll(results.values as Collection<SalesArea>)
            notifyDataSetChanged()
        }
    }

}