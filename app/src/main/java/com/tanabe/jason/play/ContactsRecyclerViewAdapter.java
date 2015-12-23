package com.tanabe.jason.play;

import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by jason on 12/11/2015.
 */
public class ContactsRecyclerViewAdapter extends
        SimpleCursorRecyclerAdapter<ContactsRecyclerViewAdapter.ContactDataHolder> {

    private static MyClickListener mClickListener;
    private ArrayList<ContactData> mDataSet;

    public static class ContactDataHolder extends SimpleViewHolder
            implements View.OnClickListener {
        ImageView picture;
        ImageView label;

        public ContactDataHolder(View view, int[] to) {
            super(view, to);
            picture = (ImageView) view.findViewById(R.id.picture_crv_item);
            label = (ImageView) view.findViewById(R.id.label_crv_item);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public ContactsRecyclerViewAdapter(int layout, Cursor c, String[] from, int[] to) {
        super(layout, c, from, to);
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        mClickListener = myClickListener;
    }


    @Override
    public ContactDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("test2", "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(mLayout, parent, false);
        ContactDataHolder holder = new ContactDataHolder(view, mTo);
        return holder;
    }

    @Override
    public void onBindViewHolderChild(ContactDataHolder holder, Cursor cursor) {
        Log.d("test2", "hi");
        super.onBindViewHolderChild(holder, cursor);
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
