package com.example.abc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.layoutId
import com.example.abc.ui.theme.AbcTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AbcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    DecoupledConstraintLayout()
                }
            }
        }
    }
}

@Composable
fun DecoupledConstraintLayout() {


    Row(
        Modifier
            .fillMaxSize()
            .horizontalScroll(rememberScrollState())
            .verticalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        planeLeft()
        planeBody()
        planeRight()
    }

}

@Composable
fun planeBody() {

        Canvas(
            modifier = Modifier
                .width(200.dp)
                .height(1000.dp)
                .padding(0.dp, 20.dp, 0.dp, 20.dp)
                .layoutId("plane_body")
        ) {
            drawRoundRect(
                color = Color.Black,
                cornerRadius = CornerRadius(10f, 10f),
                style = Stroke(width = 20f, cap = StrokeCap.Square)
            )
        }
}

@Composable
fun planeLeft() {
    Canvas(
        modifier = Modifier
            .width(300.dp)
            .height(100.dp)
            .padding(20.dp, 0.dp, 0.dp, 0.dp)
            .layoutId("plane_left")
    ) {
        drawRoundRect(
            color = Color.Black,
            cornerRadius = CornerRadius(10f, 10f),
            style = Stroke(width = 15f, cap = StrokeCap.Square)
        )
    }
}

@Composable
fun planeRight() {
    Canvas(
        modifier = Modifier
            .width(300.dp)
            .height(100.dp)
            .padding(0.dp, 0.dp, 20.dp, 0.dp)
            .layoutId("plane_right")
    ) {
        drawRoundRect(
            color = Color.Black,
            cornerRadius = CornerRadius(10f, 10f),
            style = Stroke(width = 15f, cap = StrokeCap.Square)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AbcTheme {
        DecoupledConstraintLayout()
    }
}