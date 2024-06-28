package com.rendox.routinetracker.core.data.completion_history

import com.rendox.routinetracker.core.model.Habit
import kotlinx.datetime.LocalDate

interface CompletionHistoryRepository {

    /**
     * get all records for the given habit in the ascending order, if [minDate] or [maxDate] are
     * specified, returns only the records in the given range.
     */
    suspend fun getRecordsInPeriod(
        habitId: Long,
        minDate: LocalDate? = null,
        maxDate: LocalDate? = null,
    ): List<Habit.CompletionRecord>

    suspend fun getAllRecords(): Map<Long, List<Habit.CompletionRecord>>

    suspend fun insertCompletion(
        habitId: Long,
        completionRecord: Habit.CompletionRecord,
    )

    suspend fun insertCompletions(
        completions: Map<Long, List<Habit.CompletionRecord>>
    )

    suspend fun deleteCompletionByDate(
        habitId: Long,
        date: LocalDate,
    )
}