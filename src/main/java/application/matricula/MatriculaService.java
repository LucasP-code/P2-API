package application.matricula;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import application.matricula.aluno.Aluno;
import application.matricula.aluno.AlunoRepository;
import application.matricula.curso.Curso;
import application.matricula.curso.CursoRepository;

@Service
public class MatriculaService {
    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    
    public Iterable<MatriculaDTO> getAll() {
        return matriculaRepository.findAll().stream().map(MatriculaDTO::new).toList();
    }

    public MatriculaDTO insert(MatriculaInsertDTO dados) {
        Optional<Aluno> resultadoAluno = alunoRepository.findById(dados.alunoId());
        Optional<Curso> resultadoCurso = cursoRepository.findById(dados.cursoId());
    
        String mensagem = "dados nao encontrados";
        boolean isError = false;

        if (resultadoAluno.isEmpty()) {
            if(isError)
                mensagem += ", ";
            mensagem += "aluno";
            isError = true;
        }

        if (resultadoCurso.isEmpty()) {
            if(isError)
                mensagem += ", ";
            mensagem += "curso";
            isError = true;
        }

        if (isError) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, mensagem
            );
        }

        Matricula novo = new Matricula();
        novo.setAluno(resultadoAluno.get());
        novo.setCurso(resultadoCurso.get());

        return new MatriculaDTO(matriculaRepository.save(novo));
    }

    public MatriculaDTO update(long id, MatriculaInsertDTO dados) {
        Optional<Matricula> resultadoMatricula = matriculaRepository.findById(id);
        if (resultadoMatricula.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Matricula nao encontrado"
            );
        }
        
        Optional<Aluno> resultadoAluno = alunoRepository.findById(dados.alunoId());
        Optional<Curso> resultadoCurso = cursoRepository.findById(dados.cursoId());

        String mensagem = "dados nao encontrados";
        boolean isError = false;

        if (resultadoAluno.isEmpty()) {
            if(isError)
                mensagem += ", ";
            mensagem += "aluno";
            isError = true;
        }

        if (resultadoCurso.isEmpty()) {
            if(isError)
                mensagem += ", ";
            mensagem += "curso";
            isError = true;
        }

        if (isError) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, mensagem
            );
        }

        resultadoMatricula.get().setAluno(resultadoAluno.get());
        resultadoMatricula.get().setCurso(resultadoCurso.get());

        return new MatriculaDTO(matriculaRepository.save(resultadoMatricula.get()));
    }

    public void delete(long id) {
        if (!matriculaRepository.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Matricula nao encontrado"
            );
        }
        matriculaRepository.deleteById(id);
    }   
}
