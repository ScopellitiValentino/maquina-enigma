package maquina_enigma.web.service;
import maquina_enigma.web.model.RotorInstance;

public interface RotorService {
    public RotorInstance createRotorInstance();
    public void girar(RotorInstance rotorInstance);
    public Integer avanzar(RotorInstance rotorInstance, Integer position);
    public Integer volver(RotorInstance rotorInstance, Integer position);
    public String getEstadoActual(RotorInstance rotorInstance);
    public void convertirATipo(RotorInstance rotorInstance, Integer tipo);
    public void moverRotorAPosicion(RotorInstance rotorInstance, String letra);

}
