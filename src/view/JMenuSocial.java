package view;

import model.MensagemDAO;
import model.PostDAO;
import model.SeguirDAO;

import javax.swing.*;

import static view.JMenu.userlogged;

public class JMenuSocial {
    MensagemDAO mensagemDAO = new MensagemDAO();
    PostDAO postDAO = new PostDAO();
    SeguirDAO seguirDAO = new SeguirDAO();
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

            }
        }while (op != 0);
    }
}
