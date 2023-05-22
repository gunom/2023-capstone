package com.roomeasy.capstoneproject.domain.room

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Room (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var roomId: String? = null,
    var deposit: Int? = null,
    var address: String? = null,
    var floor: String? = null,
    var imagesThumbnail: String? = null,
    var rent: Double? = null,
    var manageCost: Double? = null,
    var serviceType: String? = null,
    var salesType: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null,
    var sizeM2: Double? = null
)