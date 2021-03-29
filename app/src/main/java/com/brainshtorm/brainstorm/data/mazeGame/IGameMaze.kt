package com.brainshtorm.brainstorm.data.mazeGame

import com.brainshtorm.brainstorm.data.TapButton

interface IGameMaze {
    fun addGrid(tapButtons: ArrayList<TapButton>)
    fun generateFigure() : ArrayList<TapButton>
    fun getGameName(): String
}