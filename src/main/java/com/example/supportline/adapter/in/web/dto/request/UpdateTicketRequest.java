package com.example.supportline.adapter.in.web.dto.request;

import com.example.supportline.domain.enums.TicketCategory;
import com.example.supportline.domain.enums.TicketPriority;

public record UpdateTicketRequest(
        String title,
        String description,
        TicketCategory category,
        TicketPriority priority
) {
}