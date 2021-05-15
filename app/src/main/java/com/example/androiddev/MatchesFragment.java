package com.example.androiddev;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddev.model.Match;
import com.example.androiddev.viewmodels.MatchesViewModel;

import java.util.ArrayList;


public class MatchesFragment extends Fragment {

    public ArrayList matchesList = new ArrayList();
    private MatchesViewModel viewModel;
    private FragmentManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            matchesList = getArguments().getParcelableArrayList(Constants.KEY_MATCHES);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment with the ProductGrid theme
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        viewModel = new MatchesViewModel();

        viewModel.getMatches(
                (ArrayList<Match> matches) -> {
                    RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
                    MatchesCardRecyclerViewAdapter adapter = new MatchesCardRecyclerViewAdapter(matches);
                    recyclerView.setAdapter(adapter);
                    int largePadding = getResources().getDimensionPixelSize(R.dimen.matches_grid_spacing);
                    int smallPadding = getResources().getDimensionPixelSize(R.dimen.matches_grid_spacing_small);
                    recyclerView.addItemDecoration(new MatchesGridDecoration(largePadding, smallPadding));
                });



        return view;
    }



}