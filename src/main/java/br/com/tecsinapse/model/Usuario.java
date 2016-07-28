package br.com.tecsinapse.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.tecsinapse.model.enums.Role;
import br.com.tecsinapse.util.Constantes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDateTime;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
@NamedQueries({
        @NamedQuery(name = "Usuario.findByEmailAndAtivo", query = "SELECT DISTINCT u FROM Usuario u WHERE u.email = :email AND u.ativo = true ORDER BY u.email"),
        @NamedQuery(name = "Usuario.findByChurch", query = "SELECT DISTINCT u FROM Usuario u WHERE u.igreja = :igreja AND u.ativo = true ORDER BY u.email")
})
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties({"igreja", "dataHoraCriacao", "senha", "tokenNovaSenha", "dataExpiracaoTokenNovaSenha", "ativo", "senhaConfirmacao"})
//insert into usuario(id, role, nome, email, senha,data_hora_criacao, igreja_pk,ativo) values(nextval('usuario_id_seq'), 'ADMINISTRADOR','LIP', 'lip@tecsinapse.com.br', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','2014-08-12 14:55:38.288', 1, true);
public class Usuario implements Model<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;


    @Column(name = "imagem_profile", columnDefinition="text")
    private String profileImage = Constantes.DEFAULT_PROFILE_IMAGE;


    @NotNull
    @Column(name = "data_hora_criacao")
    private Date dataHoraCriacao;

    @NotNull
    @Column(length = 1000)
    @Size(min = 1, max = 1000)
    private String nome;

    @Column(length = 2000)
    @Size(max = 2000)
    private String descricao;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "igreja_pk")
    private Igreja igreja;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Video> videos;

    @NotNull
    @NotBlank
    @org.hibernate.validator.constraints.Email
    @Column(length = 1000)
    private String email;

    @Column(length = 100)
    @Size(max = 100)
    private String telefone;

    @Column(length = 500)
    @Size(max = 500)
    private String instagram;

    @Column(length = 500)
    @Size(max = 500)
    private String facebook;

    @NotNull
    @Column(length = 1000)
    private String senha;

    @Column(name = "token_nova_senha", length = 128)
    private String tokenNovaSenha;

    @Column(name = "data_expiracao_token_nova_senha")
    private Date dataExpiracaoTokenNovaSenha;

    @NotNull
    @Column
    private boolean ativo = true;

    @Transient
    private String senhaConfirmacao;

    public Usuario() {
        dataHoraCriacao = LocalDateTime.now().toDate();
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) obj;
        if (id != null) {
            if (!id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public String getProfileImage() {
        if (profileImage == null)
            return Constantes.DEFAULT_PROFILE_IMAGE;
        else
            return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(Date dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public String getTokenNovaSenha() {
        return tokenNovaSenha;
    }

    public void setTokenNovaSenha(String tokenNovaSenha) {
        this.tokenNovaSenha = tokenNovaSenha;
    }

    public Date getDataExpiracaoTokenNovaSenha() {
        return dataExpiracaoTokenNovaSenha;
    }

    public void setDataExpiracaoTokenNovaSenha(Date dataExpiracaoTokenNovaSenha) {
        this.dataExpiracaoTokenNovaSenha = dataExpiracaoTokenNovaSenha;
    }

    public String getSenhaConfirmacao() {
        return senhaConfirmacao;
    }

    public void setSenhaConfirmacao(String senhaConfirmacao) {
        this.senhaConfirmacao = senhaConfirmacao;
    }

    public String getAtivoString() {
        return ativo ? "SIM" : "NÃO";
    }

    public Igreja getIgreja() {
        return igreja;
    }

    public void setIgreja(Igreja igreja) {
        this.igreja = igreja;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}