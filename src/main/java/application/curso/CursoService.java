package application.curso;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service    
public class CursoService {
     @Autowired
    private CursoRepository cursoRepo;

    public Iterable<CursoDTO> getAll() {
        return cursoRepo.findAll().stream().map(CursoDTO::new).toList();
    }

    public CursoDTO insert(CursoInsertDTO dados) {
        return new CursoDTO(cursoRepo.save(new Curso(dados)));
    }

    public CursoDTO getOne(long id) {
        Optional<Curso> resultado = cursoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrada"
            );
        }

        return new CursoDTO(resultado.get());
    }

    public CursoDTO update(long id, CursoInsertDTO dadosCurso) {
        Optional<Curso> resultado = cursoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrada"
            );
        }

        resultado.get().setNome(dadosCurso.nome());
        resultado.get().setEmail(dadosCurso.email());
        resultado.get().setTelefone(dadosCurso.telefone());
        resultado.get().setDataMatricula(dadosCurso.dataMatricula());

        return new CursoDTO(cursoRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!cursoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Curso não encontrada"
            );
        }
        cursoRepo.deleteById(id);
    }
}
