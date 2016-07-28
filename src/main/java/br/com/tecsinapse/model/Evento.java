package br.com.tecsinapse.model;

import br.com.tecsinapse.model.enums.Recorrencia;
import br.com.tecsinapse.util.Constantes;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by renancastro on 11/07/16.
 */
@Entity
@Table(name = "evento")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Evento implements Model<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 1000)
    private String nome;

    @Column(length = 2000)
    private String onlineUrl;

    @Column
    @Enumerated(EnumType.STRING)
    private Recorrencia recorrencia;

    @NotNull
    @ManyToOne
    private Igreja igreja;


    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column(name = "image", columnDefinition="text")
    private String foto = Constantes.DEFAULT_PROFILE_IMAGE;

    @Column(columnDefinition="text")
    private String descricao;


    @Column(length = 1000)
    @Size(max = 1000)
    private String local;

    @Column(length = 1000)
    @Size(max = 1000)
    private String endereco;

    @Override
    public Long getId() {
        return id;
    }


    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOnlineUrl() {
        return onlineUrl;
    }

    public void setOnlineUrl(String onlineUrl) {
        this.onlineUrl = onlineUrl;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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

    public Recorrencia getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(Recorrencia recorrencia) {
        this.recorrencia = recorrencia;
    }
}
