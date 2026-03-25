package com.capgemini.book_partner_portal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "titles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Title {

    @Id
    @Column(name = "title_id", length = 10, nullable = false)
    @NotBlank(message = "Title ID is required")
    @Size(max = 10)
    private String titleId;

    @Column(name = "title", length = 80, nullable = false)
    @NotBlank(message = "Title name is required")
    @Size(max = 80)
    private String title;

    @Column(name = "type", length = 12, nullable = false)
    @NotBlank(message = "Type is required")
    @Size(max = 12)
    private String type = "UNDECIDED"; // Default value from schema

    @Column(name = "pub_id", length = 4)
    @Size(max = 4)
    private String pubId; // Stored as a simple String for now

    @Column(name = "price")
    @DecimalMin(value = "0.0", message = "Price cannot be negative")
    private Double price;

    @Column(name = "advance")
    private Double advance;

    @Column(name = "royalty")
    @Min(0)
    @Max(100)
    private Integer royalty;

    @Column(name = "ytd_sales")
    private Integer ytdSales;

    @Column(name = "notes", length = 200)
    @Size(max = 200)
    private String notes;

    @Column(name = "pubdate", nullable = false)
    @NotNull(message = "Publication date is required")
    private LocalDateTime pubdate;
}