package maquina_enigma.web.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Data
public class Maquina {
    @Getter @Setter
    private RotorInstance rotor1;
    @Getter @Setter
    private RotorInstance rotor2;
    @Getter @Setter
    private RotorInstance rotor3;
    @Getter @Setter
    private Integer cantMicroGirosR1;
    @Getter @Setter
    private Integer cantMicroGirosR2;
    @Getter @Setter
    private Integer cantMicroGirosR3;
    @Getter @Setter
    private ReflectorInstance reflector;
    @Getter @Setter
    private Plugboard plugboard;
    @Getter @Setter
    private final String teclado = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

}
