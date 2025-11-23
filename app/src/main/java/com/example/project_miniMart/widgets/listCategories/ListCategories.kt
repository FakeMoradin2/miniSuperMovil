package com.example.project_miniMart.widgets.listCategories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project_miniMart.domain.models.ProductDomain
import com.example.project_miniMart.ui.theme.AccentDark
import com.example.project_miniMart.ui.theme.Styles.roboto14MediumChipChecked
import com.example.project_miniMart.ui.theme.Styles.roboto14MediumChipUnChecked

/*

Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center // 👈 centra el contenido del ítem
        ) {
            ItemCategory(
                isChecked = item.isCheck,
                label = item.label,
                onClick = { onclick(item.label) }
            )
        }

list = list.map {
                    it.copy(isCheck = it.label == labelCheck)
                }
 */


@Composable
fun ListCategories(
    modifier: Modifier,
    listCategories: List<ChipCategoryItem>,
    onclick: (String) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Fixed(7),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(listCategories) {
            ItemCategory(it.isCheck, it.label) { labelCheck ->
                onclick(labelCheck)
            }
        }
    }
}


@Composable
fun ItemCategory(isCheck: Boolean, text: String, onclick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = { onclick(text) })
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(20.dp),
                color = if (isCheck) Color.Transparent else Color(0x4D4A4A4A)
            )
            //.width(80.dp)
            .height(25.dp)
            .background(
                if (isCheck) AccentDark else Color.White,
                shape = RoundedCornerShape(20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = if (isCheck) roboto14MediumChipChecked else roboto14MediumChipUnChecked
        )
    }
}

//@Preview
@Composable
fun ButtonPill(label: String, isEnable: Boolean = true, color:Color =AccentDark, onclick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = { onclick() }, enabled = isEnable)
            .height(24.dp)
            .background(
                if (isEnable) color else Color.LightGray,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            style = if (isEnable) roboto14MediumChipChecked else roboto14MediumChipUnChecked
        )
    }
}
