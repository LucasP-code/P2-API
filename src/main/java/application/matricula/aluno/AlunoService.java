package application.matricula.aluno;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service    
public class AlunoService {
     @Autowired
    private AlunoRepository alunoRepo;

    public Iterable<AlunoDTO> getAll() {
        return alunoRepo.findAll().stream().map(AlunoDTO::new).toList();
    }

    public AlunoDTO insert(AlunoInsertDTO dados) {
        return new AlunoDTO(alunoRepo.save(new Aluno(dados)));
    }

    public AlunoDTO getOne(long id) {
        Optional<Aluno> resultado = alunoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrada"
            );
        }

        return new AlunoDTO(resultado.get());
    }

    public AlunoDTO update(long id, AlunoInsertDTO dadosAluno) {
        Optional<Aluno> resultado = alunoRepo.findById(id);

        if(resultado.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrada"
            );
        }

        resultado.get().setNome(dadosAluno.nome());
        resultado.get().setEmail(dadosAluno.email());
        resultado.get().setTelefone(dadosAluno.telefone());
        resultado.get().setDataMatricula(dadosAluno.dataMatricula());

        return new AlunoDTO(alunoRepo.save(resultado.get()));
    }

    public void delete(long id) {
        if(!alunoRepo.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Aluno não encontrada"
            );
        }
        alunoRepo.deleteById(id);
    }
}
