package br.com.testeomie

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import br.com.testeomie.presentation.ClientScreen
import br.com.testeomie.presentation.ClientScreenViewModel
import br.com.testeomie.ui.theme.TesteOmieTheme

class ClientActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clientScreenViewModel = ViewModelProvider(this).get(ClientScreenViewModel::class.java)

        setContent {
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

@Preview(showBackground = true)
@Composable
fun ClientActivityPreview() {
    TesteOmieTheme {
    }
}