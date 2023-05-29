package com.roomeasy.capstoneproject.service.user

import com.roomeasy.capstoneproject.domain.user.BrokerReview
import com.roomeasy.capstoneproject.repository.user.BrokerReviewRepository
import org.springframework.stereotype.Service

@Service
class BrokerReviewServiceImpl(
    private val brokerReviewRepository: BrokerReviewRepository,
) : BrokerReviewService{
    override fun getBrokerReviewByBrokerId(brokerId: Long): Double {
        val scores = brokerReviewRepository.findAll().map {
            it.score
        }
        return scores.sum().div(scores.size.toDouble())
    }

    override fun addBrokerReview(userId: Long, brokerId: Long, score: Int){
        brokerReviewRepository.save(
            BrokerReview(
                userId = userId,
                brokerId = brokerId,
                score = score
            )
        )
    }
}