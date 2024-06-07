package maquina_enigma.web.service;
import maquina_enigma.web.model.RotorInstance;
import org.springframework.stereotype.Service;

@Service
public class RotorServiceImp implements RotorService {
    @Override
    public RotorInstance createRotorInstance() {
        RotorInstance rotorInstance = new RotorInstance();
        rotorInstance.setIzquierda("EJMZALYXVBWFCRQUONTSPIKHGD");
        rotorInstance.setDerecha("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return rotorInstance;
    }

    @Override
    public void girar(RotorInstance rotorInstance) {
        Character primerCaracter = rotorInstance.getDerecha().charAt(0);
        StringBuilder sb = new StringBuilder(rotorInstance.getDerecha());
        sb.deleteCharAt(0);
        sb.append(primerCaracter);
        rotorInstance.setDerecha(sb.toString());
    }

    @Override
    public Integer avanzar(RotorInstance rotorInstance, Integer position) {
        Character caracter = rotorInstance.getIzquierda().charAt(position);
        return rotorInstance.getDerecha().indexOf(caracter);
    }

    @Override
    public Integer volver(RotorInstance rotorInstance, Integer position) {
        Character caracter = rotorInstance.getDerecha().charAt(position);
        return rotorInstance.getIzquierda().indexOf(caracter);
    }

    @Override
    public String getEstadoActual(RotorInstance rotorInstance) {
        return String.valueOf(rotorInstance.getDerecha().charAt(0));
    }

    @Override
    public void convertirATipo(RotorInstance rotorInstance, Integer tipo) {
        if (tipo == 1) {rotorInstance.setIzquierda("EKMFLGDQVZNTOWYHXUSPAIBRCJ");}
        else if (tipo == 2) {rotorInstance.setIzquierda("AJDKSIRUXBLHWTMCQGZNPYFVOE");}
        else if (tipo == 3) {rotorInstance.setIzquierda("BDFHJLCPRTXVZNYEIWGAKMUSQO");}
    }

    @Override
    public void moverRotorAPosicion(RotorInstance rotorInstance, String letra) {
        while(rotorInstance.getDerecha().charAt(0) != letra.charAt(0)) {
            girar(rotorInstance);
        }
    }

}
