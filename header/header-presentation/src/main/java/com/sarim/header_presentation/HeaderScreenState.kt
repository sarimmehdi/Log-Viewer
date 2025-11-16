package com.sarim.header_presentation

import android.os.Parcelable
import com.sarim.maincontent_domain.model.LogType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeaderScreenState(
    val searchString: String = "",
    val dropDownType: DropDownType = DropDownType.NONE,
    val classFilters: ImmutableList<ClassFilter> = persistentListOf(),
    val functionFilters: ImmutableList<FunctionFilter> = persistentListOf(),
    val logTypeFilters: ImmutableList<LogTypeFilter> = persistentListOf(),
) : Parcelable

enum class DropDownType {
    CLASS_DROP_DOWN,
    FUNCTION_DROP_DOWN,
    LOG_TYPE_DROP_DOWN,
    NONE,
}

@Parcelize
data class ClassFilter(
    val className: String,
    val classFilter: Boolean,
) : Parcelable

@Parcelize
data class FunctionFilter(
    val functionName: String,
    val functionFilter: Boolean,
) : Parcelable

@Parcelize
data class LogTypeFilter(
    val logType: LogType,
    val logTypeFilter: Boolean,
) : Parcelable
