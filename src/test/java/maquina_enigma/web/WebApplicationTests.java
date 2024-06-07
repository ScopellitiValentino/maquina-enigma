package maquina_enigma.web;
import maquina_enigma.web.model.Maquina;
import maquina_enigma.web.service.MaquinaService;
import maquina_enigma.web.service.PlugboardService;
import maquina_enigma.web.service.ReflectorService;
import maquina_enigma.web.service.RotorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {
    private final MaquinaService maquinaService;
    private final RotorService rotorService;
    private final PlugboardService plugboardService;
    private final ReflectorService reflectorService;

    @Autowired
    public WebApplicationTests(MaquinaService maquinaService, RotorService rotorService, PlugboardService plugboardService, ReflectorService reflectorService) {
        this.maquinaService = maquinaService;
        this.rotorService = rotorService;
        this.plugboardService = plugboardService;
        this.reflectorService = reflectorService;
    }

	@Test
	void Prueba() {
       String plugboard = "(A,A),(B,B),(C,C),(D,D),(E,E),(F,F),(G,G),(H,H),(I,I),(J,J),(K,K),(L,L),(M,M),(N,N),(O,O),(P,P),(Q,Q),(R,R),(S,S),(T,T),(U,U),(V,V),(W,W),(X,X),(Y,Y),(Z,Z)";

        Maquina maquina = maquinaService.createMaquina();

        String[][] entradaAcomodadaPlugboard = plugboardService.acomodarString(plugboard.toUpperCase().trim());

        for (String[] pares : entradaAcomodadaPlugboard) {
            System.out.print("("+pares[0] + "," + pares[1]+")");
            System.out.print(",");

        }

        plugboardService.cambiarConfiguracion(maquina.getPlugboard(), entradaAcomodadaPlugboard);



    }

}
