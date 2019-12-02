package com.example.myroom.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.myroom.Place_Item_t
import com.example.myroom.R
import com.example.myroom.ResultActivity
import kotlinx.android.synthetic.main.item_user_t.view.*

class ListAdapter_t (val context: Context?, val PlaceList: ArrayList<Place_Item_t>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var token=1
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_user_t, null)
        val address = view.findViewById<TextView>(R.id.address)
        val rushHour = view.findViewById<TextView>(R.id.rushHour)
        val price_s=view.findViewById<TextView>(R.id.price_s)
        val price_r=view.findViewById<TextView>(R.id.price_r)
        val price_m=view.findViewById<TextView>(R.id.price_m)
        val place = PlaceList[position]


        address.text = place.address
        rushHour.text = place.rushHour
        price_s.text=place.sell
        price_r.text=place.rent
        price_m.text=place.rent_m

        var strVal=address.text.toString()
        var listVal=strVal.split(" ")
        when (listVal[1]){
            "중구"->view.imageIcon.setImageResource(R.drawable.gunggu)
            "동대문구"->view.imageIcon.setImageResource(R.drawable.dongdaemoon)
            "마포구"->view.imageIcon.setImageResource(R.drawable.mapogu)
            "성동구"->view.imageIcon.setImageResource(R.drawable.seodaemoongu)
            "서대문구"->view.imageIcon.setImageResource(R.drawable.seodaemoongu)
            "용산구"->view.imageIcon.setImageResource(R.drawable.yongsangu)
            "종로구"->view.imageIcon.setImageResource(R.drawable.jongrogu)
            "고양시"->view.imageIcon.setImageResource(R.drawable.goyang)


        }
        view.img_star.setOnClickListener(View.OnClickListener { view->
            if (token ==1){
                view.img_star.setImageResource(R.drawable.star_yellow)
                token=0
            }
            else{
                view.img_star.setImageResource(R.drawable.star_grary)
                token=1
            }
        })
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