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
        jMenuLogin();
    }

    private void jMenuLogin() {
        int op;
        String[] options = {"Login", "Cadastro", "Sair"};
        do {
            op = JOptionPane.showOptionDialog(null, "Bem Vindo a Nutrisoft!", "Boas Vindas!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            System.out.println(op);
//            switch (op) {
//                case 0:
//                    //Pessoa user = jLogin();
//                    if (user == null) {
//                        jError("Username ou Senha não encontrado!");
//                    } else {
//                        userlogged = user;
//                        jMenu();
//                    }
//                    break;
//                case 1:
//                    //jRegister();
//                    break;
//                default:
//                    op = 2;
//                    break;
//            }
        } while (op != 2);
    }
}
