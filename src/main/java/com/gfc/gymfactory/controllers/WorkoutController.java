package com.gfc.gymfactory.controllers;

import com.gfc.gymfactory.dtos.request.WorkoutRequest;
import com.gfc.gymfactory.dtos.request.WorkoutUpdateRequest;
import com.gfc.gymfactory.dtos.response.WorkoutResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.services.WorkoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Workouts", description = "Gerenciamento de treinos")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @Operation(summary = "Criar treino")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkoutResponse create(@RequestBody @Valid WorkoutRequest request) {
        return workoutService.create(request);
    }

    @Operation(summary = "Buscar treino por ID")
    @GetMapping("/{id}")
    public WorkoutResponse findById(@PathVariable Long id) {
        return workoutService.findById(id);
    }

    @Operation(summary = "Listar treinos de uma rotina")
    @GetMapping("/routine/{routineId}")
    public PageResponse<WorkoutResponse> findByRoutine(
            @PathVariable Long routineId,
            Pageable pageable) {
        return workoutService.findByRoutine(routineId, pageable);
    }

    @Operation(summary = "Atualizar treino")
    @PutMapping("/{id}")
    public WorkoutResponse update(
            @PathVariable Long id,
            @RequestBody @Valid WorkoutUpdateRequest request) {
        return workoutService.update(id, request);
    }

    @Operation(summary = "Deletar treino")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        workoutService.delete(id);
    }
}