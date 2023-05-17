package com.example.finalproject


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class RentalsAdapter(var context: Context, var data:ArrayList<Ulpoad>):BaseAdapter() {
    private class ViewHolder(row:View?){
        var mTxtAddress:TextView
        var mTxtName1:TextView
        var mTxtPhoneNumber:TextView
        var mTxtRentalFee:TextView
        var mImage:ImageView
        init {
            this.mTxtAddress = row?.findViewById(R.id.mTvAddress) as TextView
            this.mTxtName1 = row?.findViewById(R.id.mTvName1) as TextView
            this.mTxtPhoneNumber = row?.findViewById(R.id.mTvPhoneNumber) as TextView
            this.mTxtRentalFee= row?.findViewById(R.id.mTvRentalFee) as TextView
            this.mImage = row?.findViewById(R.id.mImgPic) as ImageView
        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.item_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:Ulpoad = getItem(position) as Ulpoad
        viewHolder.mTxtAddress.text = item.address
        viewHolder.mTxtName1.text = item.name
        viewHolder.mTxtPhoneNumber.text = item.phoneNumber
        viewHolder.mTxtRentalFee.text = item.rentalFee
        Glide.with(context).load(item.image).into(viewHolder.mImage)
        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}