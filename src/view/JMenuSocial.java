package view;

import model.*;

import javax.swing.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static utils.Utils.jConfirmation;
import static utils.Utils.jError;
import static view.JMenu.userlogged;

public class JMenuSocial {
    MensagemDAO mensagemDAO = new MensagemDAO();
    PostDAO postDAO = new PostDAO();
    SeguirDAO seguirDAO = new SeguirDAO();
    PessoaDAO pessoaDAO = new PessoaDAO();
    public JMenuSocial() {
        jSocial();
    }

    private void jSocial() {
        StringBuilder txt = new StringBuilder();
        txt.append("Bem Vindo a NutriSphere, ").append(userlogged.getNome());
        //txt.append("\n Seguidores: ").append(follows.followers(userlogged));
        txt.append("\n 1 - Ver Posts");
        txt.append("\n 2 - Criar Post");
        txt.append("\n 3 - Ver Mensagens");
        txt.append("\n 4 - Enviar Mensagem");
        txt.append("\n 5 - Adicionar Amigo");
        txt.append("\n 0 - Sair");
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(txt));
            switch (op){
                case 1:
                    ArrayList<Post> posts = postDAO.listMyPosts(userlogged.getId());
                    StringBuilder postText =new StringBuilder();
                    postText.append("--- Posts --- \n");
                    if(!posts.isEmpty()){
                        for (Post p:
                                posts) {
                            postText.append(p.toString());
                        }

                    }else{
                        postText.append(" Sem Posts no momento \n");
                    }
                    jConfirmation(postText.toString());
                    break;
                case 2:
                    String msgPost = JOptionPane.showInputDialog("Qual o post?");
                    Post newPost = new Post();
                    newPost.setUserId(userlogged.getId());
                    newPost.setMsg(msgPost);
                    newPost.setDataModificacao(LocalDate.now());
                    newPost.setDataCriacao(LocalDate.now());
                    postDAO.add(newPost);
                    break;
                case 3:
                    ArrayList<Mensagem> mensagems = mensagemDAO.myList(userlogged.getId());
                    StringBuilder mensagemText = new StringBuilder();
                    if (mensagems.isEmpty()){
                        mensagemText.append(" Sem Mensagens no momento \n");
                    }else{
                        for (Mensagem m:
                             mensagems) {
                            mensagemText.append(m.toString());
                        }
                    }
                    jConfirmation(mensagemText.toString());
                    break;
                case 4:
                    String userName = JOptionPane.showInputDialog("Para Quem Gostaria de Enviar a Mensagem");
                    Pessoa userFound = pessoaDAO.findUser(userName);
                    if(userFound == null){
                        jError("Usuario não encontrado, porfavor insira novamente");
                    }else{
                        String msgText = JOptionPane.showInputDialog("Qual é a mensagem");
                        Mensagem newMsg = new Mensagem();
                        newMsg.setSenderId(userlogged.getId());
                        newMsg.setRecipientId(userFound.getId());
                        newMsg.setMsg(msgText);
                        newMsg.setDataCriacao(LocalDate.now());
                        newMsg.setDataModificacao(LocalDate.now());
                        mensagemDAO.add(newMsg);
                    }
                    break;
                case 5:
                    String username = JOptionPane.showInputDialog("Quem Gostaria de Adicionar:");
                    Pessoa followUser = pessoaDAO.findUser(username);
                    if (followUser != null) {
                        Seguir follow = new Seguir();
                        follow.setFollower(userlogged.getId());
                        follow.setFollowed(followUser.getId());
                        follow.setDataCriacao(LocalDate.now());
                        follow.setDataModificacao(LocalDate.now());
                        seguirDAO.add(follow);
                        jConfirmation("Amigo adicionado com sucesso.");
                    } else {
                        jError("Error ao adicionar o amigo,Insira Novamente.");
                    }
                    break;
                default:
                    op = 0;
            }
        }while (op != 0);
    }
}
