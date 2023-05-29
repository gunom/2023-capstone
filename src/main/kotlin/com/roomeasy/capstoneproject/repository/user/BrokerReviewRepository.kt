package com.roomeasy.capstoneproject.repository.user

import com.roomeasy.capstoneproject.domain.user.BrokerReview
import org.springframework.data.jpa.repository.JpaRepository

interface BrokerReviewRepository: JpaRepository<BrokerReview, Long> {
}