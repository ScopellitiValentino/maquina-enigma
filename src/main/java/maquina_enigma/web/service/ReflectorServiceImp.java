package maquina_enigma.web.service;
import maquina_enigma.web.model.ReflectorInstance;
import org.springframework.stereotype.Service;

@Service
public class ReflectorServiceImp implements ReflectorService {

    @Override
    public ReflectorInstance createReflector() {
        ReflectorInstance reflectorInstance = new ReflectorInstance();
        reflectorInstance.setIzquierda("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        reflectorInstance.setDerecha("EJMZALYXVBWFCRQUONTSPIKHGD");
        return reflectorInstance;
    }

    @Override
    public Integer reflectar(ReflectorInstance reflectorInstance, Integer position) {
        Character caracter = reflectorInstance.getIzquierda().charAt(position);
        return reflectorInstance.getDerecha().indexOf(caracter);
    }


}
