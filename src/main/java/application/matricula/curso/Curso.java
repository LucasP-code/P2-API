package application.matricula.curso;

import java.time.LocalDateTime;

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
    private String descricao;
    @Column(nullable = false)
    private int cargaHoraria;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    public Curso(String nome, String descricao, int cargaHoraria, String status, LocalDateTime dataCriacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    public Curso(CursoDTO dados) {
        this.id = dados.id();
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.cargaHoraria = dados.cargaHoraria();
        this.status = dados.status();
        this.dataCriacao = dados.dataCriacao();
    }

    public Curso(CursoInsertDTO dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.cargaHoraria = dados.cargaHoraria();
        this.status = dados.status();
        this.dataCriacao = dados.dataCriacao();
    }
}
