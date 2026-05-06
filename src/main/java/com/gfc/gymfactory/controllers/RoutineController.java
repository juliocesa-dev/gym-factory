package com.gfc.gymfactory.controllers;

import com.gfc.gymfactory.domain.enums.RoutineDifficulty;
import com.gfc.gymfactory.dtos.request.RoutineRequestDto;
import com.gfc.gymfactory.dtos.request.RoutineUpdateRequest;
import com.gfc.gymfactory.dtos.response.RoutineDetailResponse;
import com.gfc.gymfactory.dtos.response.RoutineResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.services.RoutineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Routines", description = "Gerenciamento de rotinas")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @Operation(summary = "Criar rotina")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoutineResponse create(@RequestBody @Valid RoutineRequestDto request) {
        return routineService.create(request);
    }

    @Operation(summary = "Listar todas as rotinas")
    @GetMapping
    public PageResponse<RoutineResponse> findAll(Pageable pageable) {
        return routineService.findAll(pageable);
    }

    @Operation(summary = "Buscar rotina por ID")
    @GetMapping("/{id}")
    public RoutineResponse findById(@PathVariable Long id) {
        return routineService.findById(id);
    }

    @Operation(summary = "Buscar rotina completa com workouts e exercícios")
    @GetMapping("/{id}/detail")
    public RoutineDetailResponse findDetailById(@PathVariable Long id) {
        return routineService.findDetailById(id);
    }

    @Operation(summary = "Listar rotinas de um aluno")
    @GetMapping("/student/{studentId}")
    public PageResponse<RoutineResponse> findByStudent(@PathVariable UUID studentId, Pageable pageable) {
        return routineService.findByStudent(studentId, pageable);
    }

    @Operation(summary = "Listar todos os templates")
    @GetMapping("/templates")
    public PageResponse<RoutineResponse> findTemplates(@RequestParam(required = false) RoutineDifficulty difficulty, Pageable pageable) {
        return routineService.findTemplates(difficulty, pageable);
    }

    @Operation(summary = "Listar templates de um instrutor")
    @GetMapping("/templates/instructor/{instructorId}")
    public PageResponse<RoutineResponse> findTemplatesByInstructor(@PathVariable UUID instructorId,
                                                                   @RequestParam(required = false) RoutineDifficulty difficulty, Pageable pageable) {
        return routineService.findTemplatesByInstructor(instructorId, difficulty, pageable);
    }

    @Operation(summary = "Aplicar template a um aluno")
    @PostMapping("/{id}/apply")
    @ResponseStatus(HttpStatus.CREATED)
    public RoutineResponse applyTemplate(@PathVariable Long id, @RequestParam UUID studentId, @RequestParam UUID instructorId) {
        return routineService.applyTemplate(id, studentId, instructorId);
    }

    @Operation(summary = "Atualizar rotina")
    @PutMapping("/{id}")
    public RoutineResponse update(@PathVariable Long id, @RequestBody @Valid RoutineUpdateRequest request) {
        return routineService.update(id, request);
    }

    @Operation(summary = "Deletar rotina")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        routineService.delete(id);
    }
}