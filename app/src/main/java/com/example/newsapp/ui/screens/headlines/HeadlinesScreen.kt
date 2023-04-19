package com.example.newsapp.ui.screens.headlines

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun HeadlinesScreen() {
    HeadlinesList(headlineItems = items)
}

@Composable
fun HeadlinesList(headlineItems: List<HeadlineModel>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = headlineItems) {
            HeadlineItem(headlineItem = it)
        }
    }
}

@Composable
fun HeadlineItem(headlineItem: HeadlineModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            model = headlineItem.imageUrl,
            contentDescription = null
        )
        Text(text = headlineItem.author)
        Text(
            text = headlineItem.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold

        )
        Text(
            text = headlineItem.description,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal

        )

    }
}

data class HeadlineModel(
    val imageUrl: String,
    val author: String,
    val title: String,
    val description: String,
)

@Preview
@Composable
fun HeadlineItemPreview() {
    val headlineItem = HeadlineModel(
        imageUrl = "https://media.pff.com/2023/04/USATSI_17111914_168392742_lowres.jpg?w=956&h=538",
        title = "The PFF Analytics 2023 NFL Mock Draft: Four QBs go off the board first, Will Anderson Jr. lands in Seattle | NFL Draft - Pro Football Focus",
        description = "This mock draft exclusively follows analytical thinking by putting added emphasis on positional value, along with PFF grading and player statistics.",
        author = "Arjun Menon and Brad Spielberger",
    )
    HeadlineItem(headlineItem)
}

@Preview
@Composable
fun HeadlinesListPreview() {
    HeadlinesList(items)
}

val items = listOf(
    HeadlineModel(
        imageUrl = "https://media.pff.com/2023/04/USATSI_17111914_168392742_lowres.jpg?w=956&h=538",
        title = "The PFF Analytics 2023 NFL Mock Draft: Four QBs go off the board first, Will Anderson Jr. lands in Seattle | NFL Draft - Pro Football Focus",
        description = "This mock draft exclusively follows analytical thinking by putting added emphasis on positional value, along with PFF grading and player statistics.",
        author = "Arjun Menon and Brad Spielberger",
    ),
    HeadlineModel(
        imageUrl = "https://i.ytimg.com/vi/lJfV0pkAhbY/maxresdefault.jpg",
        title = "The PFF Analytics 2023 NFL Mock Draft: Four QBs go off the board first, Will Anderson Jr. lands in Seattle | NFL Draft - Pro Football Focus",
        description = "This mock draft exclusively follows analytical thinking by putting added emphasis on positional value, along with PFF grading and player statistics.",
        author = "Arjun Menon and Brad Spielberger",
    ),
    HeadlineModel(
        imageUrl = "https://images.mktw.net/im-730595/social",
        title = "The PFF Analytics 2023 NFL Mock Draft: Four QBs go off the board first, Will Anderson Jr. lands in Seattle | NFL Draft - Pro Football Focus",
        description = "This mock draft exclusively follows analytical thinking by putting added emphasis on positional value, along with PFF grading and player statistics.",
        author = "Arjun Menon and Brad Spielberger",
    ),
    HeadlineModel(
        imageUrl = "https://media.cnn.com/api/v1/images/stellar/prod/230418125956-los-angeles-skyline-smog-file-restricted-091420.jpg?c=16x9&q=w_800,c_fill",
        title = "The PFF Analytics 2023 NFL Mock Draft: Four QBs go off the board first, Will Anderson Jr. lands in Seattle | NFL Draft - Pro Football Focus",
        description = "This mock draft exclusively follows analytical thinking by putting added emphasis on positional value, along with PFF grading and player statistics.",
        author = "Arjun Menon and Brad Spielberger",
    ),
    HeadlineModel(
        imageUrl = "https://www.aljazeera.com/wp-content/uploads/2023/04/AP23108586042841-1681874421.jpg?resize=1920%2C1440",
        title = "The PFF Analytics 2023 NFL Mock Draft: Four QBs go off the board first, Will Anderson Jr. lands in Seattle | NFL Draft - Pro Football Focus",
        description = "This mock draft exclusively follows analytical thinking by putting added emphasis on positional value, along with PFF grading and player statistics.",
        author = "Arjun Menon and Brad Spielberger",
    ),
    HeadlineModel(
        imageUrl = "https://www.reuters.com/resizer/rlRygLHOxLT7ZSF8pMzv8ChftoM=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/GA36BC42TRLAZFNFJ3R3WUVOKI.jpg",
        title = "The PFF Analytics 2023 NFL Mock Draft: Four QBs go off the board first, Will Anderson Jr. lands in Seattle | NFL Draft - Pro Football Focus",
        description = "This mock draft exclusively follows analytical thinking by putting added emphasis on positional value, along with PFF grading and player statistics.",
        author = "Arjun Menon and Brad Spielberger",
    ),
    HeadlineModel(
        imageUrl = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/J46TAKE2C5QJFPBLOZDLCJLSZM_size-normalized.jpg&w=1440",
        title = "The PFF Analytics 2023 NFL Mock Draft: Four QBs go off the board first, Will Anderson Jr. lands in Seattle | NFL Draft - Pro Football Focus",
        description = "This mock draft exclusively follows analytical thinking by putting added emphasis on positional value, along with PFF grading and player statistics.",
        author = "Arjun Menon and Brad Spielberger",
    ),
)