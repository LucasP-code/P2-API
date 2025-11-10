package application.curso;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cursos")
@Getter
@Setter
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_curso", nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private LocalDate dataMatricula;

    public Curso(String nome, String email, String telefone, LocalDate dataMatricula) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataMatricula = dataMatricula;
    }

    public Curso(CursoDTO dados) {
        this.id = dados.id();
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dataMatricula = dados.dataMatricula();
    }

    public Curso(CursoInsertDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dataMatricula = dados.dataMatricula();
    }
}
