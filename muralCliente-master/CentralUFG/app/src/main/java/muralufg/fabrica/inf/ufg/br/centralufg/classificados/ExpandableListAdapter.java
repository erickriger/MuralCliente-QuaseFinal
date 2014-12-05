package muralufg.fabrica.inf.ufg.br.centralufg.classificados;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import muralufg.fabrica.inf.ufg.br.centralufg.R;

/**
 * Created by eric on 17/10/14.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Activity activity;
    private Context context;
    private List<CabecalhoClassificado> listaCabecalhosClassificados = new ArrayList<CabecalhoClassificado>();
    private LayoutInflater inflater;
    private static final int QUANTIDADE_DE_CORPOS_POR_CABECALHO = 1;
    private ImageLoader imageLoader;

    public ExpandableListAdapter(Context context) {
        super();

        imageLoader = new ImageLoader(VolleySingleton.getInstance().getRequestQueue(), new BitmapLruCache());

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void separarClassificados(List<Classificado> listaClassificados) {
        for (Classificado classificado : listaClassificados) {
            CabecalhoClassificado cabecalho = new CabecalhoClassificado(classificado);
            CorpoClassificado corpo = new CorpoClassificado(classificado.getDescricao(), classificado.getImagemUrl(), classificado.getEmail());
            cabecalho.setCorpoClassificado(corpo);
            listaCabecalhosClassificados.add(cabecalho);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return listaCabecalhosClassificados.size();
    }

    @Override
    public int getChildrenCount(int posicaoCabecalho) {
        return QUANTIDADE_DE_CORPOS_POR_CABECALHO;
    }

    @Override
    public Object getGroup(int posicaoCabecalho) {
        return listaCabecalhosClassificados.get(posicaoCabecalho);
    }

    @Override
    public Object getChild(int posicaoCabecalho, int posicaoCorpo)
    {
        return listaCabecalhosClassificados.get(posicaoCabecalho).getCorpoClassificado();
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int posicaoCabecalho, int posicaoCorpo) {
        return posicaoCorpo;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int posicaoCabecalho, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.cabecalho_classificado, null);
        }

        CabecalhoClassificado cabecalho = (CabecalhoClassificado) getGroup(posicaoCabecalho);

        NetworkImageView imagem = (NetworkImageView) convertView.findViewById(R.id.cabecalho_imagem_classificado);
        TextView titulo = (TextView) convertView.findViewById(R.id.cabecalho_titulo_classificado);
        TextView autor = (TextView) convertView.findViewById(R.id.cabecalho_autor_classificado);
        TextView dataCriacao = (TextView) convertView.findViewById(R.id.cabecalho_data_classificado);

        titulo.setText(cabecalho.getClassificado().getTitulo());
        autor.setText(cabecalho.getClassificado().getAutor());
        imagem.setImageUrl(cabecalho.getClassificado().getImagemUrl(), imageLoader);
        dataCriacao.setText(cabecalho.getClassificado().getDataCriacao());

        return convertView;
    }

    @Override
    public View getChildView(int posicaoCabecalho, int posicaoCorpo, boolean isUltimoItemCorpo, View convertView, ViewGroup parent) {
        final CorpoClassificado corpo = (CorpoClassificado) getChild(posicaoCabecalho, posicaoCorpo);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.corpo_classificado, null);
        }

        TextView descricao = (TextView) convertView.findViewById(R.id.corpo_descricao_classificado);
        NetworkImageView imagem = (NetworkImageView) convertView.findViewById(R.id.corpo_imagem_classificado);
        TextView email = (TextView) convertView.findViewById(R.id.corpo_email_classificado);

        descricao.setText(corpo.getDescricao());
        imagem.setImageUrl(corpo.getImagemUrl(), imageLoader);
        email.setText(corpo.getEmail());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}