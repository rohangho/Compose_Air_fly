package com.example.abc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.example.abc.ui.theme.AbcTheme

class MainActivity : ComponentActivity() {

    val seatWidth = 10
    val seatHeight  = 10
    val noOfSeatInRow = 6
    val totalRow = 31

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            AbcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    drwaMeAPlane()
                }
            }
        }
    }
}

@Composable
fun drwaMeAPlane() {


    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .horizontalScroll(rememberScrollState())
    ) {

        val (
            planeBody, planeLeft, planeRight, seatPlane,
        ) = createRefs()


        val image: Painter = painterResource(id = R.drawable.ic_baseline_airline_seat_recline_normal_24)

        Column(modifier = Modifier.wrapContentWidth().wrapContentHeight().constrainAs(seatPlane){
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top,30.dp)
        }) {
            for (i in 0..30){
                if(i == 15)
                {
                    Divider(color = Color.Transparent, thickness = 40.dp)
                }
                else {
                    Row {
                        Image(painter = image, contentDescription = "")
                        Image(painter = image, contentDescription = "")
                        Image(painter = image, contentDescription = "")
                        //put space here
                        Image(painter = image, contentDescription = "")
                        Image(painter = image, contentDescription = "")
                        Image(painter = image, contentDescription = "")
                    }
                }
            }



        }
        Canvas(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                .constrainAs(planeBody){
                    start.linkTo(seatPlane.absoluteLeft)
                    end.linkTo(seatPlane.absoluteRight)
                    top.linkTo(seatPlane.top,-20.dp)
                    bottom.linkTo(seatPlane.bottom,-20.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        ) {
            drawRoundRect(
                color = Color.Black,
                cornerRadius = CornerRadius(10f, 10f),
                style = Stroke(width = 15f, cap = StrokeCap.Square)
            )
        }
        Canvas(
            modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .constrainAs(planeLeft){
                    end.linkTo(planeBody.absoluteLeft)
                    top.linkTo(planeBody.top)
                    bottom.linkTo(planeBody.bottom)
                }
        ) {
            drawRoundRect(
                color = Color.Magenta,
                cornerRadius = CornerRadius(10f, 10f),
                style = Stroke(width = 15f, cap = StrokeCap.Square)
            )
        }

        Canvas(
            modifier = Modifier
                .width(300.dp)
                .height(100.dp).constrainAs(planeRight){
                    start.linkTo(planeBody.absoluteRight)
                    top.linkTo(planeBody.top)
                    bottom.linkTo(planeBody.bottom)
                }
        ) {
            drawRoundRect(
                color = Color.Magenta,
                cornerRadius = CornerRadius(10f, 10f),
                style = Stroke(width = 15f, cap = StrokeCap.Square)
            )
        }

    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AbcTheme {
        drwaMeAPlane()
    }
}