package com.example.magicthegathering.presentation.fragment.main_screen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.magicthegathering.databinding.ItemCardMainScreenBinding
import com.example.magicthegathering.domain.model.ui.main_screen.UINewsModel


class CardListRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemsList = arrayListOf<UINewsModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<UINewsModel>) {
        itemsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCardMainScreenBinding.inflate(layoutInflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = itemsList[position]
        val cardViewHolder = holder as CardViewHolder
        cardViewHolder.bind(model)
    }

    override fun getItemCount(): Int = itemsList.size

    inner class CardViewHolder(private val binding: ItemCardMainScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: UINewsModel) {
            with(binding) {
                cardNameTv.text = news.title
                Glide.with(root)
                    .load(news.image)
                    .centerCrop()
                    .into(cardImageIv)

                newsSiteTv.text = news.newsSite
                newsDateTv.text = news.publishDate
            }
        }
    }
}