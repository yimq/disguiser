package com.yimq.disguiser.hook;

import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by yimq on 16-2-5.
 */
public class DeviceManager {

    public static void init(XC_LoadPackage.LoadPackageParam lPParam) {

        Class<?> clz = android.os.Build.class;
        XposedHelpers.setStaticObjectField(clz, "BRAND", "hello 过年好");
        XposedHelpers.setStaticObjectField(clz, "DISPLAY", "hello 过年好");
        XposedHelpers.setStaticObjectField(clz, "MODEL", "hello 过年好");
        XposedHelpers.setStaticObjectField(clz, "MANUFACTURER", "hello 过年好");

    }

}
