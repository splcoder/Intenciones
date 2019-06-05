package com.example.intenciones;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	Button btnOpenWeb;
	Button btnGoogleMap;
	Button btnTakePhoto;
	Button btnSendEmail;
	Button btnCall;

	private final int MY_CALL_PERMISSION = 23;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnOpenWeb = findViewById( R.id.btnOpenWeb );
		btnGoogleMap = findViewById( R.id.btnGoogleMap );
		btnTakePhoto = findViewById( R.id.btnTakePhoto );
		btnSendEmail = findViewById( R.id.btnSendEmail );
		btnCall = findViewById( R.id.btnCall );

		btnOpenWeb.setOnClickListener( this );
		btnGoogleMap.setOnClickListener( this );
		btnTakePhoto.setOnClickListener( this );
		btnSendEmail.setOnClickListener( this );
		btnCall.setOnClickListener( this );
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch( v.getId() ){
			case R.id.btnOpenWeb: {
				//Toasty.info( getApplicationContext(), "OpenWeb", Toast.LENGTH_SHORT, true ).show();
				intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "https://www.google.es" ) );
				break;
			}
			case R.id.btnGoogleMap: {
				//Toasty.info( getApplicationContext(), "GoogleMap", Toast.LENGTH_SHORT, true ).show();
				intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "https://www.google.es/maps/@43.317625,-1.9888375,15z" ) );
				break;
			}
			case R.id.btnTakePhoto: {
				//Toasty.info( getApplicationContext(), "TakePhoto", Toast.LENGTH_SHORT, true ).show();
				intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
				break;
			}
			case R.id.btnSendEmail: {
				//Toasty.info( getApplicationContext(), "SendEmail", Toast.LENGTH_SHORT, true ).show();
				intent = new Intent( Intent.ACTION_SEND );
				intent.setType( "text/plain" );
				intent.putExtra( Intent.EXTRA_SUBJECT, "Asunto" );
				intent.putExtra( Intent.EXTRA_TEXT, "Texto del correo" );
				intent.putExtra( Intent.EXTRA_EMAIL, new String[]{ "www3.spl@gmail.com" } );	// Emails
				// Para whatsapp
				//intent.setPackage( "com.whatsapp" );
				break;
			}
			case R.id.btnCall: {
				//Toasty.info( getApplicationContext(), "Call", Toast.LENGTH_SHORT, true ).show();
				// In the manifest:		<uses-permission android:name="android.permission.CALL_PHONE" />
				String number = "666666666";
				if( ActivityCompat.checkSelfPermission( MainActivity.this, Manifest.permission.CALL_PHONE ) == PackageManager.PERMISSION_GRANTED ){
					intent = new Intent( Intent.ACTION_CALL, Uri.parse( "tel:" + number ) );
				}
				else{
					ActivityCompat.requestPermissions( MainActivity.this, new String[]{ Manifest.permission.CALL_PHONE }, MY_CALL_PERMISSION );
				}
				break;
			}
		}
		// Start
		if( intent != null ){
			startActivity( intent );
		}
	}
}
