package com.yimq.disguiser.hook;

import android.content.pm.ApplicationInfo;
import android.os.Build;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by yimq on 16-1-5.
 */
public class PackageManager {

    public static void init(IXposedHookZygoteInit.StartupParam lPParam) throws ClassNotFoundException {

        if (Build.VERSION.SDK_INT <=15) {
            XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generateApplicationInfo",
                    Class.forName("android.content.pm.PackageParser.Package"), int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            ApplicationInfo info = (ApplicationInfo) param.getResult();
                            ApplicationInfo fakeInfo = new ApplicationInfo(info);
                            info.packageName = "com.tencent";
                            param.setResult(fakeInfo);
                        }
                    });
        }

        XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generateApplicationInfo",
                Class.forName("android.content.pm.PackageParser.Package"), int.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        ApplicationInfo info = (ApplicationInfo) param.getResult();
                        param.setResult(fakeApplicationInfo(info));
                    }
                });


    }

    private static ApplicationInfo fakeApplicationInfo(ApplicationInfo info) {
        ApplicationInfo fakeInfo = new ApplicationInfo(info);
        fakeInfo.packageName = "com.tencent";
        return fakeInfo;
    }

//    private static PackageInfo fakePackageInfo(PackageInfo info) {
//        fakeInfo.packageName = "com.tencent";
//        return fakeInfo;
//    }

}
