package org.example;

import DAO.CondutorDAO;
import DAO.FuncionarioDAO;
import DAO.GuicheDAO;
import DAO.OnibusDAO;
import Model.Funcionario;
import Model.Condutor;
import Model.Guiche;
import Model.Onibus;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        OnibusDAO onibusDAO = new OnibusDAO();
        CondutorDAO condutorDAO = new CondutorDAO();
        GuicheDAO guicheDAO = new GuicheDAO();
        byte op;
        Scanner scByte = new Scanner(System.in);
        Scanner scInt = new Scanner(System.in);
        Scanner scLong = new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);

        menu();

        op = scByte.nextByte();

        while(op != -1){
            int update_op, insert_op;
            int id, supervisor;
            String nome, endereco, email, telefone;
            String placa, marca, modelo;
            int numero;
            String cidade;
            int id_onibus;
            long cnh;
            boolean sucesso = false, sucesso2 = false;
            switch(op){
                case 1: // inserir funcionario
                    System.out.println("Inserir funcionário");
                    System.out.print("id: ");
                    id = scInt.nextInt();
                    System.out.print("nome: ");
                    nome = scStr.nextLine();
                    System.out.print("endereço: ");
                    endereco = scStr.nextLine();
                    System.out.print("email: ");
                    email = scStr.nextLine();
                    System.out.print("telefone: ");
                    telefone = scStr.nextLine();
                    System.out.print("Id supervisor: ");
                    supervisor = scInt.nextInt();
                    Funcionario f = new Funcionario(id, nome, endereco, email, telefone, supervisor);
                    sucesso = funcionarioDAO.insertFuncionario(f);
                    if(sucesso) System.out.println("Inserção realizada com sucesso");
                    else System.out.println("Inserção falha");
                    break;
                case 2: // update funcionário
                    System.out.println("Mudar informações de um funcionário");
                    System.out.print("id: ");
                    id = scInt.nextInt();
                    System.out.println("(1) -> Mudar o nome");
                    System.out.println("(2) -> Mudar o endereço");
                    System.out.println("(3) -> Mudar o e-mail");
                    System.out.println("(4) -> Mudar o telefone");
                    System.out.println("(5) -> Mudar o supervisor");
                    update_op = scInt.nextInt();
                    switch (update_op){
                        case 1:
                            System.out.print("Novo nome: ");
                            nome = scStr.nextLine();
                            sucesso = funcionarioDAO.updateFuncionario(id, nome, update_op);
                            break;
                        case 2:
                            System.out.print("Novo endereço: ");
                            endereco = scStr.nextLine();
                            sucesso = funcionarioDAO.updateFuncionario(id, endereco, update_op);
                            break;
                        case 3:
                            System.out.print("Novo e-mail: ");
                            email = scStr.nextLine();
                            sucesso = funcionarioDAO.updateFuncionario(id, email, update_op);
                            break;
                        case 4:
                            System.out.print("Novo telefone: ");
                            telefone = scStr.nextLine();
                            sucesso = funcionarioDAO.updateFuncionario(id, telefone, update_op);
                            break;
                        case 5:
                            System.out.print("Novo supervisor: ");
                            supervisor = scInt.nextInt();
                            sucesso = funcionarioDAO.updateFuncionario(id, supervisor);
                            break;
                        default:
                            System.out.println("Operação inválida");
                    }
                    if (sucesso) System.out.println("Update realizado com sucesso");
                    else System.out.println("Update falho");
                    break;
                case 3: // select funcionários
                    System.out.println("Funcionários em ordem alfabética");
                    funcionarioDAO.selectFuncionario();
                    break;
                case 4: // pesquisar por nome e id
                    System.out.println("Pesquisar por id e nome");
                    System.out.print("id: ");
                    id = scInt.nextInt();
                    System.out.print("nome: ");
                    nome = scStr.nextLine();
                    funcionarioDAO.pesquisaPorIdNome(id, nome);
                    break;
                case 5: // deletar funcionário
                    System.out.println("Deletar funcionário");
                    System.out.print("id: ");
                    id = scInt.nextInt();
                    sucesso = funcionarioDAO.deleteFuncionario(id);
                    if(sucesso) System.out.println("Deleção realizada com sucesso");
                    else System.out.println("Deleção falha");
                    break;
                case 6:
                    System.out.println("Inserir ônibus");
                    System.out.print("Id: ");
                    id = scInt.nextInt();
                    System.out.print("Placa: ");
                    placa = scStr.nextLine();
                    System.out.print("Marca: ");
                    marca = scStr.nextLine();
                    System.out.print("Modelo: ");
                    modelo = scStr.nextLine();
                    sucesso = onibusDAO.insertOnibus(new Onibus(id, placa, marca, modelo));
                    if (sucesso) System.out.println("Inserção realizada com sucesso");
                    else System.out.println("Inserção falha");
                    break;
                case 7:
                    System.out.println("Mudar placa do ônibus");
                    System.out.print("Id: ");
                    id = scInt.nextInt();
                    System.out.print("Nova placa: ");
                    placa = scStr.nextLine();
                    sucesso = onibusDAO.updatePlacaOnibus(id, placa);
                    if (sucesso) System.out.println("Update realizado com sucesso");
                    else System.out.println("Update falho");
                    break;
                case 8:
                    System.out.println("Mostrar todos os ônibus");
                    onibusDAO.selectOnibus();
                    break;
                case 9:
                    System.out.println("Quantidade x Marca");
                    onibusDAO.contMarcas();
                    break;
                case 10:
                    System.out.println("Deletar ônibus");
                    System.out.print("Id: ");
                    id = scInt.nextInt();
                    sucesso = onibusDAO.deleteOnibus(id);
                    if (sucesso) System.out.println("Deleção realizada com sucesso");
                    else System.out.println("Deleção falha");
                    break;
                case 11:
                    System.out.println("Inserir guichê");
                    System.out.print("Id responsável: ");
                    id = scInt.nextInt();
                    System.out.print("Número: ");
                    numero = scInt.nextInt();
                    System.out.print("Cidade: ");
                    cidade = scStr.nextLine();
                    sucesso = guicheDAO.insertGuiche(new Guiche(id, numero, cidade));
                    if (sucesso) System.out.println("Inserção realizada com sucesso");
                    else System.out.println("Inserção falha");
                    break;
                case 12:
                    System.out.println("Mudar responsável do guichê");
                    System.out.print("Número: ");
                    numero = scInt.nextInt();
                    System.out.print("Novo responsável id: ");
                    id = scInt.nextInt();
                    sucesso = guicheDAO.updateResponsavelGuiche(numero, id);
                    if (sucesso) System.out.println("Update realizado com sucesso");
                    else System.out.println("Update falho");
                    break;
                case 13:
                    System.out.println("Mostrar todos os guichês");
                    guicheDAO.selectGuiche();
                    break;
                case 14:
                    System.out.println("Cidades com guichês");
                    guicheDAO.cidadesComGuiches();
                    break;
                case 15:
                    System.out.println("Deletar guichê");
                    System.out.print("Número: ");
                    numero = scInt.nextInt();
                    sucesso = guicheDAO.deleteGuiche(numero);
                    if (sucesso) System.out.println("Deleção realizada com sucesso");
                    else System.out.println("Deleção falha");
                    break;
                case 16:
                    System.out.println("Inserir condutor");
                    System.out.println("(1) -> Criar novo funcionário como condutor");
                    System.out.println("(2) -> Promover funcionário à condutor");
                    insert_op = scInt.nextInt();
                    Condutor c;
                    switch (insert_op){
                        case 1:
                            // int id, String nome, String endereco, String email, String telefone, int supervisor, int id_onibus, long cnh
                            System.out.print("Id funcionário: ");
                            id = scInt.nextInt();
                            System.out.print("Nome: ");
                            nome = scStr.nextLine();
                            System.out.print("Endereco: ");
                            endereco = scStr.nextLine();
                            System.out.print("E-mail: ");
                            email = scStr.nextLine();
                            System.out.print("Telefone: ");
                            telefone = scStr.nextLine();
                            System.out.print("Id supervisor: ");
                            supervisor = scInt.nextInt();
                            System.out.print("Id ônibus: ");
                            id_onibus = scInt.nextInt();
                            System.out.print("Número da CNH: ");
                            cnh = scLong.nextLong();
                            c = new Condutor(id, nome, endereco, email, telefone, supervisor, id_onibus, cnh);
                            sucesso = funcionarioDAO.insertFuncionario(c);
                            sucesso2 = condutorDAO.insertCondutor(c);
                            break;
                        case 2:
                            System.out.print("Id funcionário: ");
                            id = scInt.nextInt();
                            System.out.print("Id ônibus: ");
                            id_onibus = scInt.nextInt();
                            System.out.print("Número da CNH: ");
                            cnh = scLong.nextLong();
                            c = new Condutor(id, id_onibus, cnh);
                            sucesso = condutorDAO.insertCondutor(c);
                            sucesso2 = true;
                            break;
                        default:
                            System.out.println("Operação inválida");
                    }
                    if(sucesso && sucesso2) System.out.println("Inserção realizada com sucesso");
                    else System.out.println("Inserção falha");
                    break;
                case 17:
                    System.out.println("Mostrar todos os condutores e seus ônibus");
                    condutorDAO.selectCondutor();
                    break;
                case 18:
                    System.out.println("Rebaixar funcionário do cargo de condutor");
                    System.out.print("Id funcionário: ");
                    id = scInt.nextInt();
                    System.out.print("Id ônibus: ");
                    id_onibus = scInt.nextInt();
                    sucesso = condutorDAO.deleteCondutor(id, id_onibus);
                    if(sucesso) System.out.println("Deleção realizada com sucesso");
                    else System.out.println("Deleção falha");
                    break;
                default:
                    System.out.println("Opção inválida");
            }

            menu();

            op = scByte.nextByte();
        }
    }

    public static void menu(){
        System.out.println("Funcionários                                               Ônibus");
        System.out.println("(01) -> Inserir funcionário                                (06) -> Inserir ônibus");
        System.out.println("(02) -> Mudar informações do funcionário                   (07) -> Mudar placa do ônibus");
        System.out.println("(03) -> Mostrar funcionários em ordem alfabética de nome   (08) -> Mostrar todos os onibus");
        System.out.println("(04) -> Pesquisar por id e nome                            (09) -> Contar marcas");
        System.out.println("(05) -> Deletar funcionário                                (10) -> Deletar ônibus");
        System.out.println("Guichês                                                    Condutores");
        System.out.println("(11) -> Inserir guichê                                     (16) -> Inserir condutor");
        System.out.println("(12) -> Mudar responsável do guichê                        (17) -> Mostrar todos os condutores e seus ônibus");
        System.out.println("(13) -> Mostrar todos os guichês                           (18) -> Deletar condutor (Rebaixar de cargo)");
        System.out.println("(14) -> Mostrar cidades com guichês                        Outras opções");
        System.out.println("(15) -> Deletar guichê                                     (-1) -> Sair");
    }
}