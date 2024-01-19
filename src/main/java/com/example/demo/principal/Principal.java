package com.example.demo.principal;

import com.example.demo.repository.MusicaRepository;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    public Principal(MusicaRepository repositorio) {
    }


    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                    1 - Cadastrar artistas
                    2 - Cadastrar Musicas
                    3 - Listar Musicas
                    4 - Buscar múscicas por artistas
                    5 - Pesquisar dados sobre um artista
                    
                    0 - Sair              

                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao){

                case 1:
                    cadastarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusica();
                    break;
                case 4:
                    buscarMusicaPorArtista();
                    break;
                case 5:
                    pesquisarDadosSobreUmArtista();
                    break;
            }
        }
    }

    private void cadastarArtista() {
    }

    private void cadastrarMusica() {

    }

    private void listarMusica() {

    }

    private void buscarMusicaPorArtista() {

    }

    private void pesquisarDadosSobreUmArtista() {

    }



}
