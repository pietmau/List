package com.pppp.travelchecklist.profile

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.facebook.Profile
import com.pppp.travelchecklist.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_profile_view.view.name
import kotlinx.android.synthetic.main.custom_profile_view.view.profile_image

class ProfileView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_profile_view, this, true)
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        setUp(context)
    }

    private fun setUp(context: Context) {
        GoogleSignIn.getLastSignedInAccount(context)?.let {
            setUpGoogleProfile(it)
            return
        }
        Profile.getCurrentProfile()?.let {
            setUpFacebookProfile(it)
            return
        }
        visibility = View.GONE
    }

    private fun setUpFacebookProfile(profile: Profile) {
        val dimensionPixelSize = resources.getDimensionPixelSize(com.facebook.R.dimen.com_facebook_profilepictureview_preset_size_large)
        val photoUrl = profile.getProfilePictureUri(dimensionPixelSize, dimensionPixelSize)
        setNameAndPicture(profile.firstName, photoUrl)
    }

    private fun setUpGoogleProfile(googleSignInAccount: GoogleSignInAccount) {
        val displayName = googleSignInAccount.displayName
        val photoUrl = googleSignInAccount.photoUrl
        setNameAndPicture(displayName, photoUrl)
    }

    private fun setNameAndPicture(displayName: String?, photoUrl: Uri?) {
        name.text = displayName
        Picasso.get().load(photoUrl).into(profile_image);
    }
}