package com.yimq.disguiser.hook;


import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by yimq on 16-1-4.
 */
public class Init implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lPParam) throws Throwable {
        LocationManager.init(lPParam);
        ContactManager.init(lPParam);
        DeviceManager.init(lPParam);
    }
}
