package com.example.finalprojectandroid

import kotlin.math.roundToInt

class DailyIntakeCalculator(private var bmi: Double = 0.0, private var tdee: Double = 0.0) {
    companion object {
        const val CALORIES_PER_GRAM_OF_PROTEIN = 4
        const val CALORIES_PER_GRAM_OF_CARBS = 4
        const val CALORIES_PER_GRAM_OF_FATS = 9

        const val MIN_PROTEIN_PERCENTAGE = 10 // Minimum percentage of daily calories from protein
        const val MAX_PROTEIN_PERCENTAGE = 35 // Maximum percentage of daily calories from protein
        const val FAT_PERCENTAGE = 30 // Percentage of daily calories from fats
    }

    fun setBMI(bmi: Double) {
        this.bmi = bmi
    }

    fun setTDEE(tdee: Double) {
        this.tdee = tdee
    }

    fun calculateRecommendedIntake(): Map<String, Int> {
        val calories = tdee.toInt()
        val proteinCalories = (calories * MIN_PROTEIN_PERCENTAGE / 100).toDouble()
        val maxProteinCalories = (calories * MAX_PROTEIN_PERCENTAGE / 100).toDouble()

        // Calculate recommended protein intake
        val minProteinGrams = (proteinCalories / CALORIES_PER_GRAM_OF_PROTEIN).roundToInt()
        val maxProteinGrams = (maxProteinCalories / CALORIES_PER_GRAM_OF_PROTEIN).roundToInt()

        // Calculate recommended fat intake
        val fatCalories = (calories * FAT_PERCENTAGE / 100).toDouble()
        val fatGrams = (fatCalories / CALORIES_PER_GRAM_OF_FATS).roundToInt()

        // Calculate recommended carb intake
        val carbCalories = calories - (proteinCalories + fatCalories)
        val carbGrams = (carbCalories / CALORIES_PER_GRAM_OF_CARBS).roundToInt()

        return mapOf(
            "Protein" to minProteinGrams,
            "Fat" to fatGrams,
            "Carbohydrates" to carbGrams
        )
    }
}