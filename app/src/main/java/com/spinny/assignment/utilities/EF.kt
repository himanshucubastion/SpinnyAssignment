package com.spinny.assignment.utilities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


fun Context.toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

fun Activity.actionIntent(cls: Class<*>) = startActivity(Intent(this,cls))
