package com.roomeasy.capstoneproject.service.dto

data class ReviewDto(
    val id: Long = 0,
    val userId: Long,
    val name: String,
    val roomId: Long,
    val timeOfResidence: Int,
    val score: Double,
    val freeComments: String? = null,
    val myReview: Boolean
)
