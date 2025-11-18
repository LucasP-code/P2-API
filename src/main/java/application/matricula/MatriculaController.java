package application.matricula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    public MatriculaDTO insert(@RequestBody MatriculaInsertDTO dados) {
        return matriculaService.insert(dados);
    }

    @PutMapping("/{id}")
    public MatriculaDTO update(@PathVariable long id, @RequestBody MatriculaInsertDTO dados) {
        return matriculaService.update(id, dados);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        matriculaService.delete(id);
    }

    @GetMapping
    public Iterable<MatriculaDTO> getAll() {
        return matriculaService.getAll();
    }
}
