package com.mph.bugtracker.ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mph.bugtracker.project.Project;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketName;

    private String ticketDescription;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private SeverityLevel severityLevel;

    private LocalDateTime createdAt = LocalDateTime.now();
}
