package com.tanabe.jason.play;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class ScrollingActivity extends AppCompatActivity {
    private Context mContext;
    private CoordinatorLayout mCoordLayout;
    private boolean mIsStarred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        mContext = this;
        mCoordLayout = (CoordinatorLayout) findViewById(R.id.coord_layout_scroll);
        mIsStarred = false;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_scroll);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_scroll);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable filledStar = null;
                Drawable borderStar = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    filledStar = getDrawable(R.drawable.ic_star_white_36dp);
                    borderStar = getDrawable(R.drawable.ic_star_border_white_36dp);
                } else {
                    filledStar = getResources().getDrawable(R.drawable.ic_star_white_36dp);
                    borderStar = getResources().getDrawable(R.drawable.ic_star_border_white_36dp);
                }
                if (mIsStarred) {
                    Snackbar.make(view, "Unstarred", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    ((FloatingActionButton)view).setImageDrawable(borderStar);
                    mIsStarred = false;
                } else {
                    Snackbar.make(view, "Starred", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    ((FloatingActionButton)view).setImageDrawable(filledStar);
                    mIsStarred = true;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_edit_scroll:
                Snackbar.make(mCoordLayout, "Editing the page",
                              Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
