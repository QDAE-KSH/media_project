package com.example.myroom.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.example.myroom.R
import com.example.myroom.Place_Item
import com.example.myroom.ResultActivity

class ListAdapter (val context: Context?, val PlaceList: ArrayList<Place_Item>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_user, null)
        val address = view.findViewById<TextView>(R.id.address)
        val rushHour = view.findViewById<TextView>(R.id.rushHour)
        val place = PlaceList[position]
        val detail=view.findViewById<TextView>(R.id.textView)


        address.text = place.address
        rushHour.text = place.rushHour
        return view
    }


    override fun getItem(position: Int): Any {
        return PlaceList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return PlaceList.size
    }
}