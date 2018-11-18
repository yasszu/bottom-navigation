# Bottom Navigation
This application is example of how to use [BottomNavigationView](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html).
* This app is implemented in Kotlin
* Disable BottomNavigationView shift mode.  
![screenshot](docs/screenshot.png)

## Prerequisites
* Android SDK 28
* Android Build Tool 28.0.3
* Android Support Repository 28.0.0
* Android Plugin for Gradle 3.2.1
* Kotlin gradle plugin 1.2.51

## Getting Started
Build with Android Studio 3.2.1

### ProGuard
You need to configure proguard-rules.pro file if you want use ProGuard. Such as below:   

```
-keepclassmembers class android.support.design.internal.BottomNavigationMenuView { 
    boolean mShiftingMode; 
}
```