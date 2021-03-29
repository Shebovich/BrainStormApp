package com.brainshtorm.brainstorm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.brainshtorm.brainstorm.R
import com.brainshtorm.brainstorm.SessionManager
import com.brainshtorm.brainstorm.data.DayProgress
import com.brainshtorm.brainstorm.data.GamePreview
import com.brainshtorm.brainstorm.databinding.FragmentDailyBinding
import kotlin.random.Random

class FragmentDay : Fragment() {
    lateinit var binding: FragmentDailyBinding
    lateinit var navController: NavController
    var indexList = listOf(0, 1, 2, 3, 4, 5, 6)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDailyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkCurrentDay()
        navController = Navigation.findNavController(view)
        binding.dayProgressRecycler.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = DayAdapter(indexList, context)
        }
        binding.trainingRecycler.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = GameAdapter(ArrayList(generateFourDailyGames()), context, itemOnClick)
        }
    }

    private fun checkCurrentDay() {
        val sessionManager = context?.let { SessionManager(it) }
        if (sessionManager != null) {
            val firstDay = sessionManager.getValueInt(SessionManager.FIRST_DAY)
            println("firstDay = ${DayProgress.getCurrentDay() - firstDay}")
            if (firstDay == 0 || DayProgress.getCurrentDay() - firstDay > 6)
                sessionManager.save(SessionManager.FIRST_DAY, DayProgress.getCurrentDay().toInt())
        }
    }

    private fun generateFourDailyGames(): List<GamePreview> {
        val allGames = GamePreview.getAllGames();
        val picked: MutableSet<GamePreview> = HashSet()
        while (picked.size < 4) {
            picked.add(allGames[Random.nextInt(0, allGames.size)])
        }
        return picked.toList();
    }

    val itemOnClick: (GamePreview) -> Unit = {

        navController.navigate(R.id.action_fragmentDay_to_pirateFragment)
    }

}