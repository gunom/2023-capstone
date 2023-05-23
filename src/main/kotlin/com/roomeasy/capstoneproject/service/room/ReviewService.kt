package com.roomeasy.capstoneproject.service.room

import com.roomeasy.capstoneproject.domain.room.Review
import com.roomeasy.capstoneproject.service.dto.ReviewDto

interface ReviewService {
    fun addReview(
        userId: Long,
        roomId: Long,
        timeOfResidence: Int,
        gender: String,
        ageGroup: String,
        transportationRating: Int,
        neighborhoodRating: Int,
        livingConditionsRating: Int,
        freeComments: String?
    )

    fun getReview(roomId: Long): List<Review>

    fun deleteReview(reviewId: Long, userId: Long)
}