package com.jetbrains.greeting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import greetingkmp.composeapp.generated.resources.Res
import greetingkmp.composeapp.generated.resources.compose_multiplatform
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieComposition
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.Url
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    val animFromUrl by rememberLottieComposition {
                        LottieCompositionSpec.Url("https://example.com/anim.lotie")
                    }
//                    val composition: LottieComposition? by rememberLottieComposition(spec)
                    Image(
                        modifier = Modifier.size(50.dp),
                        painter = rememberLottiePainter(
                            composition = animFromUrl,
                            iterations = Compottie.IterateForever
                        ),
                        contentDescription = "Lottie animation"
                    )
                    Text("Compose: $greeting")
                }
            }
        }
    }
}