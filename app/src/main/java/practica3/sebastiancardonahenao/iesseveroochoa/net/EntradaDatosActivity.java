package practica3.sebastiancardonahenao.iesseveroochoa.net;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EntradaDatosActivity extends AppCompatActivity implements View.OnClickListener {

    public final static String EXTRA_DATOS_RESULTADO ="practica3.sebastiancardonahenao.iesseveroochoa.startactivityforresult.entradadatosactivity.resultado";
    public final static String EXTRA_DATOS="practica3.sebastiancardonahenao.iesseveroochoa.startactivityforresult.entradadatosactivity.datos";

    EditText et_datos;
    Button btnOk,btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_datos);
        et_datos = findViewById(R.id.et_datos);

        btnOk = findViewById(R.id.btn_Ok);
        btnCancelar = findViewById(R.id.btn_Cancel);

        btnOk.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Ok:
                // Si el EditText no está vacío enviamos el resultado
                if(et_datos.getText().length()!=0) {
                    //guardamos el resultado en el Intent que llamó la actividad, aunque
                    //podríamos crear uno nuevo
                    Intent iBack = getIntent();

                    iBack.putExtra(EXTRA_DATOS_RESULTADO,et_datos.getText().toString());
                    //indicamos que se ha pulsado aceptar y enviamos el Intent
                    setResult(RESULT_OK,iBack);
                    //cerramos la actividad
                    finish();
                }
                break;
            case R.id.btn_Cancel:
                //Indicamos que el usuario ha pulsado Cancelar
                setResult(RESULT_CANCELED);
                //cerramos la actividad
                finish();
                //si el usuario pulsa el botón de back también se devuelve Canceled
                break;
        }
    }

}