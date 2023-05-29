package com.roomeasy.capstoneproject.domain.user

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class BrokerReview(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var score: Int,
    var brokerId: Long,
    var userId: Long
)