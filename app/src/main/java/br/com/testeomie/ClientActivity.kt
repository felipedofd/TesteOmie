package br.com.testeomie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import br.com.testeomie.presentation.ClientScreen
import br.com.testeomie.presentation.ClientScreenViewModel
import br.com.testeomie.ui.theme.TesteOmieTheme

class ClientActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val clientScreenViewModel = ViewModelProvider(this).get(ClientScreenViewModel::class.java)

        setContent {
            TesteOmieTheme {
                Scaffold {
                    ClientScreen(
                        clientScreenViewModel,
                        onFinish = {
                            setResult(RESULT_OK)
                            finish()
                        }
                    )
                }
            }
        }
    }
}