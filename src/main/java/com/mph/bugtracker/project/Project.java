package com.mph.bugtracker.project;

import com.mph.bugtracker.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    private String projectDescription;

    @OneToMany(mappedBy = "project")
    private Set<Ticket> tickets = new HashSet<>();

    private LocalDateTime createdAt = LocalDateTime.now();
}
