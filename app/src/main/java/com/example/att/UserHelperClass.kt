package com.example.att

class UserHelperClass {

    lateinit var myName : String
    lateinit var username : String
    lateinit var phoneNumber : String
    lateinit var email : String
    lateinit var password : String

    constructor() {
    }

    constructor(myName: String, phoneNumber: String, email: String, username: String, password: String) {
        this.myName = myName
        this.username = username
        this.phoneNumber = phoneNumber
        this.email = email
        this.password = password
    }


}