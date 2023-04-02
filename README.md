# Bottom Navigation
This application is example of how to use [BottomNavigationView](https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView).
* Implement `com.google.android.material.bottomnavigation.BottomNavigationView`
* This app is implemented in Kotlin
* Disable BottomNavigationView shift mode.  
![screenshot](docs/screenshot.png)

## Prerequisites
* Android SDK 33
* Android Build Tool 30.0.2
* Androidx 1.6.1
* Android Plugin for Gradle 7.4.2
* Kotlin gradle plugin 1.8.0

## Getting Started
Build with the Android Studio Electric Eel | 2022.1.1 Patch 2

## Disable shifting mode
### Since Support library 28

```xml
    <android.support.design.widget.BottomNavigationView
        ・・・
        app:labelVisibilityMode="labeled"
        ・・・
    />
```

### Support library 27 or lower ([target-sdk-27](https://github.com/yasszu/bottom-navigation/tree/target-sdk-27))

Use the extension of [BottomNavigationView](https://github.com/yasszu/bottom-navigation/blob/target-sdk-27/app/src/main/java/com/example/bottomnavigation/extension/BottomNavigationView.kt).

```java
bottomNavigationView.disableShiftMode()
```

You need to configure proguard-rules.pro file if you want use ProGuard. Such as below:   

```
-keepclassmembers class android.support.design.internal.BottomNavigationMenuView { 
    boolean mShiftingMode; 
}
```
