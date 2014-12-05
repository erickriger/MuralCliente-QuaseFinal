package muralufg.fabrica.inf.ufg.br.centralufg.classificados;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 17/10/14.
 */
public class CabecalhoClassificado {
    private Classificado classificado;
    private CorpoClassificado corpoClassificado;

    public CabecalhoClassificado(Classificado classificado) {
        this.classificado = classificado;
    }

    public Classificado getClassificado() {
        return classificado;
    }

    public void setClassificado(Classificado classificado) {
        this.classificado = classificado;
    }

    public CorpoClassificado getCorpoClassificado() {
        return corpoClassificado;
    }

    public void setCorpoClassificado(CorpoClassificado corpoClassificado) {
        this.corpoClassificado = corpoClassificado;
    }
}