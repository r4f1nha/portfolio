package com.botaro.portifolio.api.dto;

public record ProjectRequest(
        long id,
        String title,
        String description
) {
}
