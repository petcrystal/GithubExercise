package com.zlm.project.data;

import android.content.Context;

import com.zlm.project.connect.ApiClientImp;
import com.zlm.project.data.local.PreferenceImp;
import com.zlm.project.other.uuid.DeviceUUIDImp;

/**
 * @author Milla
 * @create 2019/3/29
 */
public interface DataManagerImp extends PreferenceImp, DeviceUUIDImp,ApiClientImp  {

    Context getContext();
}
