package br.com.tecsinapse.model;

import br.com.tecsinapse.util.Constantes;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ministerio")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ministerio implements Model<Long> {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 200, unique=true)
    @Size(min = 1, max = 200)
    private String nome;

    @Column(name = "imagem", columnDefinition="text")
    private String imagem = "https://placeholdit.imgix.net/~text?txtsize=33&txt=Pr%C3%A9via&w=350&h=250";

    @Column(length = 2000)
    @Size(max = 2000)
    private String descricao;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "igreja_pk")
    private Igreja igreja;

    @ManyToOne
    private Usuario lider;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    public Usuario getLider() {
        return lider;
    }

    public void setLider(Usuario lider) {
        this.lider = lider;
    }
}
