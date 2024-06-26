package com.rendox.routinetracker.core.data

import com.rendox.routinetracker.core.database.completion_history.CompletionHistoryLocalDataSource
import com.rendox.routinetracker.core.database.habit.HabitLocalDataSource

class DatabasePrepopulatorRandom(
    private val habitsGenerator: RandomHabitsGenerator,
    private val habitLocalDataSource: HabitLocalDataSource,
    private val completionHistoryLocalDataSource: CompletionHistoryLocalDataSource,
) : DatabasePrepopulator {
    override suspend fun prepopulateDatabase() {
        val dbIsNotEmpty = !habitLocalDataSource.checkIfIsEmpty()
        if (dbIsNotEmpty) return

        val habits = habitsGenerator.generateRandomHabits()
        habitLocalDataSource.insertHabits(habits)

        val insertedHabits = habitLocalDataSource.getAllHabits()
        val completionHistory = insertedHabits
            .associateWith { habit -> habitsGenerator.generateCompletionHistory(habit) }
            .mapKeys { it.key.id!! }
        completionHistoryLocalDataSource.insertCompletions(completionHistory)
    }
}