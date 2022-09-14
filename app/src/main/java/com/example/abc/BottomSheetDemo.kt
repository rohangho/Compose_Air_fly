package com.example.abc

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun MainScreen() {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = {
            it != ModalBottomSheetValue.Expanded
        }
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(10.dp),
        sheetContent = { BottomSheet() },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to bottom sheet playground!",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        else sheetState.show()
                    }
                }
            ) {
                Text(text = "Click to show bottom sheet")
            }
        }
    }
}

@Composable
fun BottomSheet() {
    ConstraintLayout {
        val (heading, crossButton, spacerPadding,deleteContent,deleteIcon,bottomPadding) = createRefs()

        Text(
            text = "Laninnya", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.constrainAs(heading){
                top.linkTo(crossButton.top)
                bottom.linkTo(crossButton.bottom)
                start.linkTo(crossButton.end,12.dp)
            }
        )
        Icon(painter = painterResource(id = R.drawable.ic_baseline_clear_24), contentDescription ="" , modifier = Modifier.constrainAs(crossButton){
            top.linkTo(parent.top,12.dp)
            start.linkTo(parent.start,12.dp)
        } )
        Spacer(modifier = Modifier.height(32.dp).constrainAs(spacerPadding){
            top.linkTo(heading.bottom)
        })
        Text(
            text = "Batalkan Trankshi",
            fontSize = 16.sp, fontWeight = FontWeight.Normal,
            modifier = Modifier.constrainAs(deleteContent){
                top.linkTo(spacerPadding.bottom)
                start.linkTo(heading.start)
            }
        )

        Icon(painter = painterResource(id = R.drawable.ic_outline_delete_24), contentDescription ="" , modifier = Modifier.constrainAs(deleteIcon){
            top.linkTo(deleteContent.top)
            start.linkTo(parent.start,12.dp)
            bottom.linkTo(deleteContent.bottom)
        } )

        Spacer(modifier = Modifier.height(24.dp).constrainAs(bottomPadding){
            top.linkTo(deleteIcon.bottom)
        })
    }

}
