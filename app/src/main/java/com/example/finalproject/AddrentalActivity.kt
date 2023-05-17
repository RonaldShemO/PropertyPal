package com.example.finalproject

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.IOException
import java.util.*

class AddrentalActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    lateinit var imagePreview: ImageView
    lateinit var btn_choose_image: Button
    lateinit var btn_upload_image: Button
    lateinit var progress: ProgressDialog
    lateinit var edtAddress: EditText
    lateinit var edtName: EditText
    lateinit var edtphoneNumber: EditText
    lateinit var edtrentalFee: EditText
    lateinit var mtvReal : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addrental)
        btn_choose_image = findViewById(R.id.btn_choose_image)
        btn_upload_image = findViewById(R.id.btn_upload_image)
        imagePreview = findViewById(R.id.image_preview)
        edtAddress = findViewById(R.id.mEdtAddress)
        edtName= findViewById(R.id.mEdtName1)
        edtphoneNumber = findViewById(R.id.mEdtphoneNumber)
        edtrentalFee = findViewById(R.id.mEdtRentalFee)
        mtvReal = findViewById(R.id.mtvtextReal)

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        progress = ProgressDialog(this)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")

        btn_choose_image.setOnClickListener { launchGallery() }
        btn_upload_image.setOnClickListener { uploadImage() }
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imagePreview.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage(){
        var model = edtAddress.text.toString().trim()
        var color = edtName.text.toString().trim()
        var regno = edtphoneNumber.text.toString().trim()
        var price = edtrentalFee.text.toString().trim()
        var id = System.currentTimeMillis().toString()
        if (model.isEmpty()){
            edtAddress.setError("Please fill this input")
            edtAddress.requestFocus()
        }else if (color.isEmpty()){
            edtName.setError("Please fill this input")
            edtName.requestFocus()
        }else if (regno.isEmpty()){
            edtphoneNumber.setError("Please fill this input")
            edtphoneNumber.requestFocus()
        }else if (price.isEmpty()){
            edtrentalFee.setError("Please fill this input")
            edtrentalFee.requestFocus()
        }else{
            if(filePath != null){

                val ref = storageReference?.child("cars/" + UUID.randomUUID().toString())
                progress.show()
                val uploadTask = ref?.putFile(filePath!!)!!.addOnCompleteListener{
                    progress.dismiss()
                    if (it.isSuccessful){
                        ref.downloadUrl.addOnSuccessListener {
                            var rentalData = Ulpoad(address = String(), name = String(), phoneNumber = String(), rentalFee = String(),it.toString(),id)
                            var ref = FirebaseDatabase.getInstance().getReference().child("Cars/$id")
                            ref.setValue(rentalData)
                            Toast.makeText(this, "Rental submitted successfully", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, "Rental submission failed", Toast.LENGTH_SHORT).show()
                    }
                }


            }else{
                Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
            }
        }

    }

}