package com.spinny.assignment.model

data class MakeCarResponse(
    val Count: Int,
    val Message: String,
    val Results: List<Car>
)

data class Car(
    val Make_ID: String,
    val Make_Name: String
)
