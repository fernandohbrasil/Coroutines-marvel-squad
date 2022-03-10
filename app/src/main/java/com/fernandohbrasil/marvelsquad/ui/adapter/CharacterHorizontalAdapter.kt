package com.fernandohbrasil.marvelsquad.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fernandohbrasil.marvelsquad.databinding.ItemCharacterHorizontalBinding
import com.fernandohbrasil.marvelsquad.datasource.db.model.CharacterEntity
import com.fernandohbrasil.marvelsquad.extensions.loadCircleCrop
import com.fernandohbrasil.marvelsquad.ui.fragment.RootFragmentDirections

class CharacterHorizontalAdapter : ListAdapter<CharacterEntity, ViewHolder>(CharacterEntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return CharacterVerticalViewHolder(
            ItemCharacterHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characterEntity = getItem(position)
        (holder as CharacterVerticalViewHolder).bind(characterEntity)
    }

    inner class CharacterVerticalViewHolder(private val binding: ItemCharacterHorizontalBinding) :
        ViewHolder(binding.root) {

        fun bind(characterEntity: CharacterEntity) {
            binding.apply {
                ivAvatar.loadCircleCrop(characterEntity.imageUrl)
                tvName.text = characterEntity.name
            }
        }

        init {
            binding.root.setOnClickListener {
                goToCharacter(getItem(adapterPosition).id, it)
            }
        }

        private fun goToCharacter(id: Int, view: View) {
            val direction = RootFragmentDirections.actionRootFragmentToDetailFragment(id)
            view.findNavController().navigate(direction)
        }
    }
}

class CharacterEntityDiffCallback : DiffUtil.ItemCallback<CharacterEntity>() {
    override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity) = oldItem == newItem
}