package my.extension.volume;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;

import static com.adobe.fre.FREObject.newObject;

class GetCurrentVolume implements com.adobe.fre.FREFunction {
    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {
        Context appContext = freContext.getActivity().getApplicationContext();
        AudioManager audioManager = (AudioManager) appContext.getSystemService(Context.AUDIO_SERVICE);
        Float systemVolume = Float.valueOf(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

        try {
            Log.e("CurentVolume", "not null");
            return newObject(systemVolume);
        } catch (FREWrongThreadException e) {
            e.printStackTrace();
            Log.e("CurentVolume", "null");
            return null;
        }
    }
}
