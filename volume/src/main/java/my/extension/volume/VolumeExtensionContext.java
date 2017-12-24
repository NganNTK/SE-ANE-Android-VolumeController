package my.extension.volume;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

import java.util.HashMap;
import java.util.Map;

public class VolumeExtensionContext extends FREContext {
    private static final String TAG = "VolumeExtensionContext";

    @Override
    public Map<String, FREFunction> getFunctions() {
        Map<String, FREFunction> functions = new HashMap<>();
        functions.put("init", new InitFunction());
        functions.put("setVolume", new SetVolumeFunction());
        functions.put("getCurrentVolume", new GetCurrentVolume());
        return functions;
    }

    @Override
    public void dispose() {
        Log.v(TAG, "Extension disposed");
    }
}
