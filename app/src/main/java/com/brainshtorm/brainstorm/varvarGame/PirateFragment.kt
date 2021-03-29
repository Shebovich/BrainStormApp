package com.brainshtorm.brainstorm.varvarGame

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.brainshtorm.brainstorm.R
import com.brainshtorm.brainstorm.databinding.PirateLayoutBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class PirateFragment : Fragment() {
    lateinit var questionGenerator : QuestionGenerator
    lateinit var binding: PirateLayoutBinding
    lateinit var currentQuestion: PirateQuestion
    var currentLevel = 1
    lateinit var questionList: ArrayList<PirateQuestion>
    var resultMapImageId = mapOf(
        1 to R.id.resultImage_one,
        2 to R.id.resultImage_two,
        3 to R.id.resultImage_three,
        4 to R.id.resultImage_fourth
    )
    private val resultMapId = mapOf(
        R.id.first_layout to 1,
        R.id.second_layout to 2,
        R.id.third_layout to 3,
        R.id.fourth_layout to 4
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PirateLayoutBinding.inflate(inflater)
        currentLevel = 1
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionGenerator = context?.let { QuestionGenerator(it) }!!
        questionList = questionGenerator.initLevelOfGame(currentLevel)
        currentQuestion = questionList.last()
        initViewOfGame()
    }

    private fun initViewOfGame() {
        binding.questionText.text = fromHtml(currentQuestion.question)
        binding.firstLayout.setOnClickListener {

            resultMapId[binding.firstLayout.id]?.let { it1 -> checkIfRightAnswer(it1) }
        }
        binding.secondLayout.setOnClickListener {
            resultMapId[binding.secondLayout.id]?.let { it1 -> checkIfRightAnswer(it1) }
        }
        binding.thirdLayout.setOnClickListener {
            resultMapId[binding.thirdLayout.id]?.let { it1 -> checkIfRightAnswer(it1) }
        }
        binding.fourthLayout.setOnClickListener {
            resultMapId[binding.fourthLayout.id]?.let { it1 -> checkIfRightAnswer(it1) }
        }
    }

    fun fromHtml(html: String?): Spanned? {
        return if (html == null) {
            // return an empty spannable if the html is null
            SpannableString("")
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            // we are using this flag to give a consistent behaviour
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    private fun checkIfRightAnswer(buttonId: Int) {
        println("buttonId = $buttonId result = ${currentQuestion.result}")
        if (currentQuestion.result.size >= 1) {
            if (currentQuestion.result.contains(buttonId)) {
                showSuccessView(buttonId)
            } else {
                showFailure(buttonId)
            }
        } else {
            if (buttonId == currentQuestion.idButton) {
                showSuccessView(buttonId)

            } else {
                showFailure(buttonId)
                resetGame()
            }
        }
    }

    private fun resetGame() {
        currentLevel = 1
        questionList = questionGenerator.initLevelOfGame(currentLevel)
        currentQuestion = questionList.last()
        binding.questionText.text = fromHtml(currentQuestion.question)

    }

    private fun showProgress() {

    }

    private fun nextRound() {
        questionList.remove(currentQuestion)
        if (questionList.size > 0) {
            currentQuestion = questionList.last()
            binding.questionText.text = fromHtml(currentQuestion.question)
        } else {
            if (currentLevel <= 3) {
                currentLevel++
                questionList = questionGenerator.initLevelOfGame(currentLevel)
                currentQuestion = questionList.last()
                binding.questionText.text = fromHtml(currentQuestion.question)
            } else {
                showProgress()
            }

        }
    }

    private fun showSuccessView(buttonId: Int) {
        val resultImage = resultMapImageId[buttonId]?.let { view?.findViewById<ImageView>(it) }
        if (resultImage != null) {
            resultImage.setImageResource(R.drawable.ic_checked)
            resultImage.visibility = View.VISIBLE
            GlobalScope.launch(Dispatchers.Main) {
                delay(1000)
                resultImage.visibility = View.GONE
                nextRound()
            }

        }
    }

    private fun showFailure(buttonId: Int) {

        val resultImage = resultMapImageId[buttonId]?.let { view?.findViewById<ImageView>(it) }
        if (resultImage != null) {
            resultImage.setImageResource(R.drawable.ic_error)
            resultImage.visibility = View.VISIBLE
            GlobalScope.launch(Dispatchers.Main) {
                delay(1000)
                resultImage.visibility = View.GONE
            }
        }
    }


}