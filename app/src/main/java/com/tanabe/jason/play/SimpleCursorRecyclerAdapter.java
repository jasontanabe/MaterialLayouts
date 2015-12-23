package com.tanabe.jason.play;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public abstract class SimpleCursorRecyclerAdapter<VH extends SimpleViewHolder> extends
        CursorRecyclerAdapter<VH> {

    protected int mLayout;
    protected int[] mFrom;
    protected int[] mTo;
    protected String[] mOriginalFrom;

    public SimpleCursorRecyclerAdapter (int layout, Cursor c, String[] from, int[] to) {
        super(c);
        mLayout = layout;
        mTo = to;
        mOriginalFrom = from;
        findColumns(c, from);
    }

    @Override
    public void onBindViewHolderChild(VH holder, Cursor cursor) {
        final int count = mTo.length;
        final int[] from = mFrom;
        Log.d("test2", "onBindViewHolderChild");

        for (int i = 0; i < count; i++) {
            holder.views[i].setText(cursor.getString(from[i]));
        }
    }

    /**
     * Create a map from an array of strings to an array of column-id integers in cursor c.
     * If c is null, the array will be discarded.
     *
     * @param c the cursor to find the columns from
     * @param from the Strings naming the columns of interest
     */
    private void findColumns(Cursor c, String[] from) {
        if (c != null) {
            int i;
            int count = from.length;
            if (mFrom == null || mFrom.length != count) {
                mFrom = new int[count];
            }
            for (i = 0; i < count; i++) {
                mFrom[i] = c.getColumnIndexOrThrow(from[i]);
            }
            Log.d("test2", "find Column: " + mFrom[0]);
        } else {
            mFrom = null;
        }
    }

    @Override
    public Cursor swapCursor(Cursor c) {
        findColumns(c, mOriginalFrom);
        Log.d("test2", "swapCursor");
        return super.swapCursor(c);
    }
}

class SimpleViewHolder extends RecyclerView.ViewHolder
{
    public TextView[] views;

    public SimpleViewHolder (View itemView, int[] to)
    {
        super(itemView);
        views = new TextView[to.length];
        for(int i = 0 ; i < to.length ; i++) {
            views[i] = (TextView) itemView.findViewById(to[i]);
        }
    }
}
