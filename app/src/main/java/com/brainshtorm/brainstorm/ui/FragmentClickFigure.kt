package com.brainshtorm.brainstorm.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.brainshtorm.brainstorm.R
import com.brainshtorm.brainstorm.data.ClickFigure.Figure
import com.brainshtorm.brainstorm.data.ClickFigure.FigureButton
import com.brainshtorm.brainstorm.data.TapButton
import com.brainshtorm.brainstorm.databinding.FragmentAnimalBinding
import com.google.android.material.tabs.TabLayout
import kotlin.random.Random

class FragmentClickFigure : Fragment() {
    private lateinit var binding: FragmentAnimalBinding
    private var tapButtonsArray: ArrayList<FigureButton> = ArrayList()
    private var figureMap = mapOf("circle" to R.drawable.ic_circle,
            "square" to R.drawable.ic_square,
            "triangle" to R.drawable.ic_triangle)
    private var figureList = arrayListOf("circle", "square", "triangle")
    private lateinit var answerButton: FigureButton
    private var mapLevelFiguresCount = mapOf(1 to 5, 2 to 7, 3 to 9)
    private var mapOfColors = arrayListOf(R.color.matrix_color, R.color.maze_color, R.color.purple_200)
    private var mapLevelFigureKinds = mapOf(1 to 2, 2 to 3, 3 to 4)
    private lateinit var defaultButtonColor: Drawable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAnimalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initGrid(6, 4)
        initClickResultListener()
        //initGameLevelFigures(1, generateRandomFigures(2))

    }

    private fun initClickResultListener() {
        tapButtonsArray.forEach { button ->
            button.button.setOnClickListener {
                if (button.rowPosition.equals(answerButton.rowPosition) && button.columnPosition == answerButton.columnPosition) {
                    Toast.makeText(context, "YOU WIN", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "YOU LOSE", Toast.LENGTH_LONG).show()
                }

            }
        }


    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initGameLevelFigures(gameLevel: Int, resultFigures: ArrayList<Figure>) {
        var levelFiguresCount = mapLevelFiguresCount[gameLevel]
        var copyOfTabButtonsArray = tapButtonsArray
        var answerFigure = resultFigures[Random.nextInt(0, resultFigures.size)]
        answerButton = copyOfTabButtonsArray[Random.nextInt(0, copyOfTabButtonsArray.size)]
        figureMap[answerFigure.figureName]?.let { answerButton.button.setBackgroundResource(it) }
        copyOfTabButtonsArray.remove(answerButton)

        for (figureCountIndex in 0..levelFiguresCount!! / 2) {
            resultFigures.forEach {
                var notAnswerFigure = copyOfTabButtonsArray[Random.nextInt(0, copyOfTabButtonsArray.size)]
                figureMap[it.figureName]?.let { it1 ->
                    tapButtonsArray[tapButtonsArray.indexOf(notAnswerFigure)].button.setImageResource(it1)
                    tapButtonsArray[tapButtonsArray.indexOf(notAnswerFigure)].button.setColorFilter(ContextCompat.getColor(requireContext(), it.figureColor))
                    copyOfTabButtonsArray.remove(notAnswerFigure)
                }
            }

        }
    }



    fun addTapGameSquares() {

    }

    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun generateRandomFigures(figureCount: Int): ArrayList<Figure> {
        var listFiguresResult: ArrayList<Figure> = ArrayList()
        var copyOfFigureList: ArrayList<String> = figureList
        var copyOfColors = mapOfColors
        for (count in 0 until figureCount) {
            var resultColor = getRandomColor()
            var resultFigure = copyOfFigureList[Random.nextInt(0, copyOfFigureList.size)]
            listFiguresResult.add(Figure(resultColor, resultFigure))
            copyOfColors.remove(resultColor)
            copyOfFigureList.remove(resultFigure)
        }
        return listFiguresResult
    }

    fun getRandomResultFigure(): FigureButton {
        return tapButtonsArray[Random.nextInt(0, tapButtonsArray.size)]
    }

    fun getRandomColor() = Random.nextInt(0, mapOfColors.size)
}