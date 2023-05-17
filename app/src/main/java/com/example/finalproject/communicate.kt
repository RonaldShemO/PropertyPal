package com.example.finalproject

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class communicate : AppCompatActivity() {
    lateinit var vbtnsms:Button
    lateinit var vbtnemail:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicate)
        vbtnsms = findViewById(R.id.btnsms)
        vbtnemail.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("Shwam", "shemobaigwa79@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear sir/madam i hearby inquire on renting a room in ")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        vbtnsms.setOnClickListener {
            val uri: Uri = Uri.parse("smsto:0718262260")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello there,")
            startActivity(intent)
        }


    }
}