package com.mph.bugtracker.ticket;

import com.mph.bugtracker.project.Project;
import com.mph.bugtracker.project.ProjectNotFoundException;
import com.mph.bugtracker.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final ProjectRepository projectRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(ProjectRepository projectRepository,
                         TicketRepository ticketRepository) {
        this.projectRepository = projectRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + projectId));

        project.getTickets().add(ticket);
        ticket.setProject(project);
        projectRepository.save(project);
        return ticketRepository.save(ticket);
    }

    public Set<Ticket> getAllTickets(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + projectId));

        return project.getTickets();
    }

    public Ticket getTicketById(Long id, Long projectId) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + id));

        if (ticket.getProject().getId().equals(projectId)) {
            return ticket;
        }
        else {
            throw new TicketNotFoundException("Ticket not found with ID: " + id);
        }
    }

    public Ticket updateTicketById(Long id, Long projectId, Ticket updatedTicket) {
        Ticket existingTicket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + id));

        if (existingTicket.getProject().getId().equals(projectId)) {
            existingTicket.setTicketName(updatedTicket.getTicketName());
            existingTicket.setTicketDescription(updatedTicket.getTicketDescription());
            existingTicket.setSeverityLevel(updatedTicket.getSeverityLevel());
        }
        else {
            throw new TicketNotFoundException("Ticket not found with ID: " + id);
        }

        ticketRepository.save(existingTicket);
        return existingTicket;
    }

    public void deleteTicketById(Long id, Long projectId) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with ID: " + id));

        if (ticket.getProject().getId().equals(projectId)) {
            ticketRepository.deleteById(id);
        }
        else {
            throw new TicketNotFoundException("Ticket not found with ID: " + id);
        }
    }
}
