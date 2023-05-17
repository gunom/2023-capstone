package com.roomeasy.capstoneproject.domain.room

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Room (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
)