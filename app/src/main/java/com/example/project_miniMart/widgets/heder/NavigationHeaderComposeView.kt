package com.example.project_miniMart.widgets.heder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.project_miniMart.R
import com.example.project_miniMart.ui.theme.Styles.roboto16Light
import com.example.project_miniMart.ui.theme.Styles.roboto20Medium
import com.example.project_miniMart.ui.theme.Styles.textStyleRobotoMediumSp12
import com.example.project_miniMart.utils.getCurrentDateFormatted

@Composable
fun NavigationHeaderComposeView(
    titleView: String,
    descriptionView: String,
    modifier: Modifier
) {
    HeaderComposeView(modifier = modifier.height(152.dp)) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val (title, description, date, imageLogo) = createRefs()


            Text(
                text = getCurrentDateFormatted(),
                color = Color.White,
                style = textStyleRobotoMediumSp12,
                modifier = Modifier.constrainAs(date) {
                    top.linkTo(parent.top, margin = 14.dp)
                    start.linkTo(parent.start, margin = 11.dp)
                }
            )

            Text(
                text = titleView,
                style = roboto20Medium,
                color = Color.White,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(date.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })

            Image(
                painter = painterResource(R.drawable.ic_logo),
                null,
                modifier = Modifier
                    .alpha(0.9f)
                    .size(80.dp)
                    .clip(RoundedCornerShape(80.dp))
                    .constrainAs(imageLogo) {
                        end.linkTo(parent.end, margin = 8.dp)
                        bottom.linkTo(parent.bottom, margin = 8.dp)
                    }
            )

            Text(
                descriptionView,
                style = roboto16Light,
                color = Color.White,
                modifier = Modifier.constrainAs(description) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(title.bottom)
                    end.linkTo(imageLogo.start, margin = 8.dp)
                    bottom.linkTo(parent.bottom, margin = 8.dp)
                    width = Dimension.fillToConstraints
                })

        }
    }
}