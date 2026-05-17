# =============================================================================
# ProGuard / R8 Rules — Aplikasi Penghitung Gaji
# Tujuan: Obfuscate + Optimize + Shrink untuk APK release yang aman
# =============================================================================

# ── 1. OPTIMASI AGRESIF ──────────────────────────────────────────────────────
# Izinkan R8 melakukan optimasi kode tingkat lanjut
-optimizationpasses 5
-allowaccessmodification
-dontskipnonpubliclibraryclassmembers

# ── 2. OBFUSCATION ───────────────────────────────────────────────────────────
# Ganti semua nama class, method, field dengan karakter acak (a, b, c, ...)
-obfuscationdictionary           proguard-dict.txt
-classobfuscationdictionary      proguard-dict.txt
-packageobfuscationdictionary    proguard-dict.txt

# Hapus informasi debug (buat reverse engineering jauh lebih sulit)
-renamesourcefileattribute SourceFile

# Jangan simpan nama file sumber / nomor baris di APK release
# (comment baris ini jika ingin crash report yang readable)
# -keepattributes SourceFile,LineNumberTable

# Hapus semua log di release (tidak ada yang bisa baca log apl kamu)
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

# ── 3. ANDROID FRAMEWORK — WAJIB KEEP ───────────────────────────────────────
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

# ── 4. ANDROIDX / APPCOMPAT ──────────────────────────────────────────────────
-keep class androidx.** { *; }
-keep interface androidx.** { *; }
-dontwarn androidx.**

# Fragment (wajib agar fragment tidak crash karena nama di-obfuscate)
-keep public class * extends androidx.fragment.app.Fragment
-keepclassmembers class * extends androidx.fragment.app.Fragment {
    public <init>(...);
}

# ── 5. MATERIAL DESIGN COMPONENTS ────────────────────────────────────────────
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

# BottomNavigationView — jangan obfuscate agar listener tidak crash
-keepclassmembers class com.google.android.material.bottomnavigation.BottomNavigationView {
    *;
}

# ── 6. VIEW / LAYOUT ─────────────────────────────────────────────────────────
# Semua class yang dipakai di XML layout harus keep
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# onClick di XML: android:onClick="methodName"
-keepclassmembers class * extends android.content.Context {
    public void *(android.view.View);
}

# ── 7. PARCELABLE ────────────────────────────────────────────────────────────
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
}

# ── 8. SERIALIZABLE ──────────────────────────────────────────────────────────
-keepclassmembers class * implements java.io.Serializable {
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# ── 9. ENUM ──────────────────────────────────────────────────────────────────
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# ── 10. ANNOTATIONS ──────────────────────────────────────────────────────────
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions

# ── 11. NATIVE METHODS ───────────────────────────────────────────────────────
-keepclasseswithmembernames class * {
    native <methods>;
}

# ── 12. APLIKASI KITA SENDIRI ────────────────────────────────────────────────
# Keep nama Activity, Fragment, dan class utama aplikasi agar AndroidManifest tetap valid
-keep class com.yoga.aplikasipenghitunggaji.MainActivity { *; }
-keep class com.yoga.aplikasipenghitunggaji.LoginActivity { *; }
-keep class com.yoga.aplikasipenghitunggaji.SplashActivity { *; }
-keep class com.yoga.aplikasipenghitunggaji.fragment.** { *; }

# ── 13. CARDVIEW ─────────────────────────────────────────────────────────────
-keep class androidx.cardview.** { *; }
-dontwarn androidx.cardview.**

# ── 14. SUPPRESS COMMON WARNINGS ─────────────────────────────────────────────
-dontwarn java.lang.invoke.*
-dontwarn **$$Lambda$*
-dontwarn kotlin.**