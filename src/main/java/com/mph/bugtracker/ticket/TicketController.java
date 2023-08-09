package com.mph.bugtracker.ticket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/projects/{projectId}/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket,
                                               @PathVariable Long projectId) {
        Ticket createdTicket = ticketService.createTicket(ticket, projectId);
        return ResponseEntity.ok(createdTicket);
    }

    @GetMapping
    public ResponseEntity<Set<Ticket>> getAllTicketsByProjectId(@PathVariable Long projectId) {
        Set<Ticket> tickets = ticketService.getAllTickets(projectId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id,
                                                @PathVariable("projectId") Long projectId) {
        Ticket ticket = ticketService.getTicketById(id, projectId);
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicketById(@PathVariable("id") Long id,
                                                   @PathVariable("projectId") Long projectId,
                                                   @RequestBody Ticket ticket) {
        Ticket updatedTicket = ticketService.updateTicketById(id, projectId, ticket);
        return ResponseEntity.ok(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketById(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
