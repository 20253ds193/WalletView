package com.example.walletview

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaGastos()
        }
    }
}

@Composable
fun PantallaGastos() {
    Column(modifier = Modifier.padding(20.dp)) {

        Text(
            text = "Mis Gastos - WalletView",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Gráfica 1: Pastel
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp),
            factory = { context ->
                val graficaPastel = PieChart(context)

                val gastos = listOf(
                    PieEntry(3000f, "Renta"),
                    PieEntry(1500f, "Comida"),
                    PieEntry(800f, "Transporte")
                )

                val conjunto = PieDataSet(gastos, "")
                conjunto.colors = listOf(
                    Color.parseColor("#4A6FA5"),
                    Color.parseColor("#6B8E23"),
                    Color.parseColor("#D9822B")
                )
                conjunto.valueTextSize = 12f
                conjunto.valueTextColor = Color.WHITE

                graficaPastel.data = PieData(conjunto)
                graficaPastel.isDrawHoleEnabled = false // Pastel completo
                graficaPastel.description.text = "Por categoría"
                graficaPastel.isRotationEnabled = false
                graficaPastel
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Gráfica 2: Barras
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp),
            factory = { context ->
                val graficaBarras = BarChart(context)

                val semanas = listOf(
                    BarEntry(1f, 1200f),
                    BarEntry(2f, 1800f),
                    BarEntry(3f, 900f),
                    BarEntry(4f, 1400f)
                )

                val conjunto = BarDataSet(semanas, "Gasto semanal")
                conjunto.color = Color.parseColor("#3B7A57")
                conjunto.valueTextSize = 11f

                graficaBarras.data = BarData(conjunto)
                graficaBarras.description.text = "Semanas del mes"
                graficaBarras.setScaleEnabled(false)
                graficaBarras
            }
        )
    }
}