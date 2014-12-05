package muralufg.fabrica.inf.ufg.br.centralufg;

import android.app.Application;

/**
 * Created by eric on 04/12/14.
 */
public class App extends Application {
    private static App instance;
    public static App getContext() { return instance; }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
