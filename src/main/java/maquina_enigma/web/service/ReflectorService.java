package maquina_enigma.web.service;
import maquina_enigma.web.model.ReflectorInstance;

public interface ReflectorService {
    public ReflectorInstance createReflector();
    public Integer reflectar(ReflectorInstance reflectorInstance,Integer position);
}
