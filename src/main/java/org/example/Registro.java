package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Data
public class Registro {
    LocalDateTime fechaHora;
    Double temperatura;
    Double humedad;
}
