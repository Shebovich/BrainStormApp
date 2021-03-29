package com.brainshtorm.brainstorm.data

import com.brainshtorm.brainstorm.R

data class GamePreview(val name: String, val description: String,
                       val type: String, val colorTypeText: Int,
                       val imageResource: Int, val isPrivate : Boolean) {

    companion object{
        val MEMORY = "Memory"
        val QUICK_THINKING = "Quick-thinking"
        val MENTAL_ARITHMETIC = "Mental arithmetic"
        val ATTENTION = "Attention"
        val LOGIC = "Logic"
        fun generateQuickMathView(): GamePreview {
            return GamePreview("Qick math", "Make operations with numbers", MENTAL_ARITHMETIC,
                    R.color.mental_arithmetic_color, R.drawable.ic_quick_math_icon, false)

        }

        fun generateMazeView(): GamePreview {
            return GamePreview("Maze", "Find a way out", QUICK_THINKING , R.color.quick_thinking_color, R.drawable.ic_maze_icon, true)
        }

        fun generatePirateGame(): GamePreview {
            return GamePreview("Pirate", "Follow the steps", ATTENTION,
                    R.color.attention_color, R.drawable.ic_pirate_icon, false)

        }

        fun generateMatrixView(): GamePreview {
            return GamePreview("Matrix", "Memory", MEMORY, R.color.memory_color, R.drawable.ic_matrix_icon, false)

        }
        fun generateOneExtraGame(): GamePreview {
            return GamePreview("One extra", "Find an extra figure", ATTENTION,
                    R.color.attention_color, R.drawable.ic_one_extra_icon, false)

        }

        fun generateComponentGame(): GamePreview {
            return GamePreview("Component", "Pick the right detail", LOGIC,
                    R.color.logic_color, R.drawable.ic_component_icon, false)

        }
        fun getAllGames():ArrayList<GamePreview> {
           val gamePreviewList = ArrayList<GamePreview>()
            gamePreviewList.add(generateQuickMathView())
            gamePreviewList.add(generateComponentGame())
            gamePreviewList.add(generateMatrixView())
            gamePreviewList.add(generateOneExtraGame())
            gamePreviewList.add(generateMazeView())
            gamePreviewList.add(generatePirateGame())
            return gamePreviewList
        }

    }
}