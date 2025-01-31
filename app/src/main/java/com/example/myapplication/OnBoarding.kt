@file:OptIn(ExperimentalPagerApi::class)

package com.example.myapplication

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoarding(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            /*CommonText(
                textValue = stringResource(id = R.string.login_screen_name), textStyle = TextStyle(
                    color = Color.Green
                )
            )*/
           // HorizontalMultiBrowseCarouselSample()
            Spacer(Modifier.height(45.dp))
            OnBoardCrausal()
            Spacer(Modifier.height(35.dp))
            CommonText(
                textValue = stringResource(id = R.string.Simple_way_to_earn_money), textStyle = TextStyle(
                    color = colorResource(id = R.color.purple1D0330),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(Modifier.height(45.dp))
            CommonText(
                textValue = stringResource(id = R.string.Lorem_simple_dummy), textStyle = TextStyle(
                    color = colorResource(id = R.color.clr5B5B5B),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
            )
            Spacer(Modifier.height(105.dp))
            RoundedButton(text = "Explore") {
                navController.navigate("login_screen")
            }

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HorizontalMultiBrowseCarouselSample() {

    data class CarouselItem(
        val id: Int,
        @DrawableRes val imageResId: Int,
        @StringRes val contentDescriptionResId: Int
    )

    val items =
        listOf(
            CarouselItem(0, R.drawable.pic, R.string.lorem_ipsum),
            CarouselItem(1, R.drawable.pic, R.string.login_screen_name),
            CarouselItem(2, R.drawable.pic, R.string.lorem_ipsum),
            CarouselItem(3, R.drawable.pic, R.string.lorem_ipsum),
            CarouselItem(4, R.drawable.pic, R.string.lorem_ipsum),
        )

    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { items.count() },
        modifier = Modifier
            .width(412.dp)
            .height(221.dp),
        preferredItemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = items[i]
        Image(
            modifier = Modifier
                .height(205.dp)
                .maskClip(MaterialTheme.shapes.extraLarge),
            painter = painterResource(id = item.imageResId),
            contentDescription = stringResource(item.contentDescriptionResId),
            contentScale = ContentScale.Crop
        )
    }
}

@ExperimentalPagerApi
@Composable
fun OnBoardCrausal(
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val imageSlider = listOf(
        painterResource(id = R.drawable.onboard_1),
        painterResource(id = R.drawable.onboard_2),
        painterResource(id = R.drawable.onboard_3)
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(2600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            itemSpacing = 10.dp,
            count = imageSlider.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) { page ->

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.purpleD5CEFF) // Light Blue background
                ),
            ) {
                Image(
                    painter = imageSlider[page],
                    contentDescription = stringResource(R.string.lorem_ipsum),
                    contentScale = ContentScale.Inside,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Spacer(Modifier.height(25.dp))

        Row {
            repeat(pagerState.pageCount) { index ->
                Box(
                    modifier = Modifier
                        .width(if (pagerState.currentPage == index) 30.dp else 15.dp)
                        .height(15.dp)
                        .padding(4.dp)
                        .clip(shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp))
                        .background(
                            if (pagerState.currentPage == index) colorResource(id = R.color.purple7063BF) else colorResource(
                                id = R.color.purpleD5CEFF
                            )
                        )
                        .border(
                            width = 0.dp,
                            color = Color.Transparent,
                            shape = CircleShape
                        )
                ) {

                }
            }
        }
    }
}




