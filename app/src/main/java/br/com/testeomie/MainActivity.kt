package br.com.testeomie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import br.com.testeomie.presentation.HomeScreen
import br.com.testeomie.ui.theme.TesteOmieTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000L)
            splashScreen.setKeepOnScreenCondition { false }
        }
        setContent {
            TesteOmieTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    TopAppBar(title = { title }, navigationIcon = {
                        IconButton(onClick = {}, modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_omie_text),
                                contentDescription = "",
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(id = R.color.omie_color)
                    )
                    )
                }) {
                    HomeScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    TesteOmieTheme {
        HomeScreen()
    }
}