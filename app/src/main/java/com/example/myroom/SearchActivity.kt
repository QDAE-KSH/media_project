package com.example.myroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import kotlinx.android.synthetic.main.activity_sub.*
import java.util.*

class SearchActivity:AppCompatActivity(){
    val AUTOCOMPLETE_REQUEST_CODE = 1
    var rush_hour:Int?=null
    var mode:String?=null
    var dest_keyword:String?=null
    var dest_address:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        seek_time.max=9
        seek_time.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var value:Int=p1*10
                rush_hour=value
                //Toast.makeText(applicationContext,rush_hour.toString()+"분",Toast.LENGTH_LONG).show()
                txt_time_val.setText(rush_hour.toString()+"분")
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        img_bookmark.setOnClickListener(View.OnClickListener { view->
            var intent=Intent(this,BookmarkActivity::class.java)
            startActivity(intent)
        })
        image_trans.setOnClickListener { view ->
            image_trans.setImageResource(R.drawable.pt_white)
            image_car.setImageResource(R.drawable.car_gray)
            image_walk.setImageResource(R.drawable.walk_gray)
        }
        image_car.setOnClickListener { view ->
            image_trans.setImageResource(R.drawable.pt_gray)
            image_car.setImageResource(R.drawable.car_white)
            image_walk.setImageResource(R.drawable.walk_gray)
        }
        image_walk.setOnClickListener { view ->
            image_trans.setImageResource(R.drawable.pt_gray)
            image_car.setImageResource(R.drawable.car_gray)
            image_walk.setImageResource(R.drawable.walk_white)
        }
        txt_dest.setOnClickListener(View.OnClickListener { view->
            Places.initialize(getApplicationContext(), getString(R.string.google_app_key))
            // Initialize the AutocompleteSupportFragment.
            val fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)
// Start the autocomplete intent.
            val intent = Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN, fields
            ).setCountry("KR")
                .build(this)
            startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
        })
        /*
        edit_dest.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                destination=edit_dest.text.toString()

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                destination=edit_dest.text.toString()
            }
        })
        */
        btn_search.setOnClickListener { view ->
            var intent=Intent(this,TestActivity::class.java)
            intent.putExtra("rushHour",rush_hour)
            intent.putExtra("dest_keyword",dest_keyword)
            intent.putExtra("dest_address",dest_address)
            startActivity(intent)
            img_bookmark.setImageResource(R.drawable.bookmark)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode === Activity.RESULT_OK) {
                if(data==null){

                }
                else{
                    val place = Autocomplete.getPlaceFromIntent(data)
                    Log.i("Place: " , place.name + ", " + place.id)
                    val address:String?=place.address.toString()
                    Log.i("addresss:", place.toString())
                    dest_keyword=place.name
                    dest_address=place.address
                    txt_dest.setText(place.name)
                    txt_dest_d.setText(address)
                }

            } else if (resultCode === AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                if(data==null){}
                else{
                    val status = Autocomplete.getStatusFromIntent(data)
                    Log.i("ddd","status.statusMessage!!")
                }

            } else if (resultCode === Activity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }



}