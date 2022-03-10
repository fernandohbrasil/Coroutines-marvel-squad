package com.fernandohbrasil.marvelsquad.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fernandohbrasil.marvelsquad.databinding.ItemCharacterVerticalBinding
import com.fernandohbrasil.marvelsquad.datasource.network.model.Character
import com.fernandohbrasil.marvelsquad.extensions.loadCircleCrop
import com.fernandohbrasil.marvelsquad.ui.fragment.RootFragmentDirections

class CharacterVerticalAdapter :
    PagedListAdapter<Character, RecyclerView.ViewHolder>(CharacterDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterVerticalViewHolder(
            ItemCharacterVerticalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = getItem(position)
        (holder as CharacterVerticalViewHolder).bind(character!!)
    }

    inner class CharacterVerticalViewHolder(private val binding: ItemCharacterVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.apply {
                tvName.text = character.name
                ivAvatar.loadCircleCrop(character.thumbnail.iconImage())
            }
        }

        init {
            binding.root.setOnClickListener {
                goToCharacter(getItem(adapterPosition)!!.id, it)
            }
        }

        private fun goToCharacter(id: Int, view: View) {
            val direction = RootFragmentDirections.actionRootFragmentToDetailFragment(id)
            view.findNavController().navigate(direction)
        }
    }
}

class CharacterDiffCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem
}