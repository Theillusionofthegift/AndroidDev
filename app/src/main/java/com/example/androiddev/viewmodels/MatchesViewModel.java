
package com.example.androiddev.viewmodels;


import com.example.androiddev.datamodels.MatchesModel;
import com.example.androiddev.model.Match;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MatchesViewModel {

    private MatchesModel matchesModel;

    public MatchesViewModel() {
        matchesModel = new MatchesModel();
    }

    public void addMatch(Match m) {
        matchesModel.addMatch(m);
    }

    public void getMatches(Consumer<ArrayList<Match>> responseCallback) {
        matchesModel.getMatches(
                (QuerySnapshot querySnapshot) -> {
                    if (querySnapshot != null) {
                        ArrayList<Match> matches = new ArrayList<>();
                        for (DocumentSnapshot matchesSnap : querySnapshot.getDocuments()) {
                            Match m = matchesSnap.toObject(Match.class);
                            assert m != null;
                            m.uid = matchesSnap.getId();
                            matches.add(m);
                        }
                        responseCallback.accept(matches);
                    }
                },
                (databaseError -> System.out.println("Error reading Todo Items: " + databaseError))
        );
    }

    public void updateMatch(Match m) {
        matchesModel.updateMatchById(m);
    }

    public void clear() {
        matchesModel.clear();
    }
}