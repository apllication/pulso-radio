package app.pulso.radio;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.*;
import android.os.*;
import androidx.core.content.ContextCompat;
public final class CallFlash {
 private static final Handler handler=new Handler(Looper.getMainLooper());
 private static CameraManager manager;private static String camera;private static boolean active=false,on=false;private static int generation=0;
 public static void blink(Context context){handler.post(()->{if(ContextCompat.checkSelfPermission(context,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED)return;stopNow();try{manager=(CameraManager)context.getSystemService(Context.CAMERA_SERVICE);camera=null;for(String id:manager.getCameraIdList()){CameraCharacteristics c=manager.getCameraCharacteristics(id);if(Boolean.TRUE.equals(c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE))){camera=id;break;}}if(camera==null)return;active=true;int token=++generation;for(int i=0;i<6;i++){final int pulse=i;handler.postDelayed(()->{if(token!=generation||!active)return;try{manager.setTorchMode(camera,pulse%2==0);on=pulse%2==0;if(pulse==5)active=false;}catch(Exception ignored){stopNow();}},i*500L);}}catch(Exception ignored){stopNow();}});}
 private static void stopNow(){generation++;if(manager!=null&&camera!=null&&on)try{manager.setTorchMode(camera,false);}catch(Exception ignored){}on=false;active=false;}
 public static void stop(){handler.post(CallFlash::stopNow);}
}
