package br.com.tecsinapse.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tag")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name = "Tag.findPorTitulo", query
        = "select d from Tag d where d.titulo LIKE :titulo")
public class Tag implements Model<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy="tags")
    private Set<Post> postsByTag = new HashSet<>();


    @NotNull
    @Column(length = 1000, unique=true)
    @Size(min = 1, max = 1000)
    private String titulo;

    public Tag() {

    }

    public Tag(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tag)) {
            return false;
        }
        final Tag other = (Tag) obj;

        return Objects.equals(this.titulo, other.titulo);
    }

    public Set<Post> getPostsByTag() {
        return postsByTag;
    }

    public void setPostsByTag(Set<Post> postsByTag) {
        this.postsByTag = postsByTag;
    }
}
