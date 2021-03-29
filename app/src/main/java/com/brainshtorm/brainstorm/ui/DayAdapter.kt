package com.brainshtorm.brainstorm.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.brainshtorm.brainstorm.R
import com.brainshtorm.brainstorm.SessionManager
import com.brainshtorm.brainstorm.data.DayProgress
import com.brainshtorm.brainstorm.data.GamePreview
import com.brainshtorm.brainstorm.databinding.DayProgressItemBinding
import com.brainshtorm.brainstorm.databinding.ItemGameBinding

class DayAdapter(private val dayProgressList: List<Int>, val context : Context) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    lateinit var binding: DayProgressItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        binding = DayProgressItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DayViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return   dayProgressList.size

    }




    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bindView(dayProgressList[position])
    }

    class DayViewHolder(private val binding: DayProgressItemBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        val sessionManager : SessionManager = SessionManager(context)

        @SuppressLint("ResourceAsColor")
        fun bindView(index : Int){
            val firstDay = sessionManager.getValueInt(SessionManager.FIRST_DAY)
            val currentDay = DayProgress.getNextDay(firstDay) - index
            println("currentDay = ${currentDay}")
            when {
                currentDay>0 -> {
                    binding.dayProgressImage.setImageResource(R.drawable.ic_day_success)
                    binding.dayNumber.setTextColor(R.color.mental_arithmetic_color)
                }
                currentDay<0 -> {
                    binding.dayProgressImage.setImageResource(R.drawable.ic_day_lock)
                    binding.dayNumber.setTextColor(R.color.white_64)
                }
                else -> {
                    binding.dayProgressImage.setImageResource(R.drawable.ic_day_current)
                    binding.dayNumber.setTextColor(R.color.progress_color)
                }
            }
            binding.dayNumber.text = (index + 1).toString()


        }
    }
}