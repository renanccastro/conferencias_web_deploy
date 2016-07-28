package br.com.tecsinapse.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "mensagem")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Mensagem implements Model<Long> {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario criador;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "igreja_pk")
    private Igreja igreja;

    @Column(length = 2000)
    @Size(max = 2000)
    private String titulo;

    @Column(length = 2000)
    @Size(max = 2000)
    private String mensagem;

    @NotNull
    @Column(name = "data_hora_criacao")
    private Date dataHoraCriacao = new Date();

    @NotNull
    @Column(name = "data_hora_envio")
    private Date dataHoraEnvio;

    @Column
    private Boolean enviado = false;


    public Mensagem() {
    }

    public Mensagem(Usuario criador, Igreja igreja, String titulo, String mensagem) {
        this.criador = criador;
        this.igreja = igreja;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Date getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    public void setDataHoraEnvio(Date dataHoraEnvio) {
        this.dataHoraEnvio = dataHoraEnvio;
    }

    public Boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(Boolean enviado) {
        this.enviado = enviado;
    }
}
