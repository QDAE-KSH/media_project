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
import com.example.myroom.*
import com.example.myroom.Adapter.ListAdapter_t
import kotlinx.android.synthetic.main.item_user_t.*
import kotlinx.android.synthetic.main.item_user_t.view.*
import kotlin.collections.ArrayList


class Frag_Seoul_t(private val dest_keyword: String?,private val dest_address:String?, private val rushHour: Int) : Fragment() {

    var PlaceList =ArrayList<Place_Item_t>()
    var Adapter: ListAdapter_t?=null
    var time:String?=null
    private var rush:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rush=rushHour
        PlaceList.add(
            Place_Item_t("서울 중구 서소문동", "10분이내",
                "매매 5억3천만원 ~", "전세 2억4천만원 ~", "월세 1000/80만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 동대문구 신설동", "20분이내",
                "매매 6억2천만원 ~", "전세 1억4천만원 ~", "월세 1000/63만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 마포구 노고산동", "20분이내",
                "매매2억7천만원 ~", "전세 2억4천만원 ~", "월세 1000/90만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 마포구 아현동", "20분이내",
                "매매 3억8천만원 ~", "전세 1억8천만원 ~", "월세 4000/150만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 서대문구 북아현동", "20분이내",
                "매매 6억1천만원 ~", "전세 2억7천만원 ~", "월세 1000/100만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 서대문구 합동", "20분이내",
                "매매 5억 7천만원 ~", "전세 5억 8천만원 ~", "월세 3,000/200만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 성동구 상왕십리동", "20분이내",
                "매매2억 5천만원 ~", "전세 5억 9천만원 ~", "월세 1000/85만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 성동구 하왕십리동", "20분이내",
                "매매 1억 3천만원 ~", "전세 3억 2천만원 ~", "월세 1000/60만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 용산구 동자동", "20분이내",
                "매매 5억 6천만원 ~", "전세 9억 5천만원 ~", "월세 0만원/310만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 종로구 견지동", "20분이내",
                "매매 12억 ~", "전세 7억 5천만원 ~", "월세 5000/350만원 ~")
        )
        //--------------------------------------------------------------
        PlaceList.add(
            Place_Item_t("서울 중구 서소문동", "20분이내",
                "매매 5억3천만원 ~", "전세 2억4천만원 ~", "월세 1000/80만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 동대문구 신설동", "20분이내",
                "매매 6억2천만원 ~", "전세 1억4천만원 ~", "월세 1000/63만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 마포구 노고산동", "20분이내",
                "매매2억7천만원 ~", "전세 2억4천만원 ~", "월세 1000/90만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 마포구 아현동", "20분이내",
                "매매 3억8천만원 ~", "전세 1억8천만원 ~", "월세 4000/150만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 서대문구 북아현동", "20분이내",
                "매매 6억1천만원 ~", "전세 2억7천만원 ~", "월세 1000/100만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 서대문구 합동", "20분이내",
                "매매 5억 7천만원 ~", "전세 5억 8천만원 ~", "월세 3,000/200만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 성동구 상왕십리동", "20분이내",
                "매매2억 5천만원 ~", "전세 5억 9천만원 ~", "월세 1000/85만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 성동구 하왕십리동", "20분이내",
                "매매 1억 3천만원 ~", "전세 3억 2천만원 ~", "월세 1000/60만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 용산구 동자동", "20분이내",
                "매매 5억 6천만원 ~", "전세 9억 5천만원 ~", "월세 0만원/310만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 종로구 견지동", "30분이내",
                "매매 12억 ~", "전세 7억 5천만원 ~", "월세 5000/350만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 중구 서소문동", "30분이내",
                "매매 5억3천만원 ~", "전세 2억4천만원 ~", "월세 1000/80만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 동대문구 신설동", "30분이내",
                "매매 6억2천만원 ~", "전세 1억4천만원 ~", "월세 1000/63만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 마포구 노고산동", "30분이내",
                "매매2억7천만원 ~", "전세 2억4천만원 ~", "월세 1000/90만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 마포구 아현동", "30분이내",
                "매매 3억8천만원 ~", "전세 1억8천만원 ~", "월세 4000/150만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 서대문구 북아현동", "30분이내",
                "매매 6억1천만원 ~", "전세 2억7천만원 ~", "월세 1000/100만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 서대문구 합동", "40분이내",
                "매매 5억 7천만원 ~", "전세 5억 8천만원 ~", "월세 3,000/200만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 성동구 상왕십리동", "40분이내",
                "매매2억 5천만원 ~", "전세 5억 9천만원 ~", "월세 1000/85만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 성동구 하왕십리동", "40분이내",
                "매매 1억 3천만원 ~", "전세 3억 2천만원 ~", "월세 1000/60만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 용산구 동자동", "40분이내",
                "매매 5억 6천만원 ~", "전세 9억 5천만원 ~", "월세 0만원/310만원 ~")
        )
        PlaceList.add(
            Place_Item_t("서울 종로구 견지동", "50분이내",
                "매매 12억 ~", "전세 7억 5천만원 ~", "월세 5000/350만원 ~")
        )


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
            intent.putExtra("rushHour", view.rushHour.text)
            intent.putExtra("dest_keyword", dest_keyword)
            intent.putExtra("dest_address",dest_address)
            startActivity(intent)
        }
        Adapter = ListAdapter_t(context, PlaceList)
        listView.adapter = Adapter
        return view
    }
}
