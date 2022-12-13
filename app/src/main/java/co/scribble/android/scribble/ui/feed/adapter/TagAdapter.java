package co.scribble.android.scribble.ui.feed.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import co.scribble.android.scribble.databinding.ItemStoryTagBinding;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.MyViewHolder> {
    private List<String> tags = new ArrayList<>();

    public TagAdapter(List<String> tags) {
        if(tags.get(0) == null) return;
        this.tags = tags;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStoryTagBinding binding = ItemStoryTagBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.storyTag.setText(tags.get(position));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final Chip storyTag;

        public MyViewHolder(@NonNull ItemStoryTagBinding binding) {
            super(binding.getRoot());
            storyTag = binding.storyTag;
        }
    }
}
