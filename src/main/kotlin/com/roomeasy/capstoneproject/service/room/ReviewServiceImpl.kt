package com.roomeasy.capstoneproject.service.room

import com.roomeasy.capstoneproject.domain.room.Review
import com.roomeasy.capstoneproject.repository.room.ReviewRepository
import com.roomeasy.capstoneproject.service.dto.ReviewDto
import org.springframework.stereotype.Service

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository,
) : ReviewService {
    override fun addReview(
        userId: Long,
        roomId: Long,
        timeOfResidence: Int,
        gender: String,
        ageGroup: String,
        transportationRating: Int,
        neighborhoodRating: Int,
        livingConditionsRating: Int,
        freeComments: String?
    ) {
        reviewRepository.save(
            Review(
                userId = userId,
                roomId = roomId,
                timeOfResidence = timeOfResidence,
                ageGroup = ageGroup,
                transportationRating = transportationRating,
                neighborhoodRating = neighborhoodRating,
                livingConditionsRating = livingConditionsRating,
                freeComments = freeComments,
                gender = gender,
            )
        )
    }

    override fun getReview(roomId: Long): List<Review> {
        return reviewRepository.findByRoomId(roomId)
    }

    override fun deleteReview(reviewId: Long, userId: Long) {
        val review = reviewRepository.findById(reviewId).get()
        if (review.userId != userId) throw Exception("You are not authorized to delete this review")
        reviewRepository.deleteById(reviewId)
    }
}