package com.example.myroom

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bookmark.*

class BookmarkActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)
        card_sk.setOnClickListener(View.OnClickListener { view->
            var intent= Intent(this,BookmarkDetail::class.java)
            startActivity(intent)
        })
    }
}