package com.mph.bugtracker.project;

import com.mph.bugtracker.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    private LocalDateTime createdAt = LocalDateTime.now();
}
