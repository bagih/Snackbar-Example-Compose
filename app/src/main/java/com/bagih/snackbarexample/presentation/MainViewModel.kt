package com.bagih.snackbarexample.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {
    private var _text = MutableStateFlow<String>("snackbar belum terklik")
    val text = _text.asStateFlow()

    fun setText(msg: String){
      _text.value = msg
    }
}