package com.roomeasy.capstoneproject.repository.room

import com.roomeasy.capstoneproject.domain.room.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository: JpaRepository<Review, Long> {
    fun findByRoomId(roomId: Long): List<Review>
}