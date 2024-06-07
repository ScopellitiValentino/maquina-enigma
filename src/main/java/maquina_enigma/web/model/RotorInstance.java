package maquina_enigma.web.model;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class RotorInstance {

    @Getter @Setter
    private String izquierda;
    @Getter @Setter
    private String derecha;
    }



