package com.pppp.travelchecklist.profile

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
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
        val googleSignInAccount = GoogleSignIn.getLastSignedInAccount(context)
        if (googleSignInAccount == null) {
            visibility = View.GONE
        } else {
            setNameAndPicture(googleSignInAccount)
        }
    }

    private fun setNameAndPicture(googleSignInAccount: GoogleSignInAccount) {
        name.text = googleSignInAccount.displayName
        Picasso.get().load(googleSignInAccount.photoUrl).into(profile_image);
    }
}