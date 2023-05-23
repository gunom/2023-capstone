package com.roomeasy.capstoneproject.service.dto

data class ReviewDto(
    val id: Long = 0,
    val userId: Long,
    val name: String,
    val roomId: Long,
    val timeOfResidence: Int,
    val ageGroup: String,
    val gender: String,
    val transportationRating: Int,
    val neighborhoodRating: Int,
    val livingConditionsRating: Int,
    val freeComments: String? = null
)
