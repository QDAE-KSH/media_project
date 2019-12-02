package com.example.myroom.network

data class Detail(val coord:ArrayList<String>, val rushHour:String, val price:Price)
data class Price (val rent_m_max:String, val rent_m_min:String, val rent_max:String, val rent_min:String, val sell_max:String, val sell_min:String, val rushHour:String)