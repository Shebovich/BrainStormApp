package com.brainshtorm.brainstorm.data.ClickFigure

import android.widget.ImageButton
import android.widget.ImageView

data class FigureButton (val button : ImageView, val rowPosition: Int, val columnPosition: Int) {
    var isResult: Boolean = false
}