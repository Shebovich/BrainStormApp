package com.brainshtorm.brainstorm.RandomUtils

import com.brainshtorm.brainstorm.R
import kotlin.random.Random

class RandomUtil {
    private var variantAnswersArray = arrayOf("A","B","C","D")
    private var cubesNumbersArray = arrayOf(1,2,3,4,5,6)
    private var rotationRightAngleArray  = arrayOf(-270, -180, -90, 0, 90, 180, 270)
    public fun getRandomRightAngle() : Int = rotationRightAngleArray.random();
    fun getRandomAngle() : Int = Random.nextInt(-360,360)
    private var operationsArray = arrayOf("*","/","-","+")

    public var animalsArray = arrayOf("Camel","Fox","Hippo","Shark","Dog","Horse")
    var quizResultAnimal = getRandomOftenAnimal()

    public var quizAnimalsArray =  addRandomOftenAnimalInQuizArray()
    public var animalsImagesMap = mutableMapOf("Camel" to R.drawable.camel,
        "Fox" to R.drawable.fox,
        "Hippo" to R.drawable.hippo,
        "Shark" to R.drawable.shark,
        "Dog" to R.drawable.dog,
        "Horse" to R.drawable.horse)
    fun getRandomOftenAnimal() : String {
        val randomValue = Random.nextInt(animalsArray.size)
        return animalsArray[randomValue]
    }
    fun getRandomAnimalImage() : Int? {
        val randomValue = Random.nextInt(animalsArray.size)
        return animalsImagesMap[animalsArray[randomValue]]
    }
    fun addRandomOftenAnimalInQuizArray() : ArrayList<String>{
        val quizAnimalsArray : ArrayList<String> = ArrayList()
        quizAnimalsArray.add(quizResultAnimal)
        quizAnimalsArray.addAll(animalsArray)
        return quizAnimalsArray

    }

}