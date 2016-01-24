package com.yimq.disguiser.hook;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import java.util.Collections;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by yimq on 16-1-5.
 */
public class PackageManager {
    public static void init(XC_LoadPackage.LoadPackageParam lPParam) {
        XposedHelpers.findAndHookMethod("android.content.pm.PackageManager", lPParam.classLoader, "getInstalledPackages",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(motify((List<PackageInfo>) param.getResult()));
                    }
                });

        XposedHelpers.findAndHookMethod("android.content.pm.PackageManager", lPParam.classLoader, "getInstalledApplications",
                int.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        param.setResult(motify((List<ApplicationInfo>) param.getResult()));
                    }
                });
    }

    private static <T> List<T> motify(List<T> result) {
        if (result == null) {
            return null;
        }
        return Collections.emptyList();
    }

}
