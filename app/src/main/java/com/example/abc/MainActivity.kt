package com.example.abc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.snapshots.StateRecord
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.abc.ui.theme.AbcTheme

class MainActivity : ComponentActivity() {


    var seatMap =

        mutableStateMapOf<String, SeatDetail>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setDatafromBackend()



        setContent {
            AbcTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    drawOuterUI()

                }
            }
        }
    }

    private fun setDatafromBackend() {
        seatMap["1a"] = SeatDetail("Seat Type 1", true)
        seatMap["1b"] = SeatDetail("Seat Type 2", true)
        seatMap["1c"] = SeatDetail("Seat Type 3", false)
        seatMap["1d"] = SeatDetail("Seat Type 4", true)
        seatMap["1e"] = SeatDetail("Seat Type 5", true)
    }

    @Composable
    fun drawOuterUI() {
        var seatSelectedToChangeState = remember { mutableStateOf("") }
        Column {
            drawButton(seatMap) {
                seatSelectedToChangeState.value = it
            }

            Divider(
                color = Color.Red,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )

            showSelectDetail(seatSelectedToChangeState) {
                seatMap = it as SnapshotStateMap<String, SeatDetail>
            }

        }
    }

    @Composable
    private fun showSelectDetail(
        seatSelectedToChangeState: MutableState<String>,
        sendUpdateSate: (updatedData: MutableMap<String, SeatDetail>) -> Unit
    ) {
        Button(
            onClick = {
                seatMap.put(
                    seatSelectedToChangeState.value,
                    SeatDetail(seatMap[seatSelectedToChangeState.value]?.seatType, false)
                )!!
                sendUpdateSate(seatMap)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Magenta)
        ) {
            Text(text = "Update State")
        }

        if (!seatSelectedToChangeState.value.isNullOrBlank())
            Text(text = seatMap[seatSelectedToChangeState.value]?.seatType!!)
    }


    @Composable
    fun drawButton(
        seatData: SnapshotStateMap<String, SeatDetail>,
        sendSelectEvent: (String) -> Unit
    ) {
        Column {
            seatData.forEach {
                Button(
                    onClick = {
                        sendSelectEvent(it.key)
                    }, enabled = it.value.stateType,
                    colors = if (it.value.stateType) {
                        ButtonDefaults.buttonColors(backgroundColor = Color.Magenta)
                    } else {
                        ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
                    }
                ) {
                    Text(text = it.value.seatType!!)
                }
            }
        }


    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AbcTheme {
            drawOuterUI()
        }
    }
}

