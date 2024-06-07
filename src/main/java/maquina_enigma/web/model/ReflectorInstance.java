package maquina_enigma.web.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class ReflectorInstance {
    @Getter @Setter
    private String izquierda;
    @Getter @Setter
    private String derecha;
}
