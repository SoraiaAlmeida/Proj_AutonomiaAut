package br.edu.fateczl.proj_autonomiaaut;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtConsumoMedio;
    private EditText edtquantidadeComb;
    private TextView tvRes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtConsumoMedio = findViewById(R.id.edtConsumoMedio);
        edtConsumoMedio.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        edtquantidadeComb = findViewById(R.id.edtQuantidadeComb);
        edtquantidadeComb.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(op -> Calcular());

    }

    private void Calcular() {

        String consumoMedioStr = edtConsumoMedio.getText().toString();
        String quantidadeCombStr = edtquantidadeComb.getText().toString();

        if (consumoMedioStr.isEmpty() && quantidadeCombStr.isEmpty()) {
            tvRes.setText("");
            return;
        }


        if (!consumoMedioStr.isEmpty() && !quantidadeCombStr.isEmpty()) {
            try {
                float consumoMedio = Float.parseFloat(consumoMedioStr);
                float quantidadeComb = Float.parseFloat(quantidadeCombStr);

                float autonomia = (quantidadeComb  * 1000) / consumoMedio ;
                tvRes.setText("Autonomia: " + String.format("%.2f", autonomia) + " metros");

            } catch (NumberFormatException e) {

                Toast.makeText(MainActivity.this, "Por favor, insira valores válidos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();


        }
    }
}
