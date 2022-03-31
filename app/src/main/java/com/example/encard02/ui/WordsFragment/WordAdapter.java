package com.example.encard02.ui.WordsFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Hit;
import com.example.encard02.data.model.WordEntity;
import com.example.encard02.databinding.ItemRvImageBinding;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<WordEntity> imageList = new ArrayList<>();
    private ItemRvImageBinding binding;


    public void setImageList(List<WordEntity> imageList) {
        this.imageList = imageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRvImageBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new WordViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        holder.onBind(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void onBind(WordEntity hit) {
            binding.tvItemTitle.setText(hit.getWord());
            Glide.with(binding.getRoot()).load(hit.getUrl()).centerCrop()
                    .into(binding.itemImage);
        }
    }
}
