package application.matricula;

public record MatriculaInsertDTO(long alunoId, long cursoId) {
    public MatriculaInsertDTO(Matricula dados) {
        this(dados.getAluno().getId(), dados.getCurso().getId());
    }
}
