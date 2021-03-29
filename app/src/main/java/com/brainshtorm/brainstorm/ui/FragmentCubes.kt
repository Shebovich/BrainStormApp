package com.brainshtorm.brainstorm.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.brainshtorm.brainstorm.R
import com.brainshtorm.brainstorm.RandomUtils.RandomUtil
import com.brainshtorm.brainstorm.data.TapButton
import com.brainshtorm.brainstorm.databinding.FragmentAnimalBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

class FragmentCubes : Fragment() {
    private lateinit var binding: FragmentAnimalBinding
    private var randomUtil : RandomUtil = RandomUtil()
    private var lastRowPosition = 0
    private var tapButtonsArray: ArrayList<TapButton> = ArrayList()
    private var resultTapButtonsArray : ArrayList<TapButton> = ArrayList()
    private var selectedTabButtonsArray : ArrayList<TapButton> = ArrayList()
    private var lastColumnPosition = 0
    private val levelGame : Int = 1
    private var gridLevelMap = mapOf<Int,Int>()
    private var isClicking : Boolean = false
    private lateinit  var defaultButtonColor : Drawable
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }


    override fun onResume() {
        initGrid(5,4)
        addTapGameSquares()
        createLevelGame(levelGame)
        initSelectButtonListener()
        super.onResume()
    }

    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }


    fun initGrid(rowCount : Int, columnCount : Int){
        binding.animalsLayout.rowCount = rowCount
        binding.animalsLayout.columnCount = columnCount

    }

    fun addTapGameSquares() {
        for (i in 0..3){
            for (j in 0..3){
                val tapButton = ImageButton(context)
                tapButton.setBackgroundResource(R.drawable.ic_cube)
                val layoutParams: GridLayout.LayoutParams = GridLayout.LayoutParams()
                layoutParams.width = context?.let { it1 -> convertDpToPixel(90F, it1).toInt() }!!
                layoutParams.height = context?.let { it1 -> convertDpToPixel(90F, it1).toInt() }!!
                tapButton.layoutParams = layoutParams
                binding.animalsLayout.addView(tapButton)
                val tapButtonObject = TapButton(tapButton,i,j)
                tapButtonsArray.add(tapButtonObject)
                defaultButtonColor = tapButton.background
                System.out.println("rowPosition "+i+" columnPosition "+j)
            }
        }

    }
    fun setRandomResultButton(){
        System.out.println(tapButtonsArray.size)
        var firstTapButton = tapButtonsArray[Random.nextInt(0,tapButtonsArray.size)]
        resultTapButtonsArray.add(firstTapButton)
    }
    fun setColoredResultButtons(isColored : Boolean){
        for (resultButton in resultTapButtonsArray){
            if (isColored){
                resultButton.button.setBackgroundColor(Color.BLACK)
            }else{
                resultButton.button.background = defaultButtonColor
            }
        }
    }
    fun createLevelGame(levelGame : Int){
        for (i in 0..1){

            setRandomResultButton()
        }
        startTimerGame()
    }
    fun startTimerGame(){
        setColoredResultButtons(true)

        GlobalScope.launch (Dispatchers.Main){
            delay(3000)
            setColoredResultButtons(false)
        }

    }
    fun initSelectButtonListener(){
        val timer = object: CountDownTimer(3000, 3000) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {

                if (selectedTabButtonsArray.size == resultTapButtonsArray.size && selectedTabButtonsArray.containsAll(resultTapButtonsArray)){
                    Toast.makeText(context,"YOU WIN", Toast.LENGTH_LONG).show()
                    resetGame()
                }else{
                    Toast.makeText(context,"YOU LOSE", Toast.LENGTH_LONG).show()
                    resetGame()
                }

            }
        }
        for (selectButton in tapButtonsArray){
            selectButton.button.setOnClickListener{
                it.setBackgroundColor(Color.BLACK)
                selectedTabButtonsArray.add(selectButton)
                timer.cancel();
                timer.start();

            }
        }
    }



    private fun resetGame() {
        for (resultButton in tapButtonsArray){
            resultButton.button.background = defaultButtonColor
        }
        selectedTabButtonsArray = ArrayList()
        resultTapButtonsArray = ArrayList()
        createLevelGame(0)

    }


}