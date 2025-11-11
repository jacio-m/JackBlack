package Classes;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class UsuarioCRUD {
    private static String arquivo = "save.ser"; //esse arquivo vai ficar os dados do usuario
    private ArrayList<Usuario> usuarios;

    public UsuarioCRUD() {
        usuarios = new ArrayList<>(); //sempre que inicia o codigo isso aqui reseta,
        //mas quando chama a função carregar ela preenche com o que foi salvo no arquivo.
        carregar();
    }
    
    public void cadastrar(String nome) {
            String nomeUnico = gerarNomeUnico(nome); //usa a funcao abaixo dessa
            //pra colocar um numero na frente do nome se for repetido pra nao dar erro
            usuarios.add(new Usuario(nomeUnico)); //cria um usuario novo e adiciona na lista
            salvar(); //salva a lista no arquivo
            JOptionPane.showMessageDialog(null, "Usuario " + nomeUnico + " adicionado!");
    }

    private String gerarNomeUnico(String nome) {
        String nomeOriginal = nome;
        int contador = 0;
        while (nomeExiste(nome)) { //ele executa enquanto o nome existir na lista
            contador++;
            nome = nomeOriginal + contador; //cria uma união do nome com o número se tiver repetindo
        }
        return nome;
    }

    private boolean nomeExiste(String nome) {
        for (Usuario u : usuarios) { 
            if (u.getNome().equalsIgnoreCase(nome)) { //se achar nome igual ignorando maiusculo e minusculo
                return true; //retorna que o nome existe
            }
        }
        return false; //se não achar, retorna que não existe
    }

    public void deletar(String nome) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNome().equalsIgnoreCase(nome)) { //pega o usuario com esse nome
                usuarios.remove(i); //remove da lista
                salvar(); //salva a lista atualizada no arquivo
                return; //ele para o metodo sem executar o que tem pela frente
            }
        }
        JOptionPane.showMessageDialog(null, "Selecione um usuario antes de excluir!"); //se não achar usuario mostra uma msg
    }

    public ArrayList<Usuario> listar() {
        return usuarios; //retorna uma arraylist
    }

    private void salvar() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(usuarios); //escreve a lista de usuarios no arquivo
        } catch (IOException e) {
            System.err.println("Erro ao salvar: " + e.getMessage()); 
        }
    }

    @SuppressWarnings("unchecked")
    private void carregar() {
        File dados = new File(arquivo);
        if (!dados.exists()) { //caso nao tenha arquivo salvo ainda
            usuarios = new ArrayList<>(); //cria lista vazia
            return;
        }
        
        //tenta ler do arquivo
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dados))) {
            Object obj = in.readObject();
            if (obj instanceof ArrayList<?>) { //confirma se o que foi lido é uma arraylist
                usuarios = (ArrayList<Usuario>) obj; //converte pra lista de usuarios
            } else {
                usuarios = new ArrayList<>(); //se não for uma lista ele cria uma vazia
                System.err.println("Formato de arquivo invalido."); 
            }
        } catch (IOException | ClassNotFoundException e) {
            usuarios = new ArrayList<>(); //se der erro cria uma lista vazia
            System.err.println("Erro ao carregar: " + e.getMessage()); 
        }
    }

    public void registrarVitoria(String nome) {
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome)) { //acha o usuario pelo nome
                u.addVitoria(); 
                salvar(); 
                return; //para o metodo pra n ficar rodando o for
            }
        }
    }

    public void registrarDerrota(String nome) {
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome)) { 
                u.addDerrota(); 
                salvar(); 
                return;
            }
        }
    }

    public void registrarEmpate(String nome) {
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome)) { 
                u.addEmpate(); 
                salvar(); 
                return;
            }
        }
    }
}
