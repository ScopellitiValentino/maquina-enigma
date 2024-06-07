package maquina_enigma.web.service;
import maquina_enigma.web.model.Maquina;
import maquina_enigma.web.model.RotorInstance;

public interface MaquinaService {
    public Maquina createMaquina();
    public void ponerEstadoInicial(Maquina maquina,String i,String j,String k);
    public String cifrarLetra (Maquina maquina, Integer posicion);
    public Integer cadenaDeCifrado(Maquina maquina, Integer posicion);
    public void CicloDeRotacion(Maquina maquina);
    public String verEstadodeRotor1(Maquina maquina);
    public String verEstadodeRotor2(Maquina maquina);
    public String verEstadodeRotor3(Maquina maquina);
    public RotorInstance getRotor1(Maquina maquina);
    public RotorInstance getRotor2(Maquina maquina);
    public RotorInstance getRotor3(Maquina maquina);
}
