package com.startzplay.presentation.viewModels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.startzplay.presentation.activities.PlayerActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {

    fun launchPlayerActivity() {
        val intent = Intent(context, PlayerActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        context.startActivity(intent)
    }
}