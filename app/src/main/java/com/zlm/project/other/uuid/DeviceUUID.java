package com.zlm.project.other.uuid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DeviceUUID implements DeviceUUIDImp {

    // -------------------------------------------
    private Context context;

    // -------------------------------------------
    @Inject
    public DeviceUUID(Context context) {
        this.context = context;
    }

    // -------------------------------------------

    /**
     * Get device uuid but non-unique.
     *
     * @return device token
     * @see UUID
     */
    @Override
    public UUID getUUID() {

        @SuppressLint("HardwareIds") final String androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        return UUID.nameUUIDFromBytes(androidId.getBytes(StandardCharsets.UTF_8));
    }

    // -------------------------------------------
}
