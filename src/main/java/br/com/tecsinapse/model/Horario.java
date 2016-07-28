package br.com.tecsinapse.model;

        import org.hibernate.annotations.Cache;
        import org.hibernate.annotations.CacheConcurrencyStrategy;
        import org.hibernate.validator.constraints.NotBlank;

        import javax.persistence.*;
        import javax.validation.constraints.NotNull;
        import javax.validation.constraints.Size;
        import java.util.Date;
        import java.util.Objects;

@Entity
@Table(name = "horario")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Horario implements Model<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Column(length = 1000, unique=true)
    @Size(min = 1, max = 1000)
    private String titulo;

    public Horario() {

    }

    public Horario(String titulo) {
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
        if (!(obj instanceof Horario)) {
            return false;
        }
        final Horario other = (Horario) obj;

        return Objects.equals(this.titulo, other.titulo);
    }
}
