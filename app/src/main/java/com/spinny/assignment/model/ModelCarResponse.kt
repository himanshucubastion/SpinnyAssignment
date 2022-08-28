package com.spinny.assignment.model

data class ModelCarResponse(
    val Count: Int,
    val Message: String,
    val Results: List<CarModelDetails>
)

data class CarModelDetails(
    val Make_ID: String,
    val Make_Name: String
)
