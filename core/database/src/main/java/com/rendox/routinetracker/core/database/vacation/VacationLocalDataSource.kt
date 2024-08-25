package com.rendox.routinetracker.core.database.vacation

import com.rendox.routinetracker.core.logic.time.LocalDateRange
import com.rendox.routinetracker.core.model.Vacation

interface VacationLocalDataSource {

    suspend fun getVacationsInPeriod(
        habitId: Long,
        period: LocalDateRange,
    ): List<Vacation>

    suspend fun getMultiHabitVacations(
        habitsToPeriods: List<Pair<List<Long>, LocalDateRange>>,
    ): Map<Long, List<Vacation>>

    suspend fun insertVacation(
        habitId: Long,
        vacation: Vacation,
    )
    suspend fun insertVacations(habitIdsToVacations: Map<Long, List<Vacation>>)
}