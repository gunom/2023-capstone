package com.roomeasy.capstoneproject.service.room

import com.roomeasy.capstoneproject.domain.room.Room
import com.roomeasy.capstoneproject.repository.room.RoomRepository
import org.springframework.stereotype.Service

@Service
class RoomServiceImpl(
    private val roomRepository: RoomRepository
) : RoomService {
    override fun getByIds(ids: List<Long>): List<Room> {
        return roomRepository.findByIdIn(ids)
    }
}