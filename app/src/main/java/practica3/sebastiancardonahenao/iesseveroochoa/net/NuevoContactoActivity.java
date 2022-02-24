package practica3.sebastiancardonahenao.iesseveroochoa.net;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class NuevoContactoActivity extends AppCompatActivity implements View.OnClickListener{

    public final static int OPTION_REQUEST_NOMBRE=0;
    public final static int OPTION_REQUEST_APELLIDO=1;
    public final static int OPTION_REQUEST_EMPRESA=2;

    Switch swFav;
    RadioGroup rgEmpresa,rgSexo;
    ImageView ivEntidad,ivSexo,ivFav,ivLlamar;
    CheckBox ckRecordar;
    SeekBar skEdad;
    TextView tvEdad,tvNombre,tvApellido,tvEmpresa;
    EditText etNumero;
    Button btCrear,btCancela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        ivEntidad = findViewById(R.id.ivEntidad);
        ivSexo = findViewById(R.id.ivSexo);
        ivFav = findViewById(R.id.ivFav);
        ivLlamar = findViewById(R.id.ivLlamar);

        rgSexo = findViewById(R.id.rgSexo);
        rgEmpresa = findViewById(R.id.rgEmpresa);

        ckRecordar = findViewById(R.id.ckRecordar);

        tvEdad = findViewById(R.id.tvEdad);
        tvNombre = findViewById(R.id.tvNombreContacto);
        tvApellido = findViewById(R.id.tvApellido);
        tvEmpresa = findViewById(R.id.tvEmpresa);

        etNumero = findViewById(R.id.etNumero);

        skEdad = findViewById(R.id.skEdad);
        swFav = findViewById(R.id.swFav);

        btCrear = findViewById(R.id.btOk);
        btCancela = findViewById(R.id.btCancel);

        btCrear.setOnClickListener(this);
        btCancela.setOnClickListener(this);

        int edadMax=getResources().getInteger(R.integer.maximaedad);
        skEdad.setMax(edadMax);
        skEdad.setProgress(edadMax/2);



        skEdad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvEdad.setText("Edad: "+seekBar.getProgress());
            }
        });

        rgEmpresa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        switch (i) {
                            case R.id.rbEmpresa:
                                ivEntidad.setImageResource(R.drawable.ic_empresa);
                                break;
                            case R.id.rbParticular:
                                ivEntidad.setImageResource(R.drawable.ic_particular);
                                break;
                        }
                    }
        });

        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rbHombre:
                        ivSexo.setImageResource(R.drawable.ic_man);
                        break;
                    case R.id.tbMujer:
                        ivSexo.setImageResource(R.drawable.ic_girl_call);
                        break;
                }
            }
        });

        swFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    ivFav.setVisibility(View.VISIBLE);
                else
                    ivFav.setVisibility(View.INVISIBLE);

            }
        });

        ckRecordar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    ivLlamar.setVisibility(View.VISIBLE);
                }else {
                    ivLlamar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent i;
        //creamos el intent para llamar a la acitividad
        i=new Intent(this,EntradaDatosActivity.class);
        switch (view.getId()) {
            case R.id.tvNombreContacto:
                i.putExtra(EntradaDatosActivity.EXTRA_DATOS,tvNombre.getText().toString());
                startActivityForResult(i,OPTION_REQUEST_NOMBRE);
                break;
            case R.id.tvApellido:
                //enviamos el apellido actual
                i.putExtra(EntradaDatosActivity.EXTRA_DATOS,tvApellido.getText().toString());
                //llamamos a la actividad a la espera de recibir el resultado
                //indicando el código de llamada
                startActivityForResult(i,OPTION_REQUEST_APELLIDO);
                break;

            case R.id.tvEmpresa:
                //enviamos el apellido actual
                i.putExtra(EntradaDatosActivity.EXTRA_DATOS,tvEmpresa.getText().toString());
                //llamamos a la actividad a la espera de recibir el resultado
                //indicando el código de llamada
                startActivityForResult(i,OPTION_REQUEST_EMPRESA);
                break;

            case R.id.btCancel:
                finish();
                break;
            case R.id.btOk:
                String nombre = String.valueOf(tvNombre.getText());
                String apellido = String.valueOf(tvApellido.getText());
                String numero = String.valueOf(etNumero.getText());
                    Intent iBack = getIntent();
                    iBack.putExtra("Datos", nombre+" "+apellido+" :"+numero);
                    setResult(RESULT_OK,iBack);
                    finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            String resultado = data.getStringExtra(EntradaDatosActivity.EXTRA_DATOS_RESULTADO);
            switch (requestCode) {
                case OPTION_REQUEST_NOMBRE:
                    tvNombre.setText(resultado);
                    break;
                case OPTION_REQUEST_APELLIDO:
                    tvApellido.setText(resultado);
                    break;
                case OPTION_REQUEST_EMPRESA:
                    tvEmpresa.setText(resultado);

            }
        }
    }

}