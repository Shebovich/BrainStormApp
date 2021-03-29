package com.brainshtorm.brainstorm.data

import android.widget.Button
import android.widget.ImageButton

data class TapButton(val button : ImageButton, val rowPosition: Int, val columnPosition: Int) {
    var isResult: Boolean = false
}