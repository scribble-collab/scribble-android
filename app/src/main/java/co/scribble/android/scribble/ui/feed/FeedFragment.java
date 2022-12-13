package co.scribble.android.scribble.ui.feed;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.scribble.android.scribble.C;
import co.scribble.android.scribble.databinding.FragmentFeedBinding;
import co.scribble.android.scribble.models.Story;
import co.scribble.android.scribble.network.constant.N;
import co.scribble.android.scribble.ui.feed.adapter.FeedStoryAdapter;
import co.scribble.android.scribble.ui.feed.viewmodel.FeedStoryViewModel;

public class FeedFragment extends Fragment {
    private List<Story> storyList;
    private FeedStoryAdapter adapter;

    public FeedFragment() {
        // Required empty public constructor
    }

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentFeedBinding binding =  FragmentFeedBinding.inflate(getLayoutInflater(), container, false);

        FeedStoryViewModel viewModel = new ViewModelProvider(this).get(FeedStoryViewModel.class);
        viewModel.storyResponse().observe(this, stories -> {
            storyList.addAll(stories);
            adapter.notifyDataSetChanged();
        });

        storyList = new ArrayList<>();
        adapter = new FeedStoryAdapter(storyList);

        RecyclerView storyRV = binding.storyRecyclerView;
        storyRV.setHasFixedSize(true);
        storyRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        storyRV.setAdapter(adapter);

        viewModel.fetchStories(N.STORY_TYPE_ALL, N.STORY_SORT_UPDATED_DESC, 0, 100);
        return binding.getRoot();
    }
}