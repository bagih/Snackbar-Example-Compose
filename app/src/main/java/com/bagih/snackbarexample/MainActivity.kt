package com.bagih.snackbarexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import com.bagih.snackbarexample.presentation.MainViewModel
import com.bagih.snackbarexample.ui.theme.SnackbarExampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    val viewModel : MainViewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackbarExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainPage()
                }
            }
        }
    }
    @Composable
    fun MainPage(){
        val scaffoldState = rememberScaffoldState()
        val text by viewModel.text.collectAsState()

        Scaffold(scaffoldState = scaffoldState) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = text)
                Button(onClick = {
                    viewModel.setText("snackbar di klik")
                    viewModel.viewModelScope.launch {
                        val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                            message = "Apakah saya ganteng?",
                            actionLabel = "Tentu!"
                        )
                        when(snackbarResult){
                            SnackbarResult.Dismissed -> { viewModel.setText("anjay dikacangin") }
                            SnackbarResult.ActionPerformed -> {viewModel.setText("wah tengkyu")}
                        }
                    }
                }) {
                    Text(text = "Show snackbar")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SnackbarExampleTheme {
        Greeting("Android")
    }
}