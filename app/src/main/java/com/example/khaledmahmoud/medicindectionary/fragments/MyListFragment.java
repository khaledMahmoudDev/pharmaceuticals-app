package com.example.khaledmahmoud.medicindectionary.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.khaledmahmoud.medicindectionary.AddItem;
import com.example.khaledmahmoud.medicindectionary.DB.DBInstructions;
import com.example.khaledmahmoud.medicindectionary.MainActivity;
import com.example.khaledmahmoud.medicindectionary.R;
import com.example.khaledmahmoud.medicindectionary.data_model.Drug;
import com.example.khaledmahmoud.medicindectionary.recyclerView.RecyclerViewAdapter;

import java.util.List;

public class MyListFragment  extends Fragment implements RecyclerViewAdapter.ClickLlistener{


    public RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private DBInstructions dbInstructions1;
    private Button addItem;
    private Drug drug;
    private View rootView;
    private Context context;
    private CallBackInterface callBackInterface;
    List<Drug> drugs;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         rootView = inflater.inflate(R.layout.list_fragment,container,false);
         initUi();

         return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null)
        {
            callBackInterface = (MainActivity)getActivity();


        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void setCallBackInterface(CallBackInterface callBackInterface)
    {
        this.callBackInterface = callBackInterface;

    }

    public void initUi()
    {
        context = getContext();
        dbInstructions1 = new DBInstructions(context);
        dbInstructions1.createDB();
        drugs = dbInstructions1.getAllDrugs();
        Intent intent2 = getActivity().getIntent();

        if(intent2.getSerializableExtra("newDrug") != null)
        {
            drug =(Drug) getActivity().getIntent().getSerializableExtra("newDrug");
            dbInstructions1.addNewGuest(drug.getName(),drug.getCategory(),drug.getDescription());
            drugs = dbInstructions1.getAllDrugs();


        }
        Toast.makeText(context,"drugs size is "+drugs.size(),Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView_Id);
        adapter = new RecyclerViewAdapter(drugs,context,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        addItem = (Button)rootView.findViewById(R.id.addRow);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddItem.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                Long id = (Long) viewHolder.itemView.getTag();
                Toast.makeText(context,"before "+dbInstructions1.getAllDrugs().size(),Toast.LENGTH_LONG).show();

                boolean isDeleted  = dbInstructions1.deleteDrug(id);
                if (isDeleted == true)
                {
                    Toast.makeText(context,"drug was deleted successfully", Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(context,"did not removed "+id,Toast.LENGTH_LONG).show();
                }
                Toast.makeText(context,"after"+dbInstructions1.getAllDrugs().size(),Toast.LENGTH_LONG).show();
                adapter.swapDrug(dbInstructions1.getAllDrugs());

            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onClickItemListener(int clickedItemListener) {
        int id = clickedItemListener;
        Drug drug1 = drugs.get(id);



        if (callBackInterface != null)
        {
            callBackInterface.callBackMethod(drug1);

        }

    }
}
