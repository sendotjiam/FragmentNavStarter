package com.example.myapplication.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.ui.common.BundleKey;
import com.example.myapplication.ui.news.adapter.NewsAdapter;
import com.example.myapplication.ui.news.adapter.OnNewsItemClickListener;

import java.util.ArrayList;

public class NewsListFragment extends Fragment implements OnNewsItemClickListener {

    private NewsListViewModel newsListViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragmentListener();
        initViewModel();
        initView(view);
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.rvNewsList);
        layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList newsList = new ArrayList<String>();
        for(int i=1; i<20; i++) {
            newsList.add("NEWS #"+i);
        }
        adapter = new NewsAdapter(newsList, this);
        recyclerView.setAdapter(adapter);
    }

    private void initViewModel(){
        newsListViewModel = new ViewModelProvider(requireActivity()).get(NewsListViewModel.class);
        newsListViewModel.newsFeedback.observe(getViewLifecycleOwner(), feedback -> {
            showToast(feedback);
        });
    }

    private void initFragmentListener(){
        getParentFragmentManager().setFragmentResultListener(BundleKey.NEWS_FEEDBACK,
                this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String feedback = bundle.getString(BundleKey.NEWS_FEEDBACK);
                showToast(feedback);
            }
        });
    }

    public void showToast(String message){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNewsItemClicked(View view, String newsId) {
        NavDirections action = NewsListFragmentDirections.actionToNewsFragment(newsId);
        Navigation.findNavController(view).navigate(action);
    }

}
