package application.afericao;

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
@RequestMapping("/afericoes")
public class AfericaoController {
    @Autowired
    private AfericaoService afericaoService;

    @PostMapping
    public AfericaoDTO insert(@RequestBody AfericaoInsertDTO novaAfericao) {
        return afericaoService.insert(novaAfericao);
    }

    @GetMapping("/{id}")
    public AfericaoDTO getOne(@PathVariable long id) {
        return afericaoService.getOne(id);
    }

    @GetMapping
    @Operation(summary = "Lista todas as aferições",
        description = "Retorna uma lista com todas as aferições registradas.")
    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    public Iterable<AfericaoDTO> getAll() {
        return afericaoService.getAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza aferição existente",
        description = "Altera os dados de uma aferição já cadastrada, identificada pelo ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aferição atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aferição não encontrada")
    })
    public AfericaoDTO update(
        @Parameter(description = "ID da aferição a ser atualizada")
        @PathVariable long id, @RequestBody AfericaoInsertDTO novosDados) {
        return afericaoService.update(id, novosDados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma aferição",
        description = "Exclui permanentemente uma aferição com base no ID informado." )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Aferição removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aferição não encontrada")
    })
    public void remove(
        @Parameter(description = "ID da aferição a ser removida")
        @PathVariable long id) {
        afericaoService.delete(id);
    }
}