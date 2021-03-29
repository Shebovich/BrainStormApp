package com.brainshtorm.brainstorm.data.mazeGame

import com.brainshtorm.brainstorm.data.TapButton
import kotlin.random.Random

class GameProcessor() {
    private var gameMap: HashMap<String, IGameMaze> = HashMap()
    private var squaresArray = arrayOf("VerticalBoogie","VerticalLine", "Penis", "Square","HorizontalBoogie","HorizontalLine")
    init {
        generateMazeArray()
    }
    fun generateMazeArray(){
        gameMap.apply {
            put("HorizontalBoogie", HorizontalBoogie())
            put("VerticalLine", VerticalLine())
            put("Penis", Penis())
            put("Square", Square())
            put("VerticalBoogie", VerticalBoogie())
            put("HorizontalLine",HorizontalLine())
        }
    }
    fun startRandomFigure(tapButton: ArrayList<TapButton>): ArrayList<TapButton> {
        val randomValue : String = squaresArray[Random.nextInt(squaresArray.size)]
        println(randomValue)
        val  iGameMaze : IGameMaze? = gameMap[randomValue]
        if (iGameMaze != null) {
            println("iGameMaze "+iGameMaze.getGameName())
        }
        iGameMaze?.addGrid(tapButton)
        return iGameMaze!!.generateFigure()
    }
}