package my.extension.volume;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

/**
 * Created by NganNTK on 6/30/2017.
 */

public class VolumeExtension implements FREExtension {
    private static final String TAG = "VolumeExtension";

    public static FREContext extensionContext;
    public static Context appContext;
    public static SettingsContentObserver mSettingsWatcher;

    private static float NO_VALUE = (float) -1.0;
    private static Float lastSystemVolume = NO_VALUE;

    @Override
    public void initialize() {
        Log.v(TAG, "Extension initialized");
    }

    @Override
    public FREContext createContext(String s) {
        return null;
    }

    @Override
    public void dispose() {
        Log.v(TAG, "Extension disposed");

        //stop watching settings for volume changes
        VolumeExtension.appContext.getContentResolver().unregisterContentObserver(mSettingsWatcher);

        appContext = null;
        extensionContext = null;
        mSettingsWatcher = null;
        lastSystemVolume = NO_VALUE;
    }

    public static void notifyVolumeChange() {
        AudioManager audioManager = (AudioManager) appContext.getSystemService(Context.AUDIO_SERVICE);
        Float maxVolume = Float.valueOf(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        Float systemVolume = Float.valueOf(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        // Only dispatch the event if the volume actually changed.
        // The settings watcher is going to see *any* settings change,
        // so it's possible that we'll get in here but the volume hasn't
        // changed.  We shouldn't tell Flash if that's the case.
        if(systemVolume != lastSystemVolume) {
            systemVolume = lastSystemVolume;
            Float flashVolume = systemVolume/maxVolume;

            Log.i(TAG, "System volume:"+systemVolume);
            Log.i(TAG, "Adjusted volume:"+flashVolume);

            String volume = String.valueOf(flashVolume);
            String eventName = "volumeChanged";

            extensionContext.dispatchStatusEventAsync(eventName, volume);
        }
    }
}
