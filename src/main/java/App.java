import data.CarroDAO;
import service.LocadoraService;
import view.InterfaceTexto;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        CarroDAO carroDAO = new CarroDAO();
        LocadoraService locadoraService = new LocadoraService(carroDAO);
        InterfaceTexto interfaceTexto = new InterfaceTexto(locadoraService);

        System.out.println("Situação da locadora");
        interfaceTexto.mostrarStatusDaLocadora();
        scan.nextLine();

        System.out.println("Locando a BMW");
        interfaceTexto.solicitarLocacao("ABC-1234");
        interfaceTexto.mostrarStatusDaLocadora();
        scan.nextLine();

        System.out.println("Devolvendo o Gol");
        interfaceTexto.solicitarDevolucao("KLM-3453");
        interfaceTexto.mostrarStatusDaLocadora();
        scan.nextLine();

        System.out.println("Mandando o Gol para a oficina");
        interfaceTexto.solicitarManutencao("KLM-3453");
        interfaceTexto.mostrarStatusDaLocadora();
        scan.nextLine();

        System.out.println("Buscando o Corcel da Oficina");
        interfaceTexto.solicitarRetiradaManutencao("DFG-5678");
        interfaceTexto.mostrarStatusDaLocadora();
        scan.nextLine();

    }
}
