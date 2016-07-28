package br.com.tecsinapse.model;

import br.com.tecsinapse.util.Constantes;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by renancastro on 11/07/16.
 */
@Entity
@Table(name = "igreja")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Igreja implements Model<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 1000)
    private String nome;

    @Column(name = "imagem_profile", columnDefinition="text")
    private String profileImage = Constantes.DEFAULT_PROFILE_IMAGE;

    @Column(length = 100)
    @Size(max = 100)
    private String telefone;

    @OneToMany
    List<Evento> eventos;

    @Column(length = 500)
    @Size(max = 500)
    private String instagram;

    @org.hibernate.validator.constraints.Email
    @Column(length = 1000)
    private String email;

    @Column(length = 500)
    @Size(max = 500)
    private String facebook;

    @Column(length = 1000)
    @Size(max = 1000)
    private String endereco;

    @Override
    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getProfileImage() {
        if (profileImage == null){
            return Constantes.DEFAULT_PROFILE_IMAGE;
        }
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

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
