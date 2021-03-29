package com.brainshtorm.brainstorm.data.ClickFigure

import android.widget.ImageView
import com.brainshtorm.brainstorm.R

data class Figure(val figureColor : Int, val figureName : String) {
    private var figureMap = mapOf("circle" to R.drawable.ic_circle,
            "square" to R.drawable.ic_square,
            "triangle" to R.drawable.ic_triangle)
    fun getImageResourceFigure() = figureMap[figureName]

}