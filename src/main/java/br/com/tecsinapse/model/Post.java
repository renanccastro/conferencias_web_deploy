package br.com.tecsinapse.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "post")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Post implements Model<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_pk")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "igreja_pk")
    private Igreja igreja;


    @NotNull
    @Column(name = "data_hora_criacao")
    private Date dataHoraCriacao = LocalDateTime.now().toDate();

    @NotNull
    @Column(length = 1000)
    @Size(min = 1, max = 1000)
    private String titulo;

    @Column(name = "conteudo", columnDefinition="text")
    private String conteudo;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Tag> tags = new HashSet<>();

    @NotNull
    @Column
    private boolean publicado = false;

    public Post() {
    }

    public Post(Usuario usuario, String titulo, String conteudo, Set<Tag> tags) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.tags = tags;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }


    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public boolean addTag(Tag t){
        return this.tags.add(t) && t.getPostsByTag().add(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (publicado != post.publicado) return false;
        if (!usuario.equals(post.usuario)) return false;
        if (!dataHoraCriacao.equals(post.dataHoraCriacao)) return false;
        if (!titulo.equals(post.titulo)) return false;
        if (tags != null ? !tags.equals(post.tags) : post.tags != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = usuario.hashCode();
        result = 31 * result + dataHoraCriacao.hashCode();
        result = 31 * result + titulo.hashCode();
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (publicado ? 1 : 0);
        return result;
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    public void removeTag(Tag t){
        this.tags.remove(t);
        t.getPostsByTag().remove(this);
    }
}
