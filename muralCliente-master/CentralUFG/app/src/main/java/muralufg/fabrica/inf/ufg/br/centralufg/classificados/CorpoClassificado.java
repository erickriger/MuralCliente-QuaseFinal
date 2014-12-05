package muralufg.fabrica.inf.ufg.br.centralufg.classificados;

/**
 * Created by eric on 17/10/14.
 */
public class CorpoClassificado {
    private String descricao;
    private String imagemUrl;
    private String email;

    public CorpoClassificado(String descricao, String imagemUrl, String email) {
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
