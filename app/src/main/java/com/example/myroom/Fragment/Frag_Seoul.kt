package com.example.myroom.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myroom.Adapter.ListAdapter
import com.example.myroom.DetailActivity
import com.example.myroom.Place_Item
import com.example.myroom.R
import com.example.myroom.ResultActivity
import com.example.myroom.network.RetrofitNetwork
import com.google.gson.Gson
import com.mongodb.util.JSON
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.item_user.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.Collections.list

class Frag_Seoul(private val destination: String?, private val rushHour: Int) : Fragment() {

    private var PlaceList=ArrayList<Place_Item>()
    var Adapter: ListAdapter?=null
    private var rush:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rush=rushHour

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_seoul, container, false)
        var listView=view.findViewById(R.id.list_view) as ListView
        listView.setOnItemClickListener { adapterView, view, i, l ->
            var intent=Intent(context,DetailActivity::class.java)
            intent.putExtra("source",view.address.text)
            intent.putExtra("destination", destination)
            startActivity(intent)
        }
        var raw_data= resources.openRawResource(R.raw.seoul).bufferedReader().use { it.readText() }
        var json_object=JSONObject(raw_data)
        //json_object.get("dist_10")
        for (i in 10..90 step 10) {
            if(i>rush){
                break
            }
            val placeList = mutableListOf(json_object.get("dist_" + i.toString()))
            for (place in placeList) {
                var plist=place.toString()
                plist=plist.substring(1,plist.length-1)
                var list_plist= plist.split(",")
                Log.i("sentence",list_plist.toString())
                for(item in list_plist){
                    if(item==""){
                        continue
                    }
                    var new_item=item.toString()
                    new_item=new_item.substring(1,item.length-1)
                    PlaceList.add(Place_Item(new_item,i.toString()+"분 이내 통근 가능"))
                }

            }
        }

        Adapter = ListAdapter(context, PlaceList)
        listView.adapter = Adapter
        return view
    }
}
