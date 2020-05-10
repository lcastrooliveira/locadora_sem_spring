package service;

import data.CarroDAO;
import model.Carro;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LocadoraService {

    private final CarroDAO carroDAO;

    public LocadoraService(CarroDAO carroDAO) {
        this.carroDAO = carroDAO;
    }

    //Fornecer: imprimir modelo - placa - diaria
    public List<Carro> mostrarCarrosNaGaragem() {
        return this.carroDAO.retornaRelacaoDeCarros(CarroDAO.GARAGEM).stream().map(carro -> {
            Carro registroCarro = new Carro();
            registroCarro.setModelo(carro.getModelo());
            registroCarro.setPlaca(carro.getPlaca());
            registroCarro.setDiaria(carro.getDiaria());
            return registroCarro;
        }).collect(Collectors.toList());
    }

    //Fornecer: modelo - portas - motor
    public List<Carro> mostrarCarrosEmManutencao() {
        return this.carroDAO.retornaRelacaoDeCarros(CarroDAO.OFICINA).stream().map(carro -> {
            Carro registroCarro = new Carro();
            registroCarro.setModelo(carro.getModelo());
            registroCarro.setNumeroPortas(carro.getNumeroPortas());
            registroCarro.setPotenciaMotor(carro.getPotenciaMotor());
            return registroCarro;
        }).collect(Collectors.toList());
    }

    //Fornecer: mostrar placa - modelo
    public List<Carro> mostrarCarrosLocados() {
        return this.carroDAO.retornaRelacaoDeCarros(CarroDAO.LOCADOS).stream().map(carro -> {
            Carro registroCarro = new Carro();
            registroCarro.setPlaca(carro.getPlaca());
            registroCarro.setModelo(carro.getModelo());
            return registroCarro;
        }).collect(Collectors.toList());
    }

    // Para locar carro: ver se está na garagem e disponível
    // Após locado: marcar como indisponível, tirar da garagem e marcar na lista de locados
    public void locarCarro(String placa) {
        final Optional<Carro> possivelCarro = this.carroDAO.encontraCarroPorPlaca(placa, CarroDAO.GARAGEM);
        possivelCarro.ifPresent(carro -> {
            carro.setDisponivel(false);
            this.carroDAO.retiraCarro(placa, CarroDAO.GARAGEM);
            this.carroDAO.estacionaCarro(carro, CarroDAO.LOCADOS);

        });
    }

    // Para devolver carro: ver se está na lista de locados
    // se estivar retirar da lista de locados e colocar de volta na garagem
    // marcar o carro como disponível
    public void devolverCarro(String placa) {
        final Optional<Carro> possivelCarro = this.carroDAO.encontraCarroPorPlaca(placa, CarroDAO.LOCADOS);
        possivelCarro.ifPresent(carro -> {
            carro.setDisponivel(true);
            this.carroDAO.retiraCarro(placa, CarroDAO.LOCADOS);
            this.carroDAO.estacionaCarro(carro, CarroDAO.GARAGEM);
        });

    }

    // Para mandar o carro na oficina o mesmo deve estar na garagem
    // tirar o carro da garagem e por na oficina
    // marcar como disponível
    public void mandarCarroParaManutencao(String placa) {
        final Optional<Carro> possivelCarro = this.carroDAO.encontraCarroPorPlaca(placa, CarroDAO.GARAGEM);
        possivelCarro.ifPresent(carro -> {
            carro.setDisponivel(false);
            this.carroDAO.retiraCarro(placa, CarroDAO.GARAGEM);
            this.carroDAO.estacionaCarro(carro, CarroDAO.OFICINA);
        });
    }

    //Para retirar o carro da manutenção, o mesmo precisa estar na oficina
    // tirar o carro da oficina e por na garagem
    // marcar como disponível
    public void buscarCarroDaManutencao(String placa) {
        final Optional<Carro> possivelCarro = this.carroDAO.encontraCarroPorPlaca(placa, CarroDAO.OFICINA);
        possivelCarro.ifPresent(carro -> {
            carro.setDisponivel(true);
            this.carroDAO.retiraCarro(placa, CarroDAO.OFICINA);
            this.carroDAO.estacionaCarro(carro, CarroDAO.GARAGEM);
        });
    }
}
