package pl.coderslab.model;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class JsonResponse {
    private HttpStatus status;
    private String message;
}
