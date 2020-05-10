package view;

import model.Carro;
import service.LocadoraService;

import java.util.List;

public class InterfaceTexto {

    private final LocadoraService locadoraService;

    public InterfaceTexto(LocadoraService locadoraService) {
        this.locadoraService = locadoraService;
    }

    public void solicitarLocacao(String placa) {
        locadoraService.locarCarro(placa);
    }

    public void solicitarDevolucao(String placa) {
        locadoraService.devolverCarro(placa);
    }

    public void solicitarManutencao(String placa) {
        locadoraService.mandarCarroParaManutencao(placa);
    }

    public void solicitarRetiradaManutencao(String placa) {
        locadoraService.buscarCarroDaManutencao(placa);
    }

    public void mostrarStatusDaLocadora() {
        System.out.println("######Carros na garagem######");
        final List<Carro> carrosNaGaragem = this.locadoraService.mostrarCarrosNaGaragem();
        carrosNaGaragem.forEach(carro -> {
            System.out.println(String.format("modelo: %s", carro.getModelo()));
            System.out.println(String.format("placa: %s", carro.getPlaca()));
            System.out.println(String.format("diária: %f", carro.getDiaria()));
            System.out.println();
        });

        System.out.println("######Carros na oficina######");
        final List<Carro> carrosNaOficina = this.locadoraService.mostrarCarrosEmManutencao();
        carrosNaOficina.forEach(carro -> {
            System.out.println(String.format("modelo: %s", carro.getModelo()));
            System.out.println(String.format("portas: %s", carro.getNumeroPortas()));
            System.out.println(String.format("potência: %f", carro.getPotenciaMotor()));
            System.out.println();
        });

        System.out.println("######Carros locados######");
        final List<Carro> carrosLocados = this.locadoraService.mostrarCarrosLocados();
        carrosLocados.forEach(carro -> {
            System.out.println(String.format("modelo: %s", carro.getModelo()));
            System.out.println(String.format("placa: %s", carro.getPlaca()));
            System.out.println(String.format("automático: %b", carro.getAutomatico()));
            System.out.println();
        });
    }
}
