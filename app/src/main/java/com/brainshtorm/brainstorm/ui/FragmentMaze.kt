package com.brainshtorm.brainstorm.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.brainshtorm.brainstorm.data.TapButton
import com.brainshtorm.brainstorm.data.mazeGame.GameProcessor
import com.brainshtorm.brainstorm.databinding.FragmentAnimalBinding

class FragmentMaze : Fragment() {
    private lateinit var binding: FragmentAnimalBinding
    private var tapButtonsArray : ArrayList<TapButton> = ArrayList()
    private lateinit var defaultButtonColor : Drawable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAnimalBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGrid(4,5)
        addTapGameSquares()
        initGame()

    }
    fun initGrid(rowCount : Int, columnCount : Int){
        binding.animalsLayout.rowCount = rowCount
        binding.animalsLayout.columnCount = columnCount

    }
    fun addTapGameSquares() {
        for (i in 0..3){
            for (j in 0..4){
                val tapButton = ImageButton(context)
                val layoutParams: GridLayout.LayoutParams = GridLayout.LayoutParams()
                layoutParams.width = context?.let { it1 -> convertDpToPixel(80F, it1).toInt() }!!
                layoutParams.height = context?.let { it1 -> convertDpToPixel(80F, it1).toInt() }!!
                tapButton.layoutParams = layoutParams
                binding.animalsLayout.addView(tapButton)
                val tapButtonObject = TapButton(tapButton,i,j)
                tapButtonsArray.add(tapButtonObject)
                defaultButtonColor = tapButton.background
                System.out.println("rowPosition "+i+" columnPosition "+j)
            }
        }

    }
    fun initGame(){
        val gameProcessor = GameProcessor()
        val resultFigure : ArrayList<TapButton> = gameProcessor.startRandomFigure(tapButtonsArray)
        print("resultFigure "+ resultFigure.size)
        for (resultFigureTab in resultFigure){
            print(resultFigureTab.columnPosition)
            print(resultFigureTab.columnPosition)
            print(resultFigureTab.rowPosition)
            if (tapButtonsArray.contains(resultFigureTab)){
                tapButtonsArray[tapButtonsArray.indexOf(resultFigureTab)].button.setBackgroundColor(Color.BLACK)
            }
        }

    }
    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}