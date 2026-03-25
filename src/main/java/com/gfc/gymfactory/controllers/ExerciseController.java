package com.gfc.gymfactory.controllers;

import com.gfc.gymfactory.domain.enums.ExerciseCategory;
import com.gfc.gymfactory.domain.enums.MuscleGroup;
import com.gfc.gymfactory.dtos.request.ExerciseRequest;
import com.gfc.gymfactory.dtos.response.ExerciseResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.services.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Exercises", description = "Gerenciamento de exercícios")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Operation(summary = "Criar exercício")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExerciseResponse create(@RequestBody @Valid ExerciseRequest request) {
        return exerciseService.create(request);
    }

    @GetMapping
    public PageResponse<ExerciseResponse> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) MuscleGroup muscleGroup,
            @RequestParam(required = false) ExerciseCategory category,
            Pageable pageable
    ) {
        return exerciseService.findAll(name, muscleGroup, category, pageable);
    }

    @Operation(summary = "Buscar exercício por ID")
    @GetMapping("/{id}")
    public ExerciseResponse findById(@PathVariable Long id) {
        return exerciseService.findById(id);
    }

    @Operation(summary = "Atualizar exercício")
    @PutMapping("/{id}")
    public ExerciseResponse update(@PathVariable Long id, @RequestBody @Valid ExerciseRequest request) {
        return exerciseService.update(id, request);
    }

    @Operation(summary = "Deletar exercício")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        exerciseService.delete(id);
    }
}