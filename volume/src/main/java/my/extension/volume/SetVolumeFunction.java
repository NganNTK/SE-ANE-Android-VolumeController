package my.extension.volume;

import android.content.Context;
import android.media.AudioManager;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * Created by NganNTK on 6/30/2017.
 */

class SetVolumeFunction implements FREFunction {
    private static final String TAG = "SetVolumeFunction";

    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {
        //connect context and get audioManager
        Context appContext = freContext.getActivity().getApplicationContext();
        AudioManager audioManager = (AudioManager) appContext.getSystemService(Context.AUDIO_SERVICE);

        //get newVolume
        double volume = 1;
        try {
            volume = freObjects[0].getAsDouble();
        } catch (Exception e) {

        }

        //get change volume
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = volume * maxVolume;
        int index = (int) volume;

        //set volume
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);

        return null;
    }
}
