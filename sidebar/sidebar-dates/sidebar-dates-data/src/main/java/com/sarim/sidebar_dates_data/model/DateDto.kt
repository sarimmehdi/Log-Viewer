package com.sarim.sidebar_dates_data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.sarim.sidebar_dates_domain.model.Date
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class DateDto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dateHeading: String,
    val dateSessions: Int,
) {
    companion object {
        val NO_SELECTED_DATE_DTO =
            DateDto(
                id = -1,
                dateHeading = "",
                dateSessions = -1,
            )

        fun DateDto.toDate() =
            Date(
                id = id,
                dateHeading = dateHeading,
                dateSessions = dateSessions,
            )

        fun Date.fromDate() =
            DateDto(
                id = id,
                dateHeading = dateHeading,
                dateSessions = dateSessions,
            )
    }
}

@Entity
@Fts4(contentEntity = DateDto::class)
data class DateDtoFts(
    @ColumnInfo(name = "rowid")
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dateHeading: String,
)
