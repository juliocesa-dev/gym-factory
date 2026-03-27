package com.gfc.gymfactory.controllers;

import com.gfc.gymfactory.domain.enums.RoutineRequestStatus;
import com.gfc.gymfactory.dtos.request.RoutineRequestCreateDto;
import com.gfc.gymfactory.dtos.request.RoutineRequestRejectDto;
import com.gfc.gymfactory.dtos.response.RoutineRequestCreateResponse;
import com.gfc.gymfactory.dtos.response.RoutineRequestRejectResponse;
import com.gfc.gymfactory.dtos.response.RoutineRequestResponse;
import com.gfc.gymfactory.dtos.response.utils.PageResponse;
import com.gfc.gymfactory.services.RoutineRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Routine Requests", description = "Solicitações de rotina")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/routine-requests")
@RequiredArgsConstructor
public class RoutineRequestController {

    private final RoutineRequestService routineRequestService;

    @Operation(summary = "Aluno solicita uma rotina")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoutineRequestCreateResponse create(@RequestBody @Valid RoutineRequestCreateDto dto) {
        return routineRequestService.create(dto);
    }

    @Operation(summary = "Buscar solicitação por ID")
    @GetMapping("/{id}")
    public RoutineRequestResponse findById(@PathVariable Long id) {
        return routineRequestService.findById(id);
    }

    @Operation(summary = "Listar solicitações — filtro opcional por status")
    @GetMapping
    public PageResponse<RoutineRequestResponse> findAll(@RequestParam(required = false) RoutineRequestStatus status, Pageable pageable) {
        return routineRequestService.findAll(status, pageable);
    }

    @Operation(summary = "Listar solicitações de um aluno — filtro opcional por status")
    @GetMapping("/student/{studentId}")
    public PageResponse<RoutineRequestResponse> findByStudent
            (@PathVariable UUID studentId, @RequestParam(required = false) RoutineRequestStatus status, Pageable pageable) {
        return routineRequestService.findByStudent(studentId, status, pageable);
    }

    @Operation(summary = "Instrutor rejeita uma solicitação")
    @PatchMapping("/{id}/reject")
    public RoutineRequestRejectResponse reject(@PathVariable Long id, @RequestBody @Valid RoutineRequestRejectDto dto) {
        return routineRequestService.reject(id, dto);
    }
}