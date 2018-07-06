package com.example.khaledmahmoud.medicindectionary;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.khaledmahmoud.medicindectionary.data_model.Drug;
import com.example.khaledmahmoud.medicindectionary.fragments.CallBackInterface;
import com.example.khaledmahmoud.medicindectionary.fragments.DetailsFragment;
import com.example.khaledmahmoud.medicindectionary.fragments.MyListFragment;


public class MainActivity extends AppCompatActivity  implements CallBackInterface{

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Drug savedDrug,passedDrug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.activity_portrait) != null)
        {
            if (savedInstanceState == null)
            {
                addListFragment();
            }else {
                addDeatailFragmetnt((Drug)savedInstanceState.getSerializable("savedDrug"));
            }
        }
        else if (findViewById(R.id.activity_land_scape) != null)
        {

            addListFragment();
            if (savedInstanceState == null )
            {
                addDeatailFragmetnt(R.id.main_fragment_details,null);

            }else {
                addDeatailFragmetnt(R.id.main_fragment_details,(Drug)savedInstanceState.getSerializable("savedDrug"));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("savedDrug",savedDrug);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedDrug = (Drug) savedInstanceState.getSerializable("savedDrug");
    }

    private void addListFragment()
    {
        fragmentTransaction = fragmentManager.beginTransaction();
        MyListFragment listFragment = new MyListFragment();
        listFragment.setCallBackInterface(this);
        fragmentTransaction.add(R.id.main_fragment_list_or_details,listFragment);
        fragmentTransaction.commit();
    }



    private void addDeatailFragmetnt(Drug drug)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable("MYDRUG",drug);
        fragmentTransaction = fragmentManager.beginTransaction();
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.main_fragment_list_or_details,detailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void addDeatailFragmetnt(int fragmentId,Drug drug)
    {
        Bundle bundle = new Bundle();
        bundle.putSerializable("MYDRUG",drug);
        fragmentTransaction = fragmentManager.beginTransaction();
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        fragmentTransaction.replace(fragmentId,detailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void callBackMethod(Drug drug) {
        savedDrug = drug;

        if(findViewById(R.id.activity_land_scape) == null)
        {
            addDeatailFragmetnt(drug);
        }
        else{
            addDeatailFragmetnt(R.id.main_fragment_details,drug);

        }


    }
}
