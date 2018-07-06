package com.example.khaledmahmoud.medicindectionary.recyclerView;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khaledmahmoud.medicindectionary.DB.DataContract;
import com.example.khaledmahmoud.medicindectionary.R;
import com.example.khaledmahmoud.medicindectionary.data_model.Drug;

import java.util.List;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Drug> mDrugs;
    final private ClickLlistener clickLlistener;

    public RecyclerViewAdapter(List<Drug> drugs,Context context,ClickLlistener clickLlistener) {
        this.mContext = context;
        mDrugs = drugs;
        this.clickLlistener = clickLlistener;
    }



    public interface ClickLlistener
    {
        void onClickItemListener(int clickedItemListener);


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Drug drug = mDrugs.get(position);

        holder.name.setText(drug.getName());
        holder.categoty.setText(drug.getCategory());
        Long id = drug.getId();

        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {

        return mDrugs.size();
    }
    public void swapDrug(List<Drug> sDrug)
    {
        mDrugs = sDrug;
        if (sDrug != null)
        {
            this.notifyDataSetChanged();
        }


    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView categoty;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.item_name);
            categoty = (TextView)itemView.findViewById(R.id.item_category);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            int clickPosition = getAdapterPosition();
            clickLlistener.onClickItemListener(clickPosition);
        }
    }
}
