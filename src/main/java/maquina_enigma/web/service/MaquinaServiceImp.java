package maquina_enigma.web.service;
import maquina_enigma.web.model.Maquina;
import maquina_enigma.web.model.Plugboard;
import maquina_enigma.web.model.ReflectorInstance;
import maquina_enigma.web.model.RotorInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaquinaServiceImp implements MaquinaService {

    private final PlugboardService plugboardService;
    private final RotorService rotorService;
    private final ReflectorService reflectorService;

    @Autowired
    public MaquinaServiceImp(PlugboardService plugboardService, RotorService rotorService, ReflectorService reflectorService){
        this.plugboardService = plugboardService;
        this.rotorService = rotorService;
        this.reflectorService = reflectorService;
    }


    @Override
    public Maquina createMaquina() {
        Maquina maquina = new Maquina();

        RotorInstance rotor1 = rotorService.createRotorInstance();
        RotorInstance rotor2 = rotorService.createRotorInstance();
        RotorInstance rotor3 = rotorService.createRotorInstance();
        maquina.setRotor1(rotor1);
        maquina.setRotor2(rotor2);
        maquina.setRotor3(rotor3);

        maquina.setCantMicroGirosR1(0);
        maquina.setCantMicroGirosR2(0);
        maquina.setCantMicroGirosR3(0);

        ReflectorInstance reflector = reflectorService.createReflector();
        maquina.setReflector(reflector);

        Plugboard plugboard = plugboardService.createPlugboard();
        maquina.setPlugboard(plugboard);

        ponerEstadoInicial(maquina,"A","A","A");

        return maquina;
    }

    @Override
    public void ponerEstadoInicial(Maquina maquina, String i, String j, String k) {
        rotorService.moverRotorAPosicion(getRotor1(maquina),i);
        rotorService.moverRotorAPosicion(getRotor2(maquina),j);
        rotorService.moverRotorAPosicion(getRotor3(maquina),k);
    }



    @Override
    public String cifrarLetra(Maquina maquina, Integer posicion) {
        CicloDeRotacion(maquina);
        String letraCifrada = String.valueOf(maquina.getTeclado().charAt(cadenaDeCifrado(maquina,posicion)));
       return letraCifrada;
    }

    @Override
    public Integer cadenaDeCifrado(Maquina maquina, Integer posicion) {
        posicion = plugboardService.avanzar(maquina.getPlugboard(),posicion);
        posicion = rotorService.avanzar(getRotor1(maquina),posicion);
        posicion = rotorService.avanzar(getRotor2(maquina),posicion);
        posicion = rotorService.avanzar(getRotor3(maquina),posicion);
        posicion = reflectorService.reflectar(maquina.getReflector(),posicion);
        posicion = rotorService.volver(getRotor3(maquina),posicion);
        posicion = rotorService.volver(getRotor2(maquina),posicion);
        posicion = rotorService.volver(getRotor1(maquina),posicion);
        posicion = plugboardService.volver(maquina.getPlugboard(),posicion);
        return posicion;
    }

    @Override
    public void CicloDeRotacion(Maquina maquina) {
        rotorService.girar(getRotor1(maquina));
        maquina.setCantMicroGirosR1(maquina.getCantMicroGirosR1() + 1);
            if(maquina.getCantMicroGirosR1() == 26){
                maquina.setCantMicroGirosR1(0);
                rotorService.girar(getRotor2(maquina));
                maquina.setCantMicroGirosR2(maquina.getCantMicroGirosR2() + 1);

                if(maquina.getCantMicroGirosR2() == 26){
                    maquina.setCantMicroGirosR2(0);
                    rotorService.girar(getRotor3(maquina));
                    maquina.setCantMicroGirosR3(maquina.getCantMicroGirosR3() + 1);

                    if(maquina.getCantMicroGirosR3() == 26){
                        maquina.setCantMicroGirosR3(0);
                    }
                }
            }
    }

    @Override
    public String verEstadodeRotor1(Maquina maquina) {
        return rotorService.getEstadoActual(getRotor1(maquina));
    }

    @Override
    public String verEstadodeRotor2(Maquina maquina) {
        return rotorService.getEstadoActual(getRotor2(maquina));
    }

    @Override
    public String verEstadodeRotor3(Maquina maquina) {
        return rotorService.getEstadoActual(getRotor3(maquina));
    }

    @Override
    public RotorInstance getRotor1(Maquina maquina) {
        return maquina.getRotor1();
    }

    @Override
    public RotorInstance getRotor2(Maquina maquina) {
        return maquina.getRotor2();
    }

    @Override
    public RotorInstance getRotor3(Maquina maquina) {
        return maquina.getRotor3();
    }
}
