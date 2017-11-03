package my.extension.volume;

import android.content.Context;
import android.os.Handler;
import android.provider.Settings;
import android.provider.Settings.System;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * Created by NganNTK on 6/30/2017.
 */

class InitFunction implements FREFunction {
    private static final String TAG = "InitFunction";

    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {
        //connect 2 context with VolumeExtension's context
        VolumeExtension.extensionContext = freContext;

        Context appContext = freContext.getActivity().getApplicationContext();
        VolumeExtension.appContext = appContext;

        //start watching settings for volume changes
        VolumeExtension.mSettingsWatcher = new SettingsContentObserver(new Handler());
        VolumeExtension.appContext.getContentResolver().registerContentObserver(System.CONTENT_URI, true, VolumeExtension.mSettingsWatcher);

        Log.i(TAG, "in init");

        //tell AIR
        VolumeExtension.notifyVolumeChange();

        return null;
    }
}
