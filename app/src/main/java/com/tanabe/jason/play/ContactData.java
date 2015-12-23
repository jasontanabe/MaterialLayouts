package com.tanabe.jason.play;

import android.net.Uri;

/**
 * Created by jason on 12/11/2015.
 */
public class ContactData {
    private String mName;
    private Uri mProfilePic;

    public ContactData(String name, Uri profilePic) {
        mName = name;
        mProfilePic = profilePic;
    }

    public String getName() {
        return mName;
    }

    public Uri getProfilePic() {
        return mProfilePic;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setProfilePic(Uri profilePic) {
        mProfilePic = profilePic;
    }
}
