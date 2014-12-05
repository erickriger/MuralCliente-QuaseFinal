package muralufg.fabrica.inf.ufg.br.centralufg.classificados.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import muralufg.fabrica.inf.ufg.br.centralufg.R;
import muralufg.fabrica.inf.ufg.br.centralufg.classificados.Classificado;
import muralufg.fabrica.inf.ufg.br.centralufg.classificados.ExpandableListAdapter;
import muralufg.fabrica.inf.ufg.br.centralufg.classificados.VolleyApplication;
import muralufg.fabrica.inf.ufg.br.centralufg.classificados.VolleySingleton;

/**
 * Created by eric on 04/12/14.
 */
public class ClassificadosFragment extends Fragment {
    ExpandableListView expandableListView;
    ExpandableListAdapter mAdapter;
    private SwipeRefreshLayout swipeLayout;
    private static String URL_SERVIDOR_UFG = "http://invisiblerails.com/web/tacs/jsonClassificados.json";

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_classificados, container, false);
        //View rootView = inflater.inflate(R.layout.activity_principal_swipe, container, false);
        //View rootView = inflater.inflate(R.layout.fragment_classificados, container, false);
        //return rootView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        fetch();

        mAdapter = new ExpandableListAdapter(getActivity());
        swipeLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_container);
        expandableListView = (ExpandableListView) getView().findViewById(R.id.expandableListView);
        expandableListView.setAdapter(mAdapter);

        /* Serve para só acionar o refresh se o primeiro item da lista estiver totalmente visível */
        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0)
                    swipeLayout.setEnabled(true);
                else
                    swipeLayout.setEnabled(false);
            }
        });

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        fetch();
                        swipeLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });
    }

    private void fetch() {
        JsonObjectRequest request = new JsonObjectRequest(
                URL_SERVIDOR_UFG,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {

                            List<Classificado> classificados = parse(jsonObject);
                            mAdapter.separarClassificados(classificados);
                            //mAdapter.swapImageRecords(classificados);
                        }
                        catch(JSONException e) {
                            Crouton.makeText(getActivity(), getResources().getString(R.string.erro_obter_json),
                                    Style.ALERT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Crouton.makeText(getActivity(), getResources().getString(R.string.erro_obter_json),
                                Style.ALERT).show();
                    }
                });

        VolleySingleton.getInstance().getRequestQueue().add(request);
    }

    private List<Classificado> parse(JSONObject json) throws JSONException {
        ArrayList<Classificado> records = new ArrayList<Classificado>();

        JSONArray jsonListaClassificados = json.getJSONArray("ListaClassificados");

        for(int i =0; i < jsonListaClassificados.length(); i++) {
            JSONObject jsonClassificado = jsonListaClassificados.getJSONObject(i);
            int id = jsonClassificado.getInt("id");
            String imagemUrl = jsonClassificado.getString("imagem");
            String titulo = jsonClassificado.getString("titulo");
            String autor = jsonClassificado.getString("autor");
            String data = jsonClassificado.getString("data");
            String descricao = jsonClassificado.getString("descricao");
            String email = jsonClassificado.getString("email");

            Classificado classificado = new Classificado(titulo, autor, data, descricao, imagemUrl, email);

            records.add(classificado);
        }

        return records;
    }


}
