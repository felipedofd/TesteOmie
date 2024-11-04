package br.com.testeomie.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.testeomie.R
import br.com.testeomie.presentation.components.SellButtonComponent
import br.com.testeomie.presentation.components.itemSalesList.SalesListItem
import br.com.testeomie.repository.SalesRepository
import br.com.testeomie.ui.theme.TesteOmieTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val salesRepository = SalesRepository()

    TesteOmieTheme {
        Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
            SellButtonComponent(onClick = {
                showDialog.value = true
            }, Modifier)
            if (showDialog.value) {
                SellingBottomSheetScreen { showDialog.value = false }
            }
        }, topBar = {
            TopAppBar(title = {}, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(id = R.color.omie_color)
            ), navigationIcon = {
                IconButton(onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.omie.com.br"))
                    context.startActivity(intent)
                }, modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_omie_text),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            })
        }) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(stringResource(R.string.empty_selling_list_text))
                }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp, bottom = 70.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                val salesList = salesRepository.loadSales().collectAsState(mutableListOf()).value
                LazyColumn {
                    itemsIndexed(salesList) { position, _ ->
                        SalesListItem(position, salesList)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}