package com.roomeasy.capstoneproject.service.chat

import com.roomeasy.capstoneproject.domain.chat.Reminder
import com.roomeasy.capstoneproject.repository.chat.ReminderRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ReminderServiceImpl(
    private val reminderRepository: ReminderRepository,
) : ReminderService {

    override fun addReminder(userId: Long, chatRoomId: Long, date: String, time: String, place: String, longitude: String, latitude: String) {
        reminderRepository.save(
            Reminder(
                userId = userId,
                chatRoomId = chatRoomId,
                date = date,
                time = time,
                place = place,
                longitude = longitude.toDouble(),
                latitude = latitude.toDouble()
            )
        )
    }

    override fun getReminderByChatRoomId(chatRoomId: Long): Reminder {
        return reminderRepository.findByChatRoomId(chatRoomId)
    }
}