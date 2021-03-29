package com.brainshtorm.brainstorm.varvarGame

import android.graphics.Color

data class GameFigure(val number : Int, val color : Int, val figure : String, val isOdd : Boolean ) {


    companion object {
        fun generateFirstFigure() : GameFigure{
            return GameFigure(1, Color.YELLOW, "square", true)
        }
        fun generateSecondFigure() : GameFigure{
            return GameFigure(2, Color.RED, "enneagon", false)
        }
        fun generateThirdFigure() : GameFigure{
            return GameFigure(3, Color.GREEN, "triangle", true)
        }
        fun generateFourthFigure() : GameFigure{
            return GameFigure(4, Color.BLUE, "hexagon", false)
        }
        fun getArrayGameFigures(): ArrayList<GameFigure>{
            return arrayListOf(generateFirstFigure(),
                generateSecondFigure(), generateThirdFigure(), generateFourthFigure())
        }
    }
}