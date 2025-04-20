package com.jetbrains.greeting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.chuckerteam.chucker.api.Chucker
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainActivityViewModel by viewModels()

        setContent {
            LaunchedEffect(true) {
                delay(10000)
//                baseContext.startActivity(Chucker.getLaunchIntent(baseContext))
            }
            viewModel
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}