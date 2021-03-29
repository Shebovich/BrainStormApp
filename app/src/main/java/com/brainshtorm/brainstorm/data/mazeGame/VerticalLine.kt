package com.brainshtorm.brainstorm.data.mazeGame

import com.brainshtorm.brainstorm.data.TapButton
import kotlin.random.Random

class VerticalLine : IGameMaze {

    private lateinit var  tapButtonsArray: ArrayList<TapButton>
    override fun addGrid(tapButtons: ArrayList<TapButton>) {
        tapButtonsArray = tapButtons
    }

    override fun generateFigure(): ArrayList<TapButton> {
        val resultFigureTabs : ArrayList<TapButton> = ArrayList()
        val rowPositionFirstTab = Random.nextInt(0,1)
        val columnPositionFirstTab = Random.nextInt(0,3)
        println("tapButtonsArray "+tapButtonsArray.size)
        for (tabButton in tapButtonsArray){
            if (tabButton.columnPosition == columnPositionFirstTab && tabButton.rowPosition == rowPositionFirstTab){
                resultFigureTabs.add(tabButton)
            }
            if (tabButton.columnPosition == columnPositionFirstTab+1 && tabButton.rowPosition == rowPositionFirstTab){
                resultFigureTabs.add(tabButton)
            }
            if (tabButton.columnPosition == columnPositionFirstTab && tabButton.rowPosition == rowPositionFirstTab+1){
                resultFigureTabs.add(tabButton)
            }
            if (tabButton.columnPosition == columnPositionFirstTab && tabButton.rowPosition == rowPositionFirstTab+2){
                resultFigureTabs.add(tabButton)
            }

        }
        println("resultFigureTabs "+resultFigureTabs.size)
        return resultFigureTabs
    }

    override fun getGameName(): String {
       return "Line"
    }

}