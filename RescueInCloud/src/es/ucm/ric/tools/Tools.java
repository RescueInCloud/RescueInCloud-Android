package es.ucm.ric.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.widget.EditText;

public class Tools {
	
	public static String dateToString(Date date){
		String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		return dateString;
	}

	
	public static void askConfirmation(Context context, String title, String message, int iconID, 
					String positiveOption, String negativeOption, final IDialogOperations idialog){
		
		AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(context);
		 
		// Setting Dialog Title
		alertDialog2.setTitle(title);
		 
		// Setting Dialog Message
		alertDialog2.setMessage(message);
		 
		// Setting Icon to Dialog
		alertDialog2.setIcon(iconID);
		 
		// Setting Positive "Yes" Btn
		alertDialog2.setPositiveButton(positiveOption,
		        new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		            	idialog.positiveOperation();
		            }
		        });
		// Setting Negative "NO" Btn
		alertDialog2.setNegativeButton(negativeOption,
		        new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int which) {
		               idialog.negativeOperation();
		                
		            }
		        });
		 
		// Showing Alert Dialog
		alertDialog2.show();
		
		
	}
    
	public static void setEnableEditTextFields(boolean b, EditText... fields){
		
		for(EditText field : fields){
			field.setFocusable(b);
			field.setFocusableInTouchMode(b);
		}
			
	}
	
	/**
     * Creates the specified <code>toFile</code> as a byte for byte copy of the
     * <code>fromFile</code>. If <code>toFile</code> already exists, then it
     * will be replaced with a copy of <code>fromFile</code>. The name and path
     * of <code>toFile</code> will be that of <code>toFile</code>.<br/>
     * <br/>
     * <i> Note: <code>fromFile</code> and <code>toFile</code> will be closed by
     * this function.</i>
     * 
     * @param fromFile
     *            - FileInputStream for the file to copy from.
     * @param toFile
     *            - FileInputStream for the file to copy to.
     */
    public static void copyFile(FileInputStream fromFile, FileOutputStream toFile) throws IOException {
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromChannel = fromFile.getChannel();
            toChannel = toFile.getChannel();
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close();
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close();
                }
            }
        }
    }
    
    
    public static void loadDataBase(Context context, String database_name, int resource_id){
    	File file = context.getDatabasePath(database_name);
		file.getParentFile().mkdirs();// Esto crea todas las carpetas para que esta ruta exista. Esta es la ruta de la base de datos.
		Log.d("paso1", "copiamos la base de datos si no exist��a antes");
		OutputStream os = null;
		InputStream is = null;
		try {
			is = context.getResources().openRawResource(resource_id);
			os = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			os.flush();
		} 
		catch (Throwable t) {
		} finally {
			try {
				if (os != null)
					os.close();
			} 
			catch (IOException e) {//e.printStackTrace();
			}
			if (is != null) {
				try {
					is.close();
				} 
				catch (IOException e) {//e.printStackTrace();
				}
			}
		}
    }
    
    public static int getApplocationVersion(Context context){
    	PackageInfo pInfo;
		try {
			pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			int posible_nueva_version = pInfo.versionCode;
			
			return posible_nueva_version;
			
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return -1;
		}
    }
    
}
