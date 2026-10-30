package app.pulso.radio;
import android.os.Bundle;
import android.view.KeyEvent;
import com.getcapacitor.BridgeActivity;
public class MainActivity extends BridgeActivity {
 public void applyFullscreen(boolean enabled){androidx.core.view.WindowInsetsControllerCompat controller=new androidx.core.view.WindowInsetsControllerCompat(getWindow(),getWindow().getDecorView());controller.setSystemBarsBehavior(androidx.core.view.WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);if(enabled)controller.hide(androidx.core.view.WindowInsetsCompat.Type.systemBars());else controller.show(androidx.core.view.WindowInsetsCompat.Type.systemBars());}
 private boolean radioKeyHeld=false;
 @Override public void onCreate(Bundle state){registerPlugin(PulsoRadioPlugin.class);super.onCreate(state);setVolumeControlStream(android.media.AudioManager.STREAM_MUSIC);}
 private void radioKey(boolean down){if(bridge!=null&&bridge.getWebView()!=null)bridge.getWebView().post(()->bridge.getWebView().evaluateJavascript("window.PulsoHardwarePTT&&window.PulsoHardwarePTT("+(down?"true":"false")+")",null));}
 @Override public boolean dispatchKeyEvent(KeyEvent event){String selected=getSharedPreferences("pulso_native",MODE_PRIVATE).getString("hardwareButton","off");int key=event.getKeyCode();boolean matches=(selected.equals("volume-up")&&key==KeyEvent.KEYCODE_VOLUME_UP)||(selected.equals("volume-down")&&key==KeyEvent.KEYCODE_VOLUME_DOWN);if(matches&&RadioService.appVisible){if(event.getAction()==KeyEvent.ACTION_DOWN){if(event.getRepeatCount()==0){radioKeyHeld=true;radioKey(true);}return true;}if(event.getAction()==KeyEvent.ACTION_UP){radioKeyHeld=false;radioKey(false);return true;}}return super.dispatchKeyEvent(event);}
 @Override public void onWindowFocusChanged(boolean hasFocus){super.onWindowFocusChanged(hasFocus);if(!hasFocus&&radioKeyHeld){radioKeyHeld=false;radioKey(false);}}
 @Override public void onResume(){RadioService.appVisible=true;super.onResume();getWindow().getDecorView().post(()->applyFullscreen(getSharedPreferences("pulso_native",MODE_PRIVATE).getBoolean("fullscreen",true)));}
 @Override public void onPause(){radioKeyHeld=false;radioKey(false);RadioService.appVisible=false;super.onPause();}
}
