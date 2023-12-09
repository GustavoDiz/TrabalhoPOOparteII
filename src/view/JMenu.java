package view;

import model.Pessoa;
import model.PessoaDAO;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static utils.Utils.jError;

public class JMenu {

    PessoaDAO pessoaDAO = new PessoaDAO();
    //USUARIO LOGADO
    static Pessoa userlogged;

    public JMenu() {
        jHomepage();
    }

    private void jHomepage() {
        int op;
        String[] options = {"Login", "Cadastro", "Sair"};
        do {
            op = JOptionPane.showOptionDialog(null, "Bem Vindo a Nutrisoft!", "Boas Vindas!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (op) {
                case 0:
                    userlogged = jLogin();
                    if (userlogged != null){

                    }
                    break;
                default:
                    op = 2;
                    break;
            }
        } while (op != 2);
    }

    public Pessoa jLogin(){
        String username = JOptionPane.showInputDialog("Login");
        String password = JOptionPane.showInputDialog("Senha");

        Pessoa user = pessoaDAO.login(username,password);

        System.out.println(user.toString());
        return user;
    }
}
