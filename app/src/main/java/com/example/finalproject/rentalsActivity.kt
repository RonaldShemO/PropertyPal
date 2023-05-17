package com.example.finalproject

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class rentalsActivity : AppCompatActivity() {
    lateinit var mListRentals:ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rentals)
        mListRentals = findViewById(R.id.mListRentals)
        var rentals:ArrayList<Ulpoad> = ArrayList()
        var myAdapter = RentalsAdapter(applicationContext,rentals)
        var progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")

        //Access the table in the database

        var my_db = FirebaseDatabase.getInstance().reference.child("Cars")
        //Start retrieving data
        progress.show()
        my_db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                //Get the data and put it on the arraylist users
                rentals.clear()
                for (snap in p0.children){
                    var rental = snap.getValue(Ulpoad::class.java)
                    rentals.add(rental!!)
                }
                //Notify the adapter that data has changed
                myAdapter.notifyDataSetChanged()
                progress.dismiss()
            }

            override fun onCancelled(p0: DatabaseError) {
                progress.dismiss()
                Toast.makeText(applicationContext,"DB Locked",Toast.LENGTH_LONG).show()
            }
        })

        mListRentals.adapter = myAdapter
        mListRentals.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(this,BookingActivity::class.java)
            var address: String = rentals.get(position).address
            var name: String = rentals.get(position).name
            var phoneNumber: String = rentals.get(position).phoneNumber
            var rentalFee: String = rentals.get(position).rentalFee
            var image: String = rentals.get(position).image

            intent.putExtra("address",address)
            intent.putExtra("name",name)
            intent.putExtra("phoneNumber",phoneNumber)
            intent.putExtra("renatlFee",rentalFee)
            intent.putExtra("image",image)
            startActivity(intent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_add_rentals -> {
                startActivity(Intent(this,AddrentalActivity::class.java))
                true
            }

            R.id.action_view_rentals -> {
                Toast.makeText(this,"View rentals clicked",Toast.LENGTH_LONG).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}