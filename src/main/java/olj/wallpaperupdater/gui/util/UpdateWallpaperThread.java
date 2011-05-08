package olj.wallpaperupdater.gui.util;

/**
 * @author aestonpro17
 * @since 07.05.11
 */
public class UpdateWallpaperThread extends Thread {

    private boolean run;
    private long sleepMillis;
    private UpdateWallpaperListener listener;

    public UpdateWallpaperThread(long sleepSeconds, UpdateWallpaperListener listener) {
        this.sleepMillis = sleepSeconds * 1000;
        this.listener = listener;
    }

    @Override
    public void run() {
        super.run();

        run = true;

        while (run) {
            try {
                long startTime = System.currentTimeMillis();
                listener.updateWallpaper();
                long remainingMillis = sleepMillis - (System.currentTimeMillis() - startTime);

                sleep(remainingMillis);
            } catch (InterruptedException e) {
                run = false;
            }
        }
    }

    public void stopUpdateWallpaper() {
        run = false;
        interrupt();
    }
}
