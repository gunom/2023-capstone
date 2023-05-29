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
        val newDate = date.trim().replace("년", "-").replace("월", "-").replace("일", "").let {
            var year = it.split("-")[0]
            var month = it.split("-")[1]
            var day = it.split("-")[2]
            if (month < "10") {
                month = "0$month"
            }
            if (day < "10") {
                day = "0$day"
            }
            "$year-$month-$day"
        }
        // time = "오후 1시 10분"
        val newTime = time.trim().replace("오후", "PM").replace("오전", "AM").replace("시", ":").replace("분", "").let {
            var hour = it.split(":")[0]
            var minute = it.split(":")[1]
            if (hour < "10") {
                hour = "0$hour"
            }
            if (minute < "10") {
                minute = "0$minute"
            }
            if(time.contains("PM")){
                hour = (hour.toInt() + 12).toString()
            }
            "$hour:$minute"
        }
        reminderRepository.save(
            Reminder(
                userId = userId,
                chatRoomId = chatRoomId,
                date = LocalDateTime.parse("$newDate $newTime", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
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