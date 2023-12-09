package view;

import model.AvaliacaoFisica;
import model.AvaliacaoFisicaDAO;
import model.Pessoa;
import model.PessoaDAO;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static utils.Utils.jConfirmation;
import static utils.Utils.jError;

public class JMenu {

    PessoaDAO pessoaDAO = new PessoaDAO();
    AvaliacaoFisicaDAO avaliacaoFisicaDAO = new AvaliacaoFisicaDAO();
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
                    Pessoa u = jLogin();
                    if (u == null){
                        jError("Username ou Senha não encontrado!");
                    }else{
                        userlogged  = u;
                        jMenu();
                    }
                    break;
                case 1:
                    jRegister();
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
        return pessoaDAO.login(username,password);
    }

    public void jRegister() {

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

    public  void jMenu() {
        String txt = "Bem-Vindo " + userlogged.getNome() + ", " +
                "\n O que deseja? " +
                "\n 1 - Ver Perfil " +
                "\n 2 - Atualizar Informações " +
                "\n 3 - Avaliação Física" +
                "\n 4 - Dieta/Alimentação" +
                "\n 5 - NutriSphere" +
                "\n 0 - LogOut";
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(txt));
            switch (op) {
                case 0:
                    userlogged = null;
                    break;
                case 1:
                    jConfirmation(userlogged.toString());
                    break;
                case 2:
                    jUpdate();
                    break;

//                case 3:
//                    jMenuPhysicalAssessment();
//                    break;

                case 4:
                  new JmenuFoods();
                  break;
//                case 5:
//                    jSocial();
//                    break;
                default:
                    jError("Opção Inválida, Por favor insira novamente.");
                    break;
            }
        } while (op != 0);
    }

    public void jUpdate() {
        int op;
        String menuUpdate = "Bem-Vindo " + userlogged.getNome()
                + "\n Qual seria o campo a ser atualizado? "
                + "\n 1 - Nome "
                + "\n 2 - Sexo "
                + "\n 3 - Nascimento "
                + "\n 4 - login "
                + "\n 5 - senha "
                + "\n 7 - Sair ";
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog(menuUpdate));
            switch (op){
                case 1:
                    userlogged.setNome(JOptionPane.showInputDialog("Insira o Novo nome"));
                    pessoaDAO.update(userlogged,op);
                    break;
                case 2:
                    userlogged.setSexo(JOptionPane.showInputDialog("Insira o novo sexo \n M - Masculino \n F - Feminino").charAt(0));
                    pessoaDAO.update(userlogged,op);
                    break;
                case 3:
                    userlogged.setNascimento(LocalDate.parse(JOptionPane.showInputDialog("Insira a nova data de nascimento Exemplo 25-09-1999")));
                    pessoaDAO.update(userlogged,op);
                    break;
                case 4:
                    userlogged.setUsuario(JOptionPane.showInputDialog("Insira o novo login"));
                    pessoaDAO.update(userlogged,op);
                    break;
                case 5:
                    userlogged.setSenha(JOptionPane.showInputDialog("Insira o Novo Senha"));
                    pessoaDAO.update(userlogged,op);
                    break;
                case 0:
                    break;
                default:
                    jError("Opção Invalida, Por favor insira novamente.");
                    break;
            }

        }while (op != 7);
    }

    public void jMenuPhysicalAssessment() {
        String msg = "O Que Gostaria de Fazer? " +
                "\n 1 - Fazer Avaliação Física" +
                "\n 2 - Ver Avaliações Antigas" +
                "\n 0 - Sair";
        int op;
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog(msg));
            switch (op){
                case 0:
                    break;
                case 1:
                    jPhysicalAssessment();
                    break;
                case 2:
                    break;
                default:
                    jError("Opção Inválida, Por favor Insira novamente");
                    break;
            }
        }while (op != 0);
    }

    private void jPhysicalAssessment() {
        String tax = "Qual é sua taxa de atividade: \n 1.2 - Sedentário (pouco ou nenhum exercicio)" +
                "\n 1.375: levemente ativo (exercício leve 1 a 3 dias por semana) " +
                "\n 1.55: moderadamente ativo (exercício moderado 6 a 7 dias por semana) " +
                "\n 1.725: muito ativo (exercício intenso todos os dias ou exercício duas vezes ao dia) " +
                "\n 1.9: extra ativo (exercício muito difícil, treinamento ou trabalho físico)";
        AvaliacaoFisica newAssessment = new AvaliacaoFisica();
        newAssessment.setUser(userlogged);
        newAssessment.setIdUser(userlogged.getId());
    }

}
