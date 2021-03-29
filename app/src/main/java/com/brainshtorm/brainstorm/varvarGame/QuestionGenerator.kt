package com.brainshtorm.brainstorm.varvarGame

import android.content.Context
import com.brainshtorm.brainstorm.R
import kotlin.random.Random

class QuestionGenerator(val context: Context) {
    private val numbersList = arrayListOf(1, 2, 3, 4)

    private val colorForQuestionList = arrayListOf(
        R.color.yellow_color,
        R.color.red_color,
        R.color.green_color,
        R.color.purple_200
    )
    private val colorByWord = mapOf(
        "yellow" to R.color.yellow_color,
        "red" to R.color.red_color,
        "green" to R.color.green_color,
        "purple" to  R.color.purple_200
    )
    private val colorByNumber = mapOf(
        1 to R.color.yellow_color,
        2 to R.color.red_color,
        3 to R.color.green_color,
        4 to R.color.purple_200
    )
    private val colorByFigure = mapOf(
        "square" to R.color.red_color,
        "circle" to R.color.yellow_color,
        "trapezoid" to R.color.green_color,
        "hexagon" to R.color.purple_200
    )
    private val resultMapId = mapOf(
            1 to R.id.first_layout,
            2 to R.id.second_layout,
            3 to R.id.third_layout,
            4 to R.id.fourth_layout
    )
    private val colorList = arrayListOf("red", "yellow", "green", "purple")
    private val figureList = arrayListOf("square", "circle", "trapezoid", "hexagon")
    fun generateNumberQuestion(): PirateQuestion {
        val numberInt = numbersList[Random.nextInt(0, numbersList.size)]
        val colorOfNumber = colorByNumber[numberInt]
        val questionString = "Tap <font color='${context.resources.getString(colorOfNumber!!)}'>$numberInt</font>"


        return PirateQuestion(arrayListOf(numberInt),questionString,false,getIdButton(numberInt))
    }

    private fun getIdButton(numberInt: Int) : Int{
        return resultMapId[numberInt] ?: 1
    }

    private fun getNumbersWithoutOne(numberToRemove : Int):ArrayList<Int>{
        val numberListCopy : ArrayList<Int> = ArrayList()
        numberListCopy.addAll(numbersList)
        numberListCopy.remove(numberToRemove)
        return numberListCopy
    }

    fun generateColorQuestion(): PirateQuestion {
        val randomItem =  Random.nextInt(0, colorList.size)
        val numberInt = colorList[randomItem]
        val colorOfNumber = colorByWord[numberInt]
        val questionString = "Tap <font color='${context.resources.getString(colorOfNumber!!)}'>$numberInt</font>"
        return PirateQuestion(arrayListOf(randomItem+1),questionString,false, getIdButton(randomItem))
    }

    fun getColorOfText(colorOfNumber : Int) : String {
        return context.resources.getString(colorOfNumber)
    }

    fun generateNotNumberQuestion(): PirateQuestion {
        val randomItem =  Random.nextInt(0, colorList.size)
        val numberInt = numbersList[randomItem]
        val colorOfNumber = colorByNumber[numberInt]
        val questionString = "Tap not <font color='${context.resources.getString(colorOfNumber!!)}'>$numberInt</font>"
        return PirateQuestion(getNumbersWithoutOne(numberInt),questionString,false, getIdButton(randomItem))
    }

    fun generateNotColorQuestion(): PirateQuestion {
        val randomItem =  Random.nextInt(0, colorList.size)
        val numberInt = colorList[randomItem]
        val colorOfNumber = colorByWord[numberInt]
        val resultString = "Tap not <font color='${context.resources.getString(colorOfNumber!!)}'>$numberInt</font>"
        return PirateQuestion(getNumbersWithoutOne(randomItem),resultString,false, getIdButton(randomItem))
    }

    fun generateEvenQuestion(): PirateQuestion {
        val randomInt = Random.nextInt(0,colorForQuestionList.size)
        val resultColor = colorForQuestionList[randomInt]
        val resultString = "Tap <font color='${context.resources.getString(resultColor!!)}'>even</font> number"
        return PirateQuestion(arrayListOf(1,3),resultString,false, getIdButton(randomInt))
    }

    fun generateOddQuestion(): PirateQuestion {
        val randomInt = Random.nextInt(0,colorForQuestionList.size)
        val resultColor = colorForQuestionList[randomInt]
        val resultString = "Tap <font color='${context.resources.getString(resultColor!!)}'>odd</font> number"
        return PirateQuestion(arrayListOf(2,4),resultString,false, getIdButton(randomInt))
    }

    fun generateFigureQuestion(): PirateQuestion {
        val randomItem =  Random.nextInt(0, colorList.size)
        val numberInt = figureList[randomItem]
        val colorOfNumber = colorByFigure[numberInt]
        val resultString = "Tap not <font color='${context.resources.getString(colorOfNumber!!)}'>$numberInt</font>"
        return PirateQuestion(arrayListOf(randomItem),resultString,false, getIdButton(randomItem))
    }

    fun initLevelOfGame(levelGame : Int) : ArrayList<PirateQuestion> {
        return when (levelGame) {
            1 -> {
                arrayListOf(
                        generateNumberQuestion(), generateNumberQuestion(), generateColorQuestion(), generateNotNumberQuestion()
                )
            }
            2 -> {
                arrayListOf(generateNotNumberQuestion(), generateNotColorQuestion(), generateEvenQuestion(), generateOddQuestion())
            } else -> {
                arrayListOf(generateEvenQuestion(), generateNotColorQuestion(), generateFigureQuestion(), generateFigureQuestion())
            }
        }

    }

    fun getResultOfAnswer(answers : ArrayList<Int>, question: PirateQuestion) : Boolean{
        var numbersArrayResult = ArrayList<Int>()
        for (id in 0..answers.size){
            resultMapId[id]?.let { numbersArrayResult.add(it) }
        }
        return question.result.size == numbersArrayResult.size && question.result.containsAll(numbersArrayResult)
    }


//    fun generateOrderCornersQuestion(): Question {
//        val resultColor = colorForQuestionList[Random.nextInt(0,colorForQuestionList.size)]
//        val resultString = "Tap the shapes in <font color='$resultColor'>ascending</font> order of their corners."
//        return Question()
//    }
}