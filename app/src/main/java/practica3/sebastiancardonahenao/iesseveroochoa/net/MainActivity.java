package practica3.sebastiancardonahenao.iesseveroochoa.net;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btNuevo,btSalir;
    TextView tvContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btNuevo = findViewById(R.id.btNuevo);
        btSalir = findViewById(R.id.btSalir);
        tvContactos = findViewById(R.id.tvContactos);
        btNuevo.setOnClickListener(e->nuevoContacto());
        btSalir.setOnClickListener(e->finish());

    }


    private void nuevoContacto() {
        Intent i = new Intent(this, NuevoContactoActivity.class);
        i.putExtra("Datos","info");
        startActivityForResoult.launch(i);
    }

    ActivityResultLauncher<Intent> startActivityForResoult= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //si el usuario pulsa OK en la Activity que hemos llamado
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //recuperamos los dados
                        Intent intent = result.getData();
                        assert intent != null;
                        tvContactos.setText(tvContactos.getText()+"\n"+intent.getExtras().getString("Datos"));
                    }
                }
            });



}