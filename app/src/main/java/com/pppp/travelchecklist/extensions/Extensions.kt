package com.pppp.travelchecklist.extensions

import android.os.Build
import android.view.View

val View.isMarshmallowOrAbove
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M