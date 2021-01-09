package com.brainshtorm.brainstorm.RandomUtils

import android.content.Context
import android.util.DisplayMetrics




class StaticValues {
    companion object{
        fun convertDpToPixel(dp: Float, context: Context): Float {
            return dp * (context.getResources()
                .getDisplayMetrics().densityDpi as Float / DisplayMetrics.DENSITY_DEFAULT)
        }
    }
}