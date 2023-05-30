package com.roomeasy.capstoneproject.service.user

import com.roomeasy.capstoneproject.service.dto.BrokerReviewDto

interface BrokerReviewService {
    fun getBrokerReviewByBrokerId(brokerId: Long): BrokerReviewDto
    fun addBrokerReview(
        userId: Long,
        brokerId: Long,
        kindness: Int,
        reliability: Int,
        responseTime: Int
    )
}