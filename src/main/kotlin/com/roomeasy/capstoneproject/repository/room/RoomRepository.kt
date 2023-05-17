package com.roomeasy.capstoneproject.repository.room

import com.roomeasy.capstoneproject.domain.room.Room
import org.springframework.data.jpa.repository.JpaRepository

interface RoomRepository: JpaRepository<Room, Long> {
    fun findByIdIn(roomIds: List<Long>): List<Room>
}