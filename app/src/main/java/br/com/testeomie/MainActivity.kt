package br.com.testeomie

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import br.com.testeomie.presentation.HomeScreen
import br.com.testeomie.presentation.HomeScreenViewModel
import br.com.testeomie.ui.theme.TesteOmieTheme

class MainActivity : ComponentActivity() {

    private val homeScreenViewModel: HomeScreenViewModel by lazy {
        ViewModelProvider(this).get(HomeScreenViewModel::class.java)
    }

    private val clientLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                homeScreenViewModel.onLoadSales()
            }
        }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            HomeScreen(homeScreenViewModel, onAddClientClick = {
                clientLauncher.launch(Intent(this, ClientActivity::class.java))
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    TesteOmieTheme {
//        HomeScreen()
    }
}