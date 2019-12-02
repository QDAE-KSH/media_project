package com.example.myroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.myroom.network.Detail
import com.example.myroom.network.RetrofitNetwork
import kotlinx.android.synthetic.main.activity_detail.*
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback


class DetailActivity: AppCompatActivity() {
    private var mMap: GoogleMap? = null
    private var mapFragment:SupportMapFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var intent = intent
        var rushHour = intent.getStringExtra("rushHour")
        var source = intent.getStringExtra("source")
        var dest_keyword = intent.getStringExtra("dest_keyword")
        var dest_address = intent.getStringExtra("dest_address")
        txt_source.setText(source)
        txt_dest.setText(dest_keyword + "  " + rushHour)
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(object : OnMapReadyCallback {
            override fun onMapReady(googleMap: GoogleMap) {
                mMap = googleMap
                //최초 표시 부분
                //없으면 지도가 회색으로 나온다.
                // 서울역으로
                val latLng=LatLng(37.57496296167424, 127.02553857099582)
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                //지도 Zoom 정도
                googleMap.animateCamera(CameraUpdateFactory.zoomTo(15f))
                val marker = MarkerOptions()
                //마커에 넣을 정보
                //snippet은 subtitle같은 것이다
                marker.position(latLng).title("신설동")
                googleMap.addMarker(marker)
                //마커를 지도에 표기

            }
        })





        /*
        //api 호출
        val retrofit = Retrofit.Builder().baseUrl("http://13.209.79.196:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(RetrofitNetwork::class.java)
        service.get_detail(source,dest_address)?.enqueue(object : Callback<Detail> {
            override fun onFailure(call: Call<Detail>, t: Throwable) {
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                Log.d("Response :: ", response?.body().toString())
                var data: Detail? = response?.body()
                var coord=data?.coord
                var rushHour=data?.rushHour
                var price=data?.price
                val rent_m_max=price?.rent_m_max
                Log.i("rent_m_max", rent_m_max)
                //var json_data=JSONObject(str_data)

            }
        })
*/
    }

}