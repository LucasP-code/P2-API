package application.matricula.aluno;

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
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "id_aluno", nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private LocalDate dataMatricula;

    public Aluno(String nome, String email, String telefone, LocalDate dataMatricula) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataMatricula = dataMatricula;
    }

    public Aluno(AlunoDTO dados) {
        this.id = dados.id();
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dataMatricula = dados.dataMatricula();
    }

    public Aluno(AlunoInsertDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.dataMatricula = dados.dataMatricula();
    }
}
