package com.example.tbcacademyhomework.util

import android.content.Context
import com.example.tbcacademyhomework.R

fun Double.asStatus(): ActivationStatus {
    return when {
        this <= 0.0 -> ActivationStatus.INACTIVE
        this == 1.0 -> ActivationStatus.ONLINE
        this == 2.0 -> ActivationStatus.RECENTLY_ACTIVE
        this > 2 && this < 23 -> ActivationStatus.PREVIOUSLY_AVAILABLE
        else -> {
            ActivationStatus.DISABLED
        }
    }
}


fun ActivationStatus.toStatusString(context:Context):String{
    return with(context){
        when (this@toStatusString){
            ActivationStatus.INACTIVE -> getString(R.string.inactive)
            ActivationStatus.ONLINE -> getString(R.string.online)
            ActivationStatus.RECENTLY_ACTIVE -> getString(R.string.recently_active)
            ActivationStatus.PREVIOUSLY_AVAILABLE -> getString(R.string.previously_available)
            ActivationStatus.DISABLED -> getString(R.string.disabled)
        }

    }
}