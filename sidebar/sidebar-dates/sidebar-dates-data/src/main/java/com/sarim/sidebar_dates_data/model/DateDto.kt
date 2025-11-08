package com.sarim.sidebar_dates_data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.sarim.sidebar_dates_domain.model.Date
import kotlinx.serialization.Serializable

const val DATE_DTO_ID_COLUMN_NAME = "dateId"

@Serializable
@Entity
data class DateDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DATE_DTO_ID_COLUMN_NAME)
    val dateId: Long = 0,
    val dateHeading: String,
    val dateSessions: Int,
) {
    companion object {
        val NO_SELECTED_DATE_DTO =
            DateDto(
                dateId = -1,
                dateHeading = "",
                dateSessions = -1,
            )

        fun DateDto.toDate() =
            Date(
                dateId = dateId,
                dateHeading = dateHeading,
                dateSessions = dateSessions,
            )

        fun Date.fromDate() =
            DateDto(
                dateId = dateId,
                dateHeading = dateHeading,
                dateSessions = dateSessions,
            )
    }
}

@Entity
@Fts4(contentEntity = DateDto::class)
internal data class DateDtoFts(
    @ColumnInfo(name = "rowid")
    @PrimaryKey(autoGenerate = true) val dateId: Int = 0,
    val dateHeading: String,
)
