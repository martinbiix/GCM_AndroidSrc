package com.example.testgcm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
//Importar los metodos de GCMBaseIntentService para poder extender la clase

public class GCMIntentService extends GCMBaseIntentService{

	String TAG = "GCMIntent_testGCM";
	/**
	 * Este método es llamado cuando intentamos registrarnos o borramos del servicio de GCM pero se recibe un error.
	 * 
	 * Lista de errores:
	 *  - ERROR_ACCOUNT_MISSING:   			No hay cuenta de google asociada al dispositivo
	 *  - ERROR_AUTHENTICATION_FAILED:		Contraseña de la cuenta de google incorrecta
	 *  - ERROR_INVALID_PARAMETERS:			La petición no contiene los parámetros esperados. No soporta GCM.
	 *  - ERROR_INVALID_SENDER:				No se reconoce el SenderID(ProjectID) se obtiene de la consola de Google APIs
	 *  - ERROR_PHONE_REGISTRATION_ERROR:	Registro incorrecto del dispositivo, no soporta GCM.
	 *  - ERROR_SERVICE_NOT_AVAIBLE:		El dispositivo no pudo leer la respuesta o el servidor regreso un código 500/503
	 *  									no es un error de la aplicación asi que se debe reintentar mas tarde.
	 */
	@Override
	protected void onError(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		//Error en el registro, tratar el error
		Log.e(TAG,"ERROR: "+arg1);
	}

	/**
	 * Este método es llamado cuando los servidores de GCM envíen un mensaje al dispositivo (previo  envío del mensaje
	 * de nuestro servidor a los servidores de GCM). Si el mensaje contiene datos, éstos están contenidos como extras
	 * en el intent.
	 */
	@Override
	protected void onMessage(Context ctx, Intent intent) {
		// TODO Auto-generated method stub
		//Notificación recibida, realizar acciones con el mensaje recibido
		//Obtener valores para "mensaje", "id", estos valores se mandan desde el servidor PHP, node etc.. mediante peticion post y en formato JSON
		String mensaje = intent.getStringExtra("mensaje"), id = intent.getStringExtra("id");
		Log.i(TAG,"onMessage: "+mensaje+ ":"+id);
	}

	/**
	 * Este método es llamado cuando el dispositivo se da de alta en los servidores GCM
	 * se recibe el Registration ID asignado por GCM. Este valor debe ser enviado y almacenado en nuestro servidor(PHP, nodejs, etc).
	 */
	@Override
	protected void onRegistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		//Registro correcto: enviar el regId a mi servidor
		Log.d(TAG, "onRegistered: "+arg1);
	}
	
	/**
	 * Este método es llamado cuando el dispositivo se da de baja del servicio de GCM.
	 * Debe informarse a nuestro servidor para que también lo elimine.
	 */
	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		//Borrado correcto: informo a mi servidor
		Log.d(TAG, "unRegistered: "+arg1);
	}

}
