package com.shopping.android.product

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shopping.android.components.BarColumn
import com.shopping.android.components.RatingBar
import com.shopping.common.domain.model.Customer
import com.shopping.common.domain.model.valueObject.Email
import com.shopping.common.domain.model.valueObject.Password
import com.shopping.common.domain.model.valueObject.Rating
import com.shopping.common.domain.model.valueObject.Review

@Composable
fun ProductReviewsList(reviews: Set<Review>, modifier: Modifier = Modifier) {
    LazyColumnFor(items = reviews.toList(), modifier) { review ->
        ProductReviewItem(review)
    }
}

@Composable
fun ProductReviewItem(review: Review, modifier: Modifier = Modifier) {

    val customer = Customer("", "John doe", Email(""), Password(""))

    Row(
        modifier
            .fillMaxWidth()
    ) {

        Box(
            Modifier
                .clip(CircleShape)
                .background(Color.Cyan)
                .size(64.dp)
                .fillMaxSize(0.3F)
        )

        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 16.dp),
            Arrangement.SpaceEvenly,
        ) {

            val numberOfSelected = review.rating.ordinal.plus(1)
            val numberOfNotSelected = 5 - numberOfSelected

            Text(
                text = customer.name,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 22.sp,
            )

            RatingBar(
                numberOfSelected,
                numberOfNotSelected,
                Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 8.dp)
            )

            review.description?.let {

                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp)
                )

            }
        }

    }

}

@Composable
fun ProductReviews(reviews: Set<Review>, modifier: Modifier = Modifier) {
    Column(modifier) {
        ProductReviewsRating(reviews)
        ProductReviewsList(reviews)
    }
}

@Composable
fun ProductReviewsRating(reviews: Set<Review>, modifier: Modifier = Modifier) {

    val avgRating = reviews
        .map { it.rating.ordinal.plus(1) }
        .sum()
        .toDouble()
        .div(reviews.size)

    Row(modifier) {
        Text(
            text = avgRating.toString(),
            fontSize = 80.sp,
            modifier = Modifier
                .padding(16.dp)
        )
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Rating.values().forEach { rating ->

                val filteredRating = reviews.filter { it.rating == rating }
                    .sumBy { it.rating.ordinal.plus(1) }
                    .toFloat()
                    .div(reviews.count { it.rating == rating })

                Row {
                    Text(text = rating.ordinal.plus(1).toString())
                    BarColumn(height = 12.dp, fraction = filteredRating)
                }
            }
        }
    }

}