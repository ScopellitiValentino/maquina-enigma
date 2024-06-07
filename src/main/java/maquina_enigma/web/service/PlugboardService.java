package maquina_enigma.web.service;
import maquina_enigma.web.model.Plugboard;

public interface PlugboardService {
    public Plugboard createPlugboard();
    public String[][] acomodarString(String input);
    public Integer avanzar(Plugboard plugboard, Integer position);
    public Integer volver(Plugboard plugboard, Integer position);
    public void cambiarConfiguracion(Plugboard plugboard, String[][] arreglo);
}
