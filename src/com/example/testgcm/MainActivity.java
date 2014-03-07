package com.example.testgcm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;
//Importar libreria para el registro de la app es necesario el archivo gcm.rar

public class MainActivity extends Activity{
	
	/*
	 * SENDER_ID -> projectID obtenido al momento de crear el projecto en la consola de Google
	 * 				al momento de crear el proyecto en la consola de google es necesario habilitar la opcion de
	 * 				"Google Cloud Messaging"
	 */
	
	String TAG = "GCM_Test", SENDER_ID="ProjectID";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Registrar aplicaci—n cuando se ejecuta este codigo es necesario en al Main de la app
		try{
			//Consultar si el dispositivo soporta GCM
			GCMRegistrar.checkDevice(this);
			
			//Comprobar si el manifest esta bien configurado
			GCMRegistrar.checkManifest(this);
			//Buscamos el registration ID en el equipo
			final String regId = GCMRegistrar.getRegistrationId(this);
			//Si el registration ID se encuentra en blanco quiere decir que aun no se ha registrado
			if(regId.equals(""))
				GCMRegistrar.register(this, SENDER_ID); //Lo registramos
			else
				Log.v(TAG,"Ya se encuentra registrado: "+regId); //Si ya se encuentra registrado mostramos el log
		}catch(UnsupportedOperationException e){
			Log.e(TAG,"El dispositivo no soporta GCM");
		}catch(IllegalStateException e){
			Log.e(TAG,"El manifest no esta bien configurado");
		}
		/**
		 * Para borrar el registro utilizar, se puede crear una vista de configuraci—n en donde el usuario
		 * pueda desactivar las notificaciones.
		 * 
		 * GCMRegistrar.unregister(this);
		 */
		
		
	}
}
