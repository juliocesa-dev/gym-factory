package com.gfc.gymfactory.controllers;

import com.gfc.gymfactory.domain.enums.ExerciseCategory;
import com.gfc.gymfactory.domain.enums.MuscleGroup;
import com.gfc.gymfactory.domain.enums.RoutineStatus;
import com.gfc.gymfactory.dtos.response.utils.MetadataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Tag(name = "Metadata", description = "Valores de enums para uso em dropdowns e filtros")
@RestController
@RequestMapping("/metadata")
public class MetadataController {

    @Operation(summary = "Grupos musculares")
    @GetMapping("/muscle-groups")
    public List<MetadataResponse> muscleGroups() {
        return Arrays.stream(MuscleGroup.values())
                .map(e -> new MetadataResponse(e.name(), e.getLabel()))
                .toList();
    }

    @Operation(summary = "Categorias de exercício")
    @GetMapping("/exercise-categories")
    public List<MetadataResponse> exerciseCategories() {
        return Arrays.stream(ExerciseCategory.values())
                .map(e -> new MetadataResponse(e.name(), e.getLabel()))
                .toList();
    }

    @Operation(summary = "Status de rotina")
    @GetMapping("/routine-status")
    public List<MetadataResponse> routineStatus() {
        return Arrays.stream(RoutineStatus.values())
                .map(e -> new MetadataResponse(e.name(), e.getLabel()))
                .toList();
    }

}