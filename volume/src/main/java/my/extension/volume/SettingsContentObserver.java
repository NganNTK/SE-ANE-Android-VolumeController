package my.extension.volume;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

/**
 * Created by NganNTK on 6/30/2017.
 */

public class SettingsContentObserver extends ContentObserver {
    private static final String TAG = "SettingsContentObserver";

    public SettingsContentObserver(Handler handler) {
        super(handler);
    }

    @Override
    public boolean deliverSelfNotifications() {
        return super.deliverSelfNotifications();
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Log.v(TAG, "Settings change detected");

        //Dispatch event to AIR
        VolumeExtension.notifyVolumeChange();
    }
}
