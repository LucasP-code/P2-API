package application.matricula.aluno;

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
@RequestMapping("/alunos")
@Tag(name = "Alunos", description = "Operações relacionadas a alunos")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    @Operation(summary = "Cria um novo aluno",
        description = "Adiciona um novo aluno ao sistema com os dados fornecidos.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aluno criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public AlunoDTO insert(@RequestBody AlunoInsertDTO novoAluno) {
        return alunoService.insert(novoAluno);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém uma aferição por ID",
        description = "Retorna os detalhes de uma aferição específica com base no ID fornecido.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aferição encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aferição não encontrada")
    })
    public AlunoDTO getOne(
        @Parameter(description = "ID da aferição a ser obtida")
        @PathVariable long id) {
        return alunoService.getOne(id);
    }

    @GetMapping
    @Operation(summary = "Lista todas as aferições",
        description = "Retorna uma lista com todas as aferições registradas.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public Iterable<AlunoDTO> getAll() {
        return alunoService.getAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza aferição existente",
        description = "Altera os dados de uma aferição já cadastrada, identificada pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aferição atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aferição não encontrada")
    })
    public AlunoDTO update(
        @Parameter(description = "ID da aferição a ser atualizada")
        @PathVariable long id, @RequestBody AlunoInsertDTO novosDados) {
        return alunoService.update(id, novosDados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma aferição",
        description = "Exclui permanentemente uma aferição com base no ID informado." )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Aferição removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aferição não encontrada")
    })
    public void remove(@PathVariable long id) {
        alunoService.delete(id);
    }
}