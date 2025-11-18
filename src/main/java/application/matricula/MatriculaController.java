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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/matriculas")
@Tag(name = "Matrículas", description = "Operações relacionadas a matrículas")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;

    @PostMapping
    @Operation(summary = "Cria uma nova matrícula",
        description = "Adiciona uma nova matrícula ao sistema com os dados fornecidos.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matrícula criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public MatriculaDTO insert(
        @Parameter(description = "Dados da nova matrícula")
        @RequestBody MatriculaInsertDTO dados) {
        return matriculaService.insert(dados);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza matrícula existente",
        description = "Altera os dados de uma matrícula já cadastrada, identificada pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matrícula atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public MatriculaDTO update(
        @Parameter(description = "ID da matrícula a ser atualizada")
        @PathVariable long id, @RequestBody MatriculaInsertDTO dados) {
        return matriculaService.update(id, dados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma matrícula",
        description = "Exclui permanentemente uma matrícula com base no ID informado." )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Matrícula removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Matrícula não encontrada")
    })
    public void delete(@Parameter(description = "ID da matrícula a ser removida") @PathVariable long id) {
        matriculaService.delete(id);
    }

    @GetMapping
    @Operation(summary = "Lista todas as matrículas",
        description = "Retorna uma lista com todas as matrículas registradas.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public Iterable<MatriculaDTO> getAll() {
        return matriculaService.getAll();
    }
}
