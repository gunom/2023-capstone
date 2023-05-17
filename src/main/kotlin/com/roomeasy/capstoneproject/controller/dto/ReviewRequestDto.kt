package com.roomeasy.capstoneproject.controller.dto

data class ReviewRequestDto(
    val roomId: Long,
    val timeOfResidence: Int,
    val ageGroup: String,
    val gender: String,
    val transportationRating: Int,
    val neighborhoodRating: Int,
    val livingConditionsRating: Int,
    val freeComments: String? = null
)
