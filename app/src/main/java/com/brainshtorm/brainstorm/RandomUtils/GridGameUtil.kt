package com.brainshtorm.brainstorm.RandomUtils

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.widget.GridLayout
import android.widget.ImageButton
import com.brainshtorm.brainstorm.data.TapButton

class GridGameUtil(val gridLayout: GridLayout, val context: Context) {

    private var  tapButtonsArray : ArrayList<TapButton> = ArrayList()
    private lateinit  var defaultButtonColor : Drawable

}