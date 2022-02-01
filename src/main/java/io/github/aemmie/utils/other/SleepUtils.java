package io.github.aemmie.utils.other;

public class SleepUtils {
    public static void sleep(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException ignored) {
        }
    }
}
