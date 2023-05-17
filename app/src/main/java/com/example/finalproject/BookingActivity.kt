package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide

class BookingActivity : AppCompatActivity() {
    lateinit var rentalImage:ImageView
    lateinit var LandlordName : EditText
    lateinit var rentalName: EditText
    lateinit var paybill :EditText
    lateinit var rentalFee  :EditText
    lateinit var btnpurchase:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        LandlordName = findViewById(R.id.mtvedtname)
        rentalName = findViewById(R.id.medtcolor)
        paybill = findViewById(R.id.medtReg)
        rentalFee = findViewById(R.id.mtvpricee)
        rentalImage = findViewById(R.id.imageView)
        btnpurchase = findViewById(R.id.btnpurchase)





        btnpurchase.setOnClickListener {
            val intent = Intent(this@BookingActivity, paymentActivity::class.java)
            startActivity(intent)
        }


        var landlordname = intent.getStringExtra("LandlordName")
        var rentalname = intent.getStringExtra("rentalName")
        var paybill = intent.getStringExtra("paybill")
        var rentalfee = intent.getStringExtra("rentalFee")
        var image = intent.getStringExtra("image")
        Glide.with(this).load(image).into(rentalImage)



    }
}