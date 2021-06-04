package com.example.myapplication.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.myapplication.R;

public class NewsFragment extends Fragment implements View.OnClickListener {

    private NewsViewModel newsViewModel;
    private NewsListViewModel newsListViewModel;

    Button likeBtn;
    Button interestingBtn;
    Button okayBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initView(view);
    }

    private void initView(View view){
        final TextView textView = view.findViewById(R.id.text_news);
        newsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        likeBtn = view.findViewById(R.id.btnLike);
        likeBtn.setOnClickListener(this);
        interestingBtn = view.findViewById(R.id.btnInteresting);
        interestingBtn.setOnClickListener(this);
        okayBtn = view.findViewById(R.id.btnOkay);
        okayBtn.setOnClickListener(this);
    }

    private void initViewModel(){
        newsListViewModel = new ViewModelProvider(requireActivity()).get(NewsListViewModel.class);
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnLike:
                // Use Fragment Result API to send one time data to previous fragment
                /*Bundle result = new Bundle();
                result.putString(BundleKey.NEWS_FEEDBACK, "I LIKE THIS NEWS");
                getParentFragmentManager().setFragmentResult("requestKey", result);
                Navigation.findNavController(requireView()).popBackStack();*/

                // Use shared ViewModel to send data back to previous fragment
                newsListViewModel.sendFeedback(likeBtn.getText().toString());
                Navigation.findNavController(requireView()).popBackStack();
                break;
            case R.id.btnInteresting:
                newsListViewModel.sendFeedback(interestingBtn.getText().toString());
                Navigation.findNavController(requireView()).popBackStack();
                break;
            case R.id.btnOkay:
                newsListViewModel.sendFeedback(okayBtn.getText().toString());
                Navigation.findNavController(requireView()).popBackStack();
                break;
            default:
                break;
        }
    }
}