package com.tanabe.jason.play;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jason on 12/11/2015.
 */
public class ContactsFragment extends android.support.v4.app.Fragment implements
        ContactsRecyclerViewAdapter.MyClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String[] PROJECTION = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
    };
    private final static String[] FROM_COLUMNS = {
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
    };
    private final static int[] TO_IDS = {
            R.id.name_crv_item
    };

    private static final String SELECTION = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY +
            " LIKE ?";
    private static final String SORT_ORDER = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
    private static final int CONTACT_ID_INDEX = 0;
    private static final int CONTACT_KEY_INDEX = 1;


    private RecyclerView mRecyclerView;
    private ContactsRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String mSearchString = "";
    private String[] mSelectionArgs = { mSearchString };

    public ContactsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_contacts);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ContactsRecyclerViewAdapter(R.layout.contacts_recycler_view_item,
                null, FROM_COLUMNS, TO_IDS);
        mRecyclerView.setAdapter(mAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ContactsRecyclerViewAdapter) mAdapter).setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position, View v) {
        Cursor cursor = mAdapter.getCursor();
        cursor.moveToPosition(position);
        Uri uri = ContactsContract.Contacts.getLookupUri(cursor.getLong((CONTACT_ID_INDEX)),
                cursor.getString(CONTACT_KEY_INDEX));
   }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        mSelectionArgs[0] = "%" + mSearchString + "%";
        return new CursorLoader(getActivity(), ContactsContract.Contacts.CONTENT_URI, PROJECTION, SELECTION, mSelectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d("test2", "onLoadFinished: " + cursor.getCount());
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
