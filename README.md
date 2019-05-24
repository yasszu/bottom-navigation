# Bottom Navigation
This application is example of how to use [BottomNavigationView](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html).
* This app is implemented in Kotlin
* Disable BottomNavigationView shift mode.  
![screenshot](docs/screenshot.png)

## Prerequisites
* Android SDK 28
* Android Build Tool 28.0.3
* Androidx 1.0.0
* Android Plugin for Gradle 3.4.1
* Kotlin gradle plugin 1.3.21

## Getting Started
Build with Android Studio 3.4

## Disable shifting mode
### Since Support library 28

```xml
    <android.support.design.widget.BottomNavigationView
        ・・・
        app:labelVisibilityMode="labeled"
        ・・・
    />
```

### Under Support library 27

Use the extension of BottomNavigationView.

```java
BottomNavigationView.disableShiftMode()
```

You need to configure proguard-rules.pro file if you want use ProGuard. Such as below:   

```
-keepclassmembers class android.support.design.internal.BottomNavigationMenuView { 
    boolean mShiftingMode; 
}
```