package application.matricula;

import application.matricula.aluno.AlunoDTO;
import application.matricula.curso.CursoDTO;

public record MatriculaDTO(long id, AlunoDTO aluno, CursoDTO curso){
    MatriculaDTO(Matricula dados) {
        this(dados.getId(), 
        new AlunoDTO(dados.getAluno()), 
        new CursoDTO(dados.getCurso()));
    }
}
