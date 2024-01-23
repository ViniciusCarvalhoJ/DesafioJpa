package com.example.demo.principal;

import com.example.demo.model.Artista;
import com.example.demo.model.Musica;
import com.example.demo.model.TipoArtista;
import com.example.demo.repository.ArtistaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final ArtistaRepository repositorio;
    private Scanner leitura = new Scanner(System.in);

    List<Musica> musicas = new ArrayList<>();

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }


    public void exibirMenu() {
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                    1 - Cadastrar artistas
                    2 - Cadastrar Musicas
                    3 - Listar Musicas
                    4 - Buscar múscicas por artistas
                                      
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

                case 0:
                    System.out.println("Encerrando a aplicação");
                default:
                    System.out.println("Opção Inválida!!");
            }
        }
    }



    private void cadastarArtista() {

        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("s")){
        System.out.println("Digite o nome do artista para ser cadastrado: ");
        var nome = leitura.nextLine();
        System.out.println("Qual o tipo do artista: (solo, dupla ou banda)");
        var tipo = leitura.nextLine();

        //conversão do valor passado pelo usuario p/ maiusculo
        TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());

        Artista artista = new Artista(nome, tipoArtista);
        repositorio.save(artista);
        System.out.println("Cadastrar um novo artista? (S/N");
        cadastrarNovo = leitura.nextLine();
    }
    }

    private void cadastrarMusica() {
        System.out.println("Cadastrar Musica de que artista: ");
        var nome = leitura.nextLine();

        //Um optional de artista que devolve o artista que tiver cadastrado no BD.
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);

        if(artista.isPresent()) {
            System.out.println("Informe o nome da musica: ");
            var nomeMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());
        } else {
            System.out.println("Artista não encontrado");
        }

    }

    private void listarMusica() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));

    }

    private void buscarMusicaPorArtista() {
        System.out.println("Busca musicas de que artista? ");
        var nome = leitura.nextLine();

        List<Musica> musicas = repositorio.buscaMusicaPorArtista(nome);
        musicas.forEach(System.out::println);
    }




}
