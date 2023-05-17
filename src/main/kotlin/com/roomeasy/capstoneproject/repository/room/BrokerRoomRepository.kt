package com.roomeasy.capstoneproject.repository.room

import com.roomeasy.capstoneproject.domain.room.BrokerRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BrokerRoomRepository: JpaRepository<BrokerRoom, Long> {
    fun findAllByUserId(userId: Long): List<BrokerRoom>
    fun findByUserIdAndRoomId(userId: Long, roomId: Long): BrokerRoom
}