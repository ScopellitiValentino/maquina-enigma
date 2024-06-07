package maquina_enigma.web.service;
import maquina_enigma.web.model.Plugboard;
import org.springframework.stereotype.Service;

@Service
public class PlugboardServiceImp implements PlugboardService {
    @Override
    public Plugboard createPlugboard() {
        Plugboard plugboard = new Plugboard();
        plugboard.setIzquierda("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        plugboard.setDerecha("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        return plugboard;
    }

    @Override
    public String[][] acomodarString(String input) {
        String[] pairs = input.replace("(", "").replace(")", "").split(",");

        String[][] arreglo = new String[pairs.length / 2][2];

        for (int i = 0; i < pairs.length; i += 2) {
            arreglo[i / 2][0] = pairs[i];
            arreglo[i / 2][1] = pairs[i + 1];
        }

        return arreglo;
    }

    @Override
    public Integer avanzar(Plugboard plugboard, Integer position) {
        Character caracter = plugboard.getIzquierda().charAt(position);
        return plugboard.getDerecha().indexOf(caracter);
    }

    @Override
    public Integer volver(Plugboard plugboard, Integer position) {
        Character caracter = plugboard.getDerecha().charAt(position);
        return plugboard.getIzquierda().indexOf(caracter);
    }

    @Override
    public void cambiarConfiguracion(Plugboard plugboard, String[][] arreglo) {
        for (String[] par : arreglo) {
            char caracterUno = par[0].charAt(0);
            char caracterDos = par[1].charAt(0);
            int posUno = plugboard.getIzquierda().indexOf(caracterUno);
            int posDos = plugboard.getDerecha().indexOf(caracterDos);

            plugboard.setDerecha(plugboard.getDerecha().substring(0, posUno) + caracterDos + plugboard.getDerecha().substring(posUno + 1));
            plugboard.setDerecha(plugboard.getDerecha().substring(0, posDos) + caracterUno + plugboard.getDerecha().substring(posDos + 1));
        }
    }
}
