package com.example.tbcacademyhomework

import android.util.Patterns


fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
