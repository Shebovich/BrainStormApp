package com.brainshtorm.brainstorm.data

import android.widget.Button

data class TapButton(val button : Button, val rowPosition: Int, val columnPosition: Int) {
    var isResult: Boolean = false
}