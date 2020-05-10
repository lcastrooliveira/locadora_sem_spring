package data;

import model.Carro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarroDAO {

    public static final String GARAGEM = "GARAGEM";
    public static final String LOCADOS = "LOCADOS";
    public static final String OFICINA = "OFICINA";

    private final List<Carro> carrosNaGaragem;
    private final List<Carro> carrosLocados;
    private final List<Carro> carrosNaOficina;

    public CarroDAO() {
        carrosNaGaragem = new ArrayList<>();
        carrosLocados = new ArrayList<>();
        carrosNaOficina = new ArrayList<>();
        arrumarOsCarros();
    }

    public List<Carro> retornaRelacaoDeCarros(String lugar) {
        final List<Carro> relacaoVeiculos = encontraOndeEstaOCarro(lugar);
        return new ArrayList<>(relacaoVeiculos);
    }

    public void estacionaCarro(Carro carro, String lugar) {
        final List<Carro> lugarOndeEstaOCarro = encontraOndeEstaOCarro(lugar);
        lugarOndeEstaOCarro.add(carro);
    }

    public Optional<Carro> encontraCarroPorPlaca(String placa, String lugar) {
        final List<Carro> lugarOndeEstaOCarro = encontraOndeEstaOCarro(lugar);
        return lugarOndeEstaOCarro.stream()
                   .filter(carro -> carro.getPlaca().equals(placa))
                   .findFirst();
    }

    public void retiraCarro(String placa, String lugar) {
        final List<Carro> lugarOndeEstaOCarro = encontraOndeEstaOCarro(lugar);
        lugarOndeEstaOCarro.removeIf(carro -> carro.getPlaca().equals(placa));
    }

    private List<Carro> encontraOndeEstaOCarro(String lugar) {
        switch (lugar) {
            case LOCADOS:
                return this.carrosLocados;
            case OFICINA:
                return this.carrosNaOficina;
            default:
                return this.carrosNaGaragem;
        }
    }

    private void arrumarOsCarros() {
        final Carro bmw = new Carro();
        bmw.setId(1L);
        bmw.setDisponivel(true);
        bmw.setAutomatico(true);
        bmw.setModelo("BMW 320i");
        bmw.setNumeroPortas(4);
        bmw.setPlaca("ABC-1234");
        bmw.setPotenciaMotor(2.4);
        bmw.setDiaria(900.00);

        final Carro corcelII = new Carro();
        corcelII.setId(2L);
        corcelII.setDisponivel(false);
        corcelII.setAutomatico(false);
        corcelII.setModelo("Corcel II");
        corcelII.setNumeroPortas(4);
        corcelII.setPlaca("DFG-5678");
        corcelII.setPotenciaMotor(1.4);
        corcelII.setDiaria(60.00);

        final Carro gol = new Carro();
        gol.setId(3L);
        gol.setDisponivel(false);
        gol.setAutomatico(false);
        gol.setModelo("Gol City");
        gol.setNumeroPortas(2);
        gol.setPlaca("KLM-3453");
        gol.setPotenciaMotor(1.0);
        gol.setDiaria(70.00);

        carrosNaGaragem.add(bmw);
        carrosLocados.add(gol);
        carrosNaOficina.add(corcelII);
    }
}
