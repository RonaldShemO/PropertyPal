package com.example.finalproject

import android.location.Address
import android.widget.EditText

class Ulpoad {
     var address: String = ""
     var name: String = ""
     var phoneNumber: String = ""
     var rentalFee: String = ""
     var image: String = ""
     var id: String = ""

    constructor(
        address: String,
        name: String,
        phoneNumber: String,
        rentalFee: String,
        image: String,
        id: String
    ) {
        this.address = address
        this.name = name
        this.phoneNumber = phoneNumber
        this.rentalFee= rentalFee
        this.image = image
        this.id = id
    }
    constructor()
}