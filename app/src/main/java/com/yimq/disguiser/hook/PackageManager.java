package com.yimq.disguiser.hook;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Parcel;
import android.util.ArraySet;

import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by yimq on 16-1-5.
 */
public class PackageManager {

    public static void init(IXposedHookZygoteInit.StartupParam lPParam) throws ClassNotFoundException {

        if (Build.VERSION.SDK_INT <= 15) {

            XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generatePackageInfo",
                    Class.forName("android.content.pm.PackageParser.Package"), int[].class, int.class,
                    long.class, long.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            PackageInfo info = (PackageInfo) param.getResult();
                            param.setResult(fakePackageInfo(info));
                        }
                    });

            XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generateApplicationInfo",
                    Class.forName("android.content.pm.PackageParser.Package"), int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            ApplicationInfo info = (ApplicationInfo) param.getResult();
                            param.setResult(fakeApplicationInfo(info));
                        }
                    });

        } else if (Build.VERSION.SDK_INT == 16) {

            XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generatePackageInfo",
                    Class.forName("android.content.pm.PackageParser.Package"), int[].class, int.class,
                    long.class, long.class, HashSet.class, boolean.class, int.class, int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            PackageInfo info = (PackageInfo) param.getResult();
                            param.setResult(fakePackageInfo(info));
                        }
                    });

            XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generateApplicationInfo",
                    Class.forName("android.content.pm.PackageParser.Package"), int.class,
                    boolean.class, int.class, int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            ApplicationInfo info = (ApplicationInfo) param.getResult();
                            param.setResult(fakeApplicationInfo(info));
                        }
                    });

        } else if (Build.VERSION.SDK_INT <=21) {

            XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generatePackageInfo",
                    Class.forName("android.content.pm.PackageParser.Package"), int[].class, int.class,
                    long.class, long.class, HashSet.class, Class.forName("android.content.pm.PackageUserState"),
                    int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            PackageInfo info = (PackageInfo) param.getResult();
                            param.setResult(fakePackageInfo(info));
                        }
                    });

            XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generateApplicationInfo",
                    Class.forName("android.content.pm.PackageParser.Package"), int.class,
                    Class.forName("android.content.pm.PackageUserState"), int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            ApplicationInfo info = (ApplicationInfo) param.getResult();
                            param.setResult(fakeApplicationInfo(info));
                        }
                    });

        } else if (Build.VERSION.SDK_INT ==22) {

            XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generatePackageInfo",
                    Class.forName("android.content.pm.PackageParser.Package"), int[].class, int.class,
                    long.class, long.class, ArraySet.class, Class.forName("android.content.pm.PackageUserState"),
                    int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            PackageInfo info = (PackageInfo) param.getResult();
                            param.setResult(fakePackageInfo(info));
                        }
                    });

            XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generateApplicationInfo",
                    Class.forName("android.content.pm.PackageParser.Package"), int.class,
                    Class.forName("android.content.pm.PackageUserState"), int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            ApplicationInfo info = (ApplicationInfo) param.getResult();
                            param.setResult(fakeApplicationInfo(info));
                        }
                    });
        } else {

            XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generatePackageInfo",
                    Class.forName("android.content.pm.PackageParser.Package"), int[].class, int.class,
                    long.class, long.class, Set.class, Class.forName("android.content.pm.PackageUserState"),
                    int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            PackageInfo info = (PackageInfo) param.getResult();
                            param.setResult(fakePackageInfo(info));
                        }
                    });

            XposedHelpers.findAndHookMethod(Class.forName("android.content.pm.PackageParser"), "generateApplicationInfo",
                    Class.forName("android.content.pm.PackageParser.Package"), int.class,
                    Class.forName("android.content.pm.PackageUserState"), int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            ApplicationInfo info = (ApplicationInfo) param.getResult();
                            param.setResult(fakeApplicationInfo(info));
                        }
                    });
        }
    }

    private static ApplicationInfo fakeApplicationInfo(ApplicationInfo info) {
        ApplicationInfo fakeInfo = new ApplicationInfo(info);
        fakeInfo.packageName = "com.tencent";
        return fakeInfo;
    }

    private static PackageInfo fakePackageInfo(PackageInfo info) {
        Parcel parcel = Parcel.obtain();
        info.writeToParcel(parcel, 0);
        PackageInfo fakeInfo = PackageInfo.CREATOR.createFromParcel(parcel);
        fakeInfo.packageName = "com.tencent";
        return fakeInfo;
    }
}
