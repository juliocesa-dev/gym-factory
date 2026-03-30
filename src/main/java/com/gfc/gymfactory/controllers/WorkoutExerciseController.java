package com.gfc.gymfactory.controllers;

import com.gfc.gymfactory.dtos.request.WorkoutExerciseRequest;
import com.gfc.gymfactory.dtos.request.WorkoutExerciseUpdateRequest;
import com.gfc.gymfactory.dtos.response.WorkoutExerciseResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.services.WorkoutExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Workout Exercises", description = "Exercícios de um treino")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/workout-exercises")
@RequiredArgsConstructor
public class WorkoutExerciseController {

    private final WorkoutExerciseService workoutExerciseService;

    @Operation(summary = "Adicionar exercício a um treino")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutExerciseResponse create(@RequestBody @Valid WorkoutExerciseRequest request) {
        return workoutExerciseService.create(request);
    }

    @Operation(summary = "Buscar exercício do treino por ID")
    @GetMapping("/{id}")
    public WorkoutExerciseResponse findById(@PathVariable Long id) {
        return workoutExerciseService.findById(id);
    }

    @Operation(summary = "Listar exercícios de um treino")
    @GetMapping("/workout/{workoutId}")
    public PageResponse<WorkoutExerciseResponse> findByWorkout(@PathVariable Long workoutId, Pageable pageable) {
        return workoutExerciseService.findByWorkout(workoutId, pageable);
    }

    @Operation(summary = "Atualizar exercício do treino")
    @PutMapping("/{id}")
    public WorkoutExerciseResponse update(@PathVariable Long id, @RequestBody @Valid WorkoutExerciseUpdateRequest request) {
        return workoutExerciseService.update(id, request);
    }

    @Operation(summary = "Remover exercício do treino")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        workoutExerciseService.delete(id);
    }
}