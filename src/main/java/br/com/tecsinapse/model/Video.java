package br.com.tecsinapse.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "video")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Video implements Model<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_pk")
    private Usuario usuario;

    @NotBlank
    @Column(name = "url_video", length=1000)
    private String url;

    @NotBlank
    @Column(name = "nome_video", length=1000)
    private String nome;

    @NotBlank
    @Column(name = "thumbnail", length=1000)
    private String thumbnail = "https://placeholdit.imgix.net/~text?txtsize=33&txt=Pr%C3%A9via&w=350&h=250";


    @Column(name = "descricao", columnDefinition="text")
    private String descricao;


    public Video() {
    }

    public Video(Usuario user, String urlVideo, String nomeVideo) {
        this.usuario = user;
        this.url = urlVideo;
        this.nome = nomeVideo;
    }


    @Override
    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, url, nome);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Video)) {
            return false;
        }
        final Video other = (Video) obj;

        return Objects.equals(this.usuario, other.usuario)
                && Objects.equals(this.url, other.url)
                && Objects.equals(this.nome, other.nome);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
