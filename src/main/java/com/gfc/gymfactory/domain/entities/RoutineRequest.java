package com.gfc.gymfactory.domain.entities;

import com.gfc.gymfactory.domain.enums.RoutineDifficulty;
import com.gfc.gymfactory.domain.enums.RoutineGoal;
import com.gfc.gymfactory.domain.enums.RoutineRequestStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "routine_requests")
public class RoutineRequest extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoutineGoal goal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoutineDifficulty difficulty;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoutineRequestStatus status;

    @Column(columnDefinition = "TEXT")
    private String rejectionReason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;
}