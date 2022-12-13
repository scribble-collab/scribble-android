package co.scribble.android.scribble.ui.feed.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.scribble.android.scribble.C;
import co.scribble.android.scribble.databinding.ItemFeedStoryBinding;
import co.scribble.android.scribble.models.Story;

public class FeedStoryAdapter extends RecyclerView.Adapter<FeedStoryAdapter.MyViewHolder> {

    private final List<Story> stories;

    public FeedStoryAdapter(List<Story> stories){
        this.stories = stories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFeedStoryBinding binding = ItemFeedStoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(stories.get(position).getTitle());
        holder.description.setText(stories.get(position).getDescription());
        holder.tagRV.setAdapter(new TagAdapter(stories.get(position).getTags()));
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;
        private final RecyclerView tagRV;

        public MyViewHolder(@NonNull ItemFeedStoryBinding binding) {
            super(binding.getRoot());

            title = binding.title;
            description = binding.description;
            
            tagRV = binding.tagsRV;
            tagRV.setHasFixedSize(true);
            tagRV.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
    }
}
