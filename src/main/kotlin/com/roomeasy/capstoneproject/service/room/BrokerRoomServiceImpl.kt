package com.roomeasy.capstoneproject.service.room

import com.roomeasy.capstoneproject.domain.room.BrokerRoom
import com.roomeasy.capstoneproject.repository.room.BrokerRoomRepository
import org.springframework.stereotype.Service

@Service
class BrokerRoomServiceImpl(
    private val brokerRoomRepository: BrokerRoomRepository,
) : BrokerRoomService {
    override fun getListByUserId(userId: Long): List<BrokerRoom> {
        return brokerRoomRepository.findAllByUserId(userId)
    }

    override fun getBrokerRoomByUserIdAndRoomId(userId: Long, roomId: Long): BrokerRoom? {
        return brokerRoomRepository.findByUserIdAndRoomId(userId, roomId)
    }

    override fun addBrokerRoom(userId: Long, roomId: Long) {
        val brokerRoom = BrokerRoom(userId = userId, roomId = roomId)
        brokerRoomRepository.save(brokerRoom)
    }

    override fun deleteBrokerRoom(brokerRoom: BrokerRoom){
        brokerRoomRepository.delete(brokerRoom)
    }
}