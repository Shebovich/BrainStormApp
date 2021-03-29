package com.brainshtorm.brainstorm.ui

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.brainshtorm.brainstorm.data.GamePreview
import com.brainshtorm.brainstorm.databinding.ItemGameBinding
import kotlin.coroutines.coroutineContext

class GameAdapter(private val gamePreviewList: ArrayList<GamePreview>, val context: Context, private val onClickListener: (GamePreview) -> Unit) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    lateinit var binding: ItemGameBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GameViewHolder(binding, context)
    }

    override fun getItemCount(): Int {
        return   gamePreviewList.size

    }




    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bindView(gamePreviewList[position])
        holder.itemView.setOnClickListener {
            onClickListener.invoke(gamePreviewList[position])
        }
    }

    inner class GameViewHolder(private val binding: ItemGameBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bindView(gamePreview: GamePreview){

            binding.gameDescription.text = gamePreview.description
            binding.gameName.text = gamePreview.name
            binding.imageGame.setImageResource(gamePreview.imageResource)
            binding.gameType.text = gamePreview.type
            binding.gameType.setTextColor(gamePreview.colorTypeText)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.typeBackground.backgroundTintList = ContextCompat.getColorStateList(context, gamePreview.colorTypeText)
                binding.typeBackground.alpha = 0.08F
            }
            binding.root.setOnClickListener { onClickListener.invoke(gamePreview) }
        }
    }



}