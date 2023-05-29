package com.roomeasy.capstoneproject.service.user

interface BrokerReviewService {
    fun getBrokerReviewByBrokerId(brokerId: Long): Double
    fun addBrokerReview(userId: Long, brokerId: Long, score: Int)
}