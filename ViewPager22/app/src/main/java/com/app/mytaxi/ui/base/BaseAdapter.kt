package com.app.mytaxi.ui.base

import androidx.recyclerview.widget.RecyclerView
import com.app.mytaxi.data.model.Area

/**
 * Created by Ajay Deepak on 19-11-2019, 17:52
 */

abstract class BaseAdapter<T: Area, VH: CommonViewHolder>(
    private val dataList: ArrayList<T>): RecyclerView.Adapter<VH>(){


    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(dataList[position])
    }


    fun appendData(dataList: List<T>){
        val oldList = itemCount
        this.dataList.addAll(dataList)
        val currentList = itemCount

        if(oldList == 0 && currentList > 0){
            notifyDataSetChanged()
        } else if(oldList in 1 until currentList){
            notifyItemRangeChanged(oldList -1, currentList - oldList)
        }
    }


}