package com.roomeasy.capstoneproject.service.room

import com.roomeasy.capstoneproject.domain.room.Room

interface RoomService {
    fun getByIds(ids: List<Long>): List<Room>
}