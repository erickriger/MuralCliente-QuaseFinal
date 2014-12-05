package muralufg.fabrica.inf.ufg.br.centralufg.classificados;

/**
 * Created by eric on 17/10/14.
 */
public class Classificado {
    private String titulo;
    private String autor;
    private String dataCriacao;
    private String descricao;
    private String imagemUrl;
    private String email;

    public Classificado(String titulo, String autor, String dataCriacao, String descricao, String imagemUrl, String email) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.imagemUrl = imagemUrl;
        this.email = email;
    }

    public Classificado() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
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
