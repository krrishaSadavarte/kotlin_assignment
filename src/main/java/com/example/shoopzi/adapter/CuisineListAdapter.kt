package com.example.shoopzi.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoopzi.Models.Profile
import com.example.shoopzi.Utils.Utils
import com.example.shoopzi.databinding.ItemCuisineLayoutBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class CuisineListAdapter(var context: Context,var cuisineList:MutableList<Profile>):Adapter<CuisineListAdapter.MyViewHolder>() {
    class MyViewHolder (var bind: ItemCuisineLayoutBinding):ViewHolder(bind.root)


    var itemEditClicklistener:((position:Int,cuisine : Profile)->Unit)? = null
    var itemDelClicklistener:((position:Int,cuisine : Profile)->Unit)? = null

    var num :Int = 10
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = ItemCuisineLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cuisineList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var profile = cuisineList[position]
        holder.bind.TvName.text = profile.U_name
        holder.bind.TvBio.text = profile.U_bio
        if(profile.U_img!=null){
            holder.bind.ivThumbnail.setImageBitmap(Utils.getBitmapFromAbsolutePath(profile.U_img))
        }

        holder.bind.ivEdit.setOnClickListener {
            itemEditClicklistener?.invoke(position,profile)
        }


        holder.bind.ivDelete.setOnClickListener {
            itemDelClicklistener?.invoke(position,profile)
        }
    }




    fun setItems(mutableList: MutableList<Profile>){
        this.cuisineList = mutableList
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int){
        cuisineList.removeAt(position)
        notifyItemRemoved(position)
    }


}