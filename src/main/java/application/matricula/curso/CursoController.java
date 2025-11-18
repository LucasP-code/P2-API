package application.matricula.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "Operações relacionadas a cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Operation(summary = "Cria um novo curso",
        description = "Adiciona um novo curso ao sistema com os dados fornecidos.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public CursoDTO insert(@RequestBody CursoInsertDTO novoCurso) {
        return cursoService.insert(novoCurso);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um curso pelo ID", description = "Retorna os dados de um curso específico com base no ID fornecido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public CursoDTO getOne(
        @Parameter(description = "ID do curso a ser obtido")
        @PathVariable long id) {
        return cursoService.getOne(id);
    }

    @GetMapping
    @Operation(summary = "Lista todos os cursos",
        description = "Retorna uma lista com todos os cursos registrados.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public Iterable<CursoDTO> getAll() {
        return cursoService.getAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza curso existente",
        description = "Altera os dados de um curso já cadastrado, identificado pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public CursoDTO update(
        @Parameter(description = "ID do curso a ser atualizado")
        @PathVariable long id, @RequestBody CursoInsertDTO novosDados) {
        return cursoService.update(id, novosDados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove um curso",
        description = "Exclui permanentemente um curso com base no ID informado." )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Curso removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Curso não encontrado")
    })
    public void remove(
        @Parameter(description = "ID do curso a ser removido")
        @PathVariable long id) {
        cursoService.delete(id);
    }
}