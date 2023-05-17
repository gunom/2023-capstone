package com.roomeasy.capstoneproject.service.room

import com.roomeasy.capstoneproject.domain.room.BrokerRoom

interface BrokerRoomService {
    fun getListByUserId(userId: Long): List<BrokerRoom>
    fun getBrokerRoomByUserIdAndRoomId(userId: Long, roomId: Long): BrokerRoom?
    fun addBrokerRoom(userId: Long, roomId: Long)
    fun deleteBrokerRoom(brokerRoom: BrokerRoom)
}