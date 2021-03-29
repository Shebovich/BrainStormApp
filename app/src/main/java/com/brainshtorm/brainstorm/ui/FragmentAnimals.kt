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
import com.brainshtorm.brainstorm.data.Animal
import com.brainshtorm.brainstorm.data.TapButton
import com.brainshtorm.brainstorm.databinding.FragmentAnimalBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.NonCancellable.isActive
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer
import kotlin.random.Random

class FragmentAnimals : Fragment() {
    private lateinit var binding: FragmentAnimalBinding
    private var randomUtil : RandomUtil = RandomUtil()
    private var lastRowPosition = 0
    private var animalsImagesArray: ArrayList<Animal> = ArrayList()
    private var resultTapButtonsArray : ArrayList<TapButton> = ArrayList()
    private var selectedTabButtonsArray : ArrayList<TapButton> = ArrayList()
    private var lastColumnPosition = 0
    private val levelGame : Int = 1
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

    private fun addAnimalView(){
        randomUtil.quizAnimalsArray.forEach {
            var animalImageView = ImageView(context)

            var layoutParams: GridLayout.LayoutParams = GridLayout.LayoutParams()
            layoutParams.width = context?.let { it1 -> convertDpToPixel(100F, it1).toInt() }!!
            layoutParams.height = context?.let { it1 -> convertDpToPixel(100F, it1).toInt() }!!

            animalImageView.rotation = randomUtil.getRandomAngle().toFloat()
            animalImageView.layoutParams = layoutParams
            val animalResourse = randomUtil.animalsImagesMap.get(it)
            if (animalResourse != null) {
                animalImageView.setImageResource(animalResourse)
            }
            binding.animalsLayout.addView(animalImageView)

        }


    }

    override fun onResume() {
        addAnimalView()
        super.onResume()
    }

    fun convertDpToPixel(dp: Float, context: Context): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }



}


