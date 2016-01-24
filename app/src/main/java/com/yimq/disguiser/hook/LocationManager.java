package com.yimq.disguiser.hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by yimq on 16-1-5.
 */
public class LocationManager {

    public static void init(XC_LoadPackage.LoadPackageParam lPParam) {
        XposedHelpers.findAndHookMethod("android.location.LocationManager.ListenerTransport", lPParam.classLoader, "onLocationChanged",
                android.location.Location.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                        param.args[0] = motify((android.location.Location) param.args[0]);
                    }
                }
        );

        XposedHelpers.findAndHookMethod("android.location.LocationManager", lPParam.classLoader, "getLastKnownLocation",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(motify((android.location.Location) param.getResult()));
                    }
                }
        );

    }

    private static Object motify(android.location.Location location) {

        if (location == null) {
            return null;
        }
        // TODO 这里新new了一个Location,因为如果直接改变原来的，会有副作用，具体尚未研究
        android.location.Location newLocation = new android.location.Location(location);
        newLocation.setLatitude(20);
        return  newLocation;
    }
}
