package com.tanabe.jason.play;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jason on 12/11/2015.
 */
public class ContactsRecyclerViewAdapter extends RecyclerView
        .Adapter<ContactsRecyclerViewAdapter.ContactDataHolder> {

    private static MyClickListener mClickListener;
    private ArrayList<ContactData> mDataSet;

    public static class ContactDataHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView name;
        ImageView picture;
        ImageView label;

        public ContactDataHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name_crv_item);
            picture = (ImageView) view.findViewById(R.id.picture_crv_item);
            label = (ImageView) view.findViewById(R.id.label_crv_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public ContactsRecyclerViewAdapter(ArrayList<ContactData> dataSet) {
        mDataSet = dataSet;
        for (int i = 0; i < mDataSet.size(); i++) {
            Log.d("TEST", mDataSet.get(i).getName());
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        mClickListener = myClickListener;
    }

    public void addItem(ContactData data, int index) {
        mDataSet.add(data);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataSet.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public ContactDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_recycler_view_item, parent, false);
        ContactDataHolder holder = new ContactDataHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContactDataHolder holder, int position) {
        holder.name.setText(mDataSet.get(position).getName());
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
