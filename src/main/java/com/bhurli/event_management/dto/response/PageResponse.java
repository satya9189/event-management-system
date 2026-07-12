package com.bhurli.event_management.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

/**
 * Generic response DTO for paginated APIs. Custom pagination Response
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse <T>{//generic class
    @Schema(
            description = "List of records"
    )
    private List<T> content;

    @Schema(
            description = "Current page number",
            example = "0"
    )
    private int page;

    @Schema(
            description = "Number of records per page",
            example = "5"
    )
    private int size;

    @Schema(
            description = "Total number of records",
            example = "25"
    )
    private long totalElements;

    @Schema(
            description = "Total number of pages",
            example = "5"
    )
    private int totalPages;

    @Schema(
            description = "Whether this is the first page",
            example = "true"
    )
    private boolean first;

    @Schema(
            description = "Whether this is the last page",
            example = "false"
    )
    private boolean last;


}
