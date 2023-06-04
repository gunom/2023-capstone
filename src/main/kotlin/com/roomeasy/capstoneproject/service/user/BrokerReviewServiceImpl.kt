package com.roomeasy.capstoneproject.service.user

import com.roomeasy.capstoneproject.domain.user.BrokerReview
import com.roomeasy.capstoneproject.repository.user.BrokerReviewRepository
import com.roomeasy.capstoneproject.service.dto.BrokerReviewDto
import org.springframework.stereotype.Service

@Service
class BrokerReviewServiceImpl(
    private val brokerReviewRepository: BrokerReviewRepository,
) : BrokerReviewService{
    override fun getBrokerReviewByBrokerId(brokerId: Long): BrokerReviewDto {
        val scores = brokerReviewRepository.findAllByBrokerId(brokerId).map {
            (it.kindness + it.reliability + it.responseTime)/3.0
        }
        return BrokerReviewDto(
            scores.average()
        )
    }

    override fun addBrokerReview(userId: Long, brokerId: Long, kindness: Int, reliability: Int, responseTime: Int){
        brokerReviewRepository.save(
            BrokerReview(
                userId = userId,
                brokerId = brokerId,
                kindness = kindness,
                reliability = reliability,
                responseTime = responseTime
            )
        )
    }
}