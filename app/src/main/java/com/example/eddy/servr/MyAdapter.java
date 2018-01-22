package com.example.eddy.servr;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eddy.servr.Activities.BufferingActivity;
import com.example.eddy.servr.Activities.ServiceItemActivity;

import java.util.ArrayList;

/** November 13, 2017
 *  Darren Liu
 *
 *      Class to create card view
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<ArrayList<String>> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView serviceTitle;
        TextView serviceDescription;
        MyViewHolder(View v) {
            super(v);

            mCardView = v.findViewById(R.id.card_view);
            serviceTitle = v.findViewById(R.id.tv_text);
            serviceDescription = v.findViewById(R.id.tv_description);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<ArrayList<String>> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.serviceTitle.setText(mDataset.get(position).get(2));
        holder.serviceDescription.setText(mDataset.get(position).get(3));
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceItemActivity.toolbarName = mDataset.get(position).get(2);
                ServiceItemActivity.description = mDataset.get(position).get(3);
                ServiceItemActivity.price = mDataset.get(position).get(4);
                ServiceItemActivity.user = BufferingActivity.servr.getUser(Integer.parseInt(mDataset.get(position).get(1).replaceAll(" ", "")));
                Intent i = new Intent(view.getContext(), ServiceItemActivity.class);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}