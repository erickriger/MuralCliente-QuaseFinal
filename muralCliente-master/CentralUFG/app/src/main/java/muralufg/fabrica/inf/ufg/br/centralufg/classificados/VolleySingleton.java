package muralufg.fabrica.inf.ufg.br.centralufg.classificados;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import muralufg.fabrica.inf.ufg.br.centralufg.App;

/**
 * Created by eric on 04/12/14.
 */
public class VolleySingleton {
    private static VolleySingleton mInstance = null;
    private RequestQueue mRequestQueue;

    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(App.getContext());
        mInstance = this;
    }

    public static synchronized VolleySingleton getInstance(){
        if(mInstance == null){
            mInstance = new VolleySingleton();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
