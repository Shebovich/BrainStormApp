package com.brainshtorm.brainstorm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brainshtorm.brainstorm.R
import com.brainshtorm.brainstorm.data.GamePreview
import com.brainshtorm.brainstorm.databinding.FragmentMainBinding


class FragmentMain : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var adapter: GameAdapter
    lateinit var navController : NavController
    private var gamePreviewList : ArrayList<GamePreview> = ArrayList()
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.buttonStartTraining.setOnClickListener { navController.navigate(R.id.action_fragment_main_to_fragmentDay) }
        gamePreviewList = ArrayList()
        gamePreviewList.add(GamePreview.generateQuickMathView())
        gamePreviewList.add(GamePreview.generateComponentGame())
        gamePreviewList.add(GamePreview.generateMatrixView())
        gamePreviewList.add(GamePreview.generateOneExtraGame())
        gamePreviewList.add(GamePreview.generateMazeView())
        gamePreviewList.add(GamePreview.generatePirateGame())


        binding.gamesRecycler.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            adapter = GameAdapter(gamePreviewList, context, itemOnClick)
        }


    }

    val itemOnClick: (GamePreview) -> Unit = {

        navController.navigate(R.id.action_fragment_main_to_pirateFragment)
    }





}