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


class Frag_Gyeonggi(private val dest_keyword: String?,private val dest_address:String?, private val rushHour: Int) : Fragment() {

    var PlaceList =ArrayList<Place_Item_t>()
    var Adapter: ListAdapter_t?=null
    var time:String?=null
    private var rush:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rush=rushHour
        PlaceList.add(
            Place_Item_t("경기 고양시 덕양구 삼송동", "50분이내",
                "매매 5억2천만원 ~", "전세 3억8천만원 ~", "월세 300/45만원 ~")
        )
        PlaceList.add(
            Place_Item_t("경기도 고양시 덕양구 행신동", "50분이내",
                "매매 1억5천만원 ~", "전세 9천500만원 ~", "월세 1000/50만원 ~")
        )
        PlaceList.add(
            Place_Item_t("경기도 과천시 부림동", "50분이내",
                "매매 9억 ~", "전세 4억2천만원 ~", "월세 2억5천/80만원 ~")
        )
        PlaceList.add(
            Place_Item_t("경기도 과천시 중앙동", "50분이내",
                "매매 12억 ~", "전세 6억5천만원 ~", "월세 2억5천/80만원 ~")
        )
        PlaceList.add(
            Place_Item_t("경기도 광명시 일직동", "50분이내",
                "매매 8억1천만원 ~", "전세 3억5천만원 ~", "월세 5000/120만원 ~")
        )
        PlaceList.add(
            Place_Item_t("경기도 광명시 철산동", "50분이내",
                "매매 2억 7천만원 ~", "전세 4천만원 ~", "월세 200/40만원 ~")
        )
        PlaceList.add(
            Place_Item_t("경기도 구리시 교문동", "50분이내",
                "매매 3억 ~", "전세 1억 4천만원 ~", "월세 2000/75만원 ~")
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
