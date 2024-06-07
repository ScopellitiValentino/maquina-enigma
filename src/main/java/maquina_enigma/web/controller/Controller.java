package maquina_enigma.web.controller;
import jakarta.annotation.Nullable;
import maquina_enigma.web.model.Maquina;
import maquina_enigma.web.service.MaquinaService;
import maquina_enigma.web.service.PlugboardService;
import maquina_enigma.web.service.ReflectorService;
import maquina_enigma.web.service.RotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Locale;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {

    private final MaquinaService maquinaService;
    private final ReflectorService reflectorService;
    private final RotorService rotorService;
    private final PlugboardService plugboardService;

    @Autowired
    public Controller(MaquinaService maquinaService, ReflectorService reflectorService, RotorService rotorService, PlugboardService plugboardService) {
        this.maquinaService = maquinaService;
        this.reflectorService = reflectorService;
        this.rotorService = rotorService;
        this.plugboardService = plugboardService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("tipoRotor1", 1);
        model.addAttribute("tipoRotor2", 1);
        model.addAttribute("tipoRotor3", 1);
        return "home";
    }

    @PostMapping("/")
    public String encriptar(@RequestParam("rotor1") String rotor1,
                            @RequestParam("rotor2") String rotor2,
                            @RequestParam("rotor3") String rotor3,
                            @RequestParam("mensaje") String mensaje,
                            @RequestParam("tipoRotor1") Integer tipoRotor1,
                            @RequestParam("tipoRotor2") Integer tipoRotor2,
                            @RequestParam("tipoRotor3") Integer tipoRotor3,
                            @Nullable @RequestParam("plugboard") String plugboard,
                            Model model)  {

        if(plugboard == null || plugboard.isEmpty()) {
            plugboard = "(A,A),(B,B),(C,C),(D,D),(E,E),(F,F),(G,G),(H,H),(I,I),(J,J),(K,K),(L,L),(M,M),(N,N)," +
                    "(O,O),(P,P),(Q,Q),(R,R),(S,S),(T,T),(U,U),(V,V),(W,W),(X,X),(Y,Y),(Z,Z)";
        }

        Maquina maquina = maquinaService.createMaquina();

        String[][] entradaAcomodadaPlugboard = plugboardService.acomodarString(plugboard.toUpperCase().trim());

        plugboardService.cambiarConfiguracion(maquina.getPlugboard(), entradaAcomodadaPlugboard);

        rotorService.convertirATipo(maquinaService.getRotor1(maquina),tipoRotor1);
        rotorService.convertirATipo(maquinaService.getRotor2(maquina),tipoRotor2);
        rotorService.convertirATipo(maquinaService.getRotor3(maquina),tipoRotor3);

        maquinaService.ponerEstadoInicial(maquina,rotor1.toUpperCase(),rotor2.toUpperCase(),rotor3.toUpperCase());

        String mensajeTrim = mensaje.trim().toUpperCase(Locale.US);
        StringBuilder mensajeEncriptado = new StringBuilder();

        for (int i = 0; i < mensajeTrim.length(); i++) {
            Character charAt = mensajeTrim.charAt(i);
            if(charAt == ' '){mensajeEncriptado.append(' ');}
            else {
            Integer index = maquina.getTeclado().indexOf(charAt);
            if (index == -1) {
                throw new IllegalArgumentException("Carácter no válido en el mensaje: " + charAt);
            }

            mensajeEncriptado.append(maquinaService.cifrarLetra(maquina,index));
        }
        }

        model.addAttribute("mensajeEncriptado", mensajeEncriptado);

        model.addAttribute("estadoRotor1", maquinaService.verEstadodeRotor1(maquina));
        model.addAttribute("estadoRotor2", maquinaService.verEstadodeRotor2(maquina));
        model.addAttribute("estadoRotor3", maquinaService.verEstadodeRotor3(maquina));

        model.addAttribute("tipoRotor1", tipoRotor1);
        model.addAttribute("tipoRotor2", tipoRotor2);
        model.addAttribute("tipoRotor3", tipoRotor3);

        if(plugboard != "(A,A),(B,B),(C,C),(D,D),(E,E),(F,F),(G,G),(H,H),(I,I),(J,J),(K,K),(L,L),(M,M),(N,N)," +
                "(O,O),(P,P),(Q,Q),(R,R),(S,S),(T,T),(U,U),(V,V),(W,W),(X,X),(Y,Y),(Z,Z)"){
            model.addAttribute("plugboard", plugboard);
        }

        return "home";
    }

}