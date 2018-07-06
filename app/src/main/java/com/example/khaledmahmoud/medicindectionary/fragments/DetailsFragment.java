package com.example.khaledmahmoud.medicindectionary.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khaledmahmoud.medicindectionary.R;
import com.example.khaledmahmoud.medicindectionary.data_model.Drug;

public class DetailsFragment extends Fragment {
    private View rootView;
    private Context context;
    private TextView name , decs, cat;
    private Drug drug,savedDrug;
    private Bundle bundle;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.detailed_item_fragment,container,false);
        initUi();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        drug = new Drug();
        if(savedInstanceState != null)
        {
                drug = (Drug) savedInstanceState.getSerializable("savedDrug");
        }
        else {
            bundle = getArguments();
            drug = (Drug) bundle.getSerializable("MYDRUG");

        }


        if (drug!= null)
        {
            name.setText(drug.getName());
            decs.setText(drug.getDescription());
            cat.setText(drug.getCategory());

        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("savedDrug",savedDrug);
    }


    private void initUi() {
        context = getContext();
        name = (TextView)rootView.findViewById(R.id.detailed_item_name);
        decs = (TextView)rootView.findViewById(R.id.detailed_item_descrp);
        cat = (TextView) rootView.findViewById(R.id.detailed_item_category);





    }
}
