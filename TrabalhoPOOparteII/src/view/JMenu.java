package view;

import model.Pessoa;
import model.PessoaDAO;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class JMenu {

    PessoaDAO pessoaDAO = new PessoaDAO();

    public JMenu() {
        jMenuLogin();
    }

    private void jMenuLogin() {

        Pessoa newUser = new Pessoa();

        newUser.setNome(JOptionPane.showInputDialog("Insira o nome"));

        do{
            String sexoInput = JOptionPane.showInputDialog("Insira o sexo \n M - Masculino \n F - Feminino");
            if (sexoInput.length() == 1 && (sexoInput.charAt(0) == 'M' || sexoInput.charAt(0) == 'F')){
                newUser.setSexo(sexoInput.charAt(0));
                break;
            }else{
                JOptionPane.showMessageDialog(null, "Sexo inválido. Por favor, insira M para Masculino ou F para Feminino.");
            }
        }while(true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        do{
                String dataNascimentoInput = JOptionPane.showInputDialog("Insira a data de nascimento Exemplo 25-09-1999");
                LocalDate dateFormat = LocalDate.parse(dataNascimentoInput);
                newUser.setNascimento(dateFormat);
                break;
        }while (true);

        newUser.setUsuario(JOptionPane.showInputDialog("Insira o novo login"));
        newUser.setSenha(JOptionPane.showInputDialog("Insira o Novo Senha"));


        newUser.setDataCriacao(LocalDate.now());
        newUser.setDataModificacao(LocalDate.now());

        pessoaDAO.add(newUser);
    }
}
