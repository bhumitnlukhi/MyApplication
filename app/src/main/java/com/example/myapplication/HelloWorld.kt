package com.example.myapplication

import java.util.ArrayList

fun main(arg: Array<String>) {
    println("Enter day from week : ")
    val day : Int = readlnOrNull()!!.toInt()

    var dayName : String
    when(day) {
        1 -> dayName = "Monday"
        2 -> dayName = "Tueday"
        else -> dayName = "Not found"
    }

    println("Enter day is $dayName")
}