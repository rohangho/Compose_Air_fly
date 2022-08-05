package com.example.abc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
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


    ConstraintLayout(
        decoupledConstraints(16.dp),
        Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .horizontalScroll(rememberScrollState())
    ) {
        planeBody()
        planeLeft()
        planeRight()


    }
}

@Composable
fun planeBody() {
    Canvas(
        modifier = Modifier
            .width(200.dp)
            .height(1000.dp)
            .padding(0.dp, 0.dp, 0.dp, 20.dp)
            .layoutId("plane_body")
    ) {
        drawRoundRect(
            color = Color.Black,
            cornerRadius = CornerRadius(10f, 10f),
            style = Stroke(width = 15f, cap = StrokeCap.Square)
        )
    }
}

@Composable
fun planeLeft() {
    Canvas(
        modifier = Modifier
            .width(300.dp)
            .height(100.dp)
            .layoutId("plane_left")
    ) {
        drawRoundRect(
            color = Color.Magenta,
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
            .layoutId("plane_right")
    ) {
        drawRoundRect(
            color = Color.Magenta,
            cornerRadius = CornerRadius(10f, 10f),
            style = Stroke(width = 15f, cap = StrokeCap.Square)
        )
    }
}


private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val planeBody = createRefFor("plane_body")
        val planeWingLeft = createRefFor("plane_left")
        val planeWingRight = createRefFor("plane_right")
        val startGuideline = createGuidelineFromStart(0.5f)


        constrain(planeBody) {
            start.linkTo(parent.absoluteLeft)
            end.linkTo(parent.absoluteRight)
            top.linkTo(parent.top, 20.dp)
        }
        constrain(planeWingLeft) {
            end.linkTo(planeBody.absoluteLeft)
            top.linkTo(planeBody.top)
            bottom.linkTo(planeBody.bottom)
        }
        constrain(planeWingRight) {
            start.linkTo(planeBody.absoluteRight)
            top.linkTo(planeBody.top)
            bottom.linkTo(planeBody.bottom)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AbcTheme {
        DecoupledConstraintLayout()
    }
}