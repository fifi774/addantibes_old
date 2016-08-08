package com.addantibes.addantibes;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by fifi on 19/07/16.
 */
public class class_accueil extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String[] Texte = new String[] { "Nos réunions", "Venir à l'église", "Annonces de l'église", "Evènements", "Messages vidéos",
                "Messages audio", "Messages écrits"};

        myView = inflater.inflate(R.layout.layout_evenements, container, false);
        accueil_adapter adapter = new accueil_adapter(getActivity(), Texte);
        ListView lv = (ListView)myView.findViewById(R.id.list);
        lv.setAdapter(adapter);*/

        return myView;
    }

}
