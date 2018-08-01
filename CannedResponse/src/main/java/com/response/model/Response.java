package com.response.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO(Data Transfer Object) Class
 */
@Setter @Getter @ToString
public class Response implements Serializable {
    private Integer response_id;
    private String title;
    private String message;
    private String label;
    private String mark_public;
    private LocalDate update_date;
}
