package br.com.testeomie.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.testeomie.R
import br.com.testeomie.presentation.components.SellButtonComponent
import br.com.testeomie.presentation.components.TotalSellingValueComponent
import br.com.testeomie.presentation.components.item.SalesListItem
import br.com.testeomie.ui.theme.TesteOmieTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel,
    onAddClientClick: () -> Unit
) {
    val context = LocalContext.current

    TesteOmieTheme {
        Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
            SellButtonComponent(onClick = {
                onAddClientClick()
            })
        }, topBar = {
            TopAppBar(title = {}, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(id = R.color.omie_color)
            ), navigationIcon = {
                IconButton(onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(context.getString(R.string.omie_url_text)))
                    context.startActivity(intent)
                }, modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_omie_text),
                        contentDescription = stringResource(R.string.omie_button_content_description),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            })
        }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp, bottom = 70.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                val salesList = homeScreenViewModel.salesFlow.collectAsState(listOf()).value
                if (salesList.isNotEmpty()) {
                    Column {
                        val totalSellingValue =
                            salesList.flatMap { it.products.orEmpty() }
                                .sumOf { it.quantity * it.value }
                        TotalSellingValueComponent(
                            modifier = Modifier.wrapContentWidth(),
                            totalSellingValue = totalSellingValue
                        )
                        LazyColumn {
                            items(salesList) { item ->
                                SalesListItem(item, onConfirmDelete = { uuid ->
                                    homeScreenViewModel.onConfirmDelete(uuid)
                                })
                            }
                        }
                    }
                } else {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.empty_selling_list_text),
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}