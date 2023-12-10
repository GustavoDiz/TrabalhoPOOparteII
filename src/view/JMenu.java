package view;

import model.*;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static utils.Utils.jConfirmation;
import static utils.Utils.jError;

public class JMenu {

    PessoaDAO pessoaDAO = new PessoaDAO();
    SeguirDAO seguirDAO = new SeguirDAO();
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

        Pessoa p = pessoaDAO.add(newUser);



        Seguir selfFollow = new Seguir();
        selfFollow.setFollowed(p.getId());
        selfFollow.setFollower(p.getId());
        selfFollow.setDataCriacao(LocalDate.now());
        selfFollow.setDataModificacao(LocalDate.now());
        seguirDAO.add(selfFollow);
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
                case 3:
                    jMenuPhysicalAssessment();
                    break;
                case 4:
                  new JmenuFoods();
                  break;
                case 5:
                    new JMenuSocial();
                    break;
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
                    StringBuilder text = new StringBuilder();
                    text.append("Peso\tAltura\tIdade\tPescoço\tCintura\tQuadril\tAbdomem\tBf\tImc\tTbm\tMassa Gorda\tMassa Magra\n");
                    ArrayList<AvaliacaoFisica> afs = avaliacaoFisicaDAO.myList(userlogged.getId());
                    for (AvaliacaoFisica af:
                            afs) {
                        System.out.println(af.toString());
                        text.append(af.toString());
                    }
                    JOptionPane.showMessageDialog(null,new JTextArea(text.toString()));
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
        newAssessment.setPeso(Double.parseDouble(JOptionPane.showInputDialog("Insira o Peso em kg (Exemplo: 67.0)")));
        newAssessment.setAltura(Double.parseDouble(JOptionPane.showInputDialog("Insira a altura em cm (Exemplo: 180)")));
        newAssessment.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Insira a sua idade")));
        newAssessment.setPescoco(Double.parseDouble(JOptionPane.showInputDialog("Insira a medida do seu Pescoço em cm (Exemplo: 20.5)")));
        newAssessment.setCintura(Double.parseDouble(JOptionPane.showInputDialog("Insira a medida da sua cintura em cm (Exemplo: 40.2)")));
        newAssessment.setQuadril(Double.parseDouble(JOptionPane.showInputDialog("Insira a medida do quadril")));
        newAssessment.setAbdomen(Double.parseDouble(JOptionPane.showInputDialog("Insira a do seu Abdômen (Exemplo: 43.5)")));
        newAssessment.calculateIMC();
        newAssessment.calculateTMB(Double.parseDouble(JOptionPane.showInputDialog(tax)));
        newAssessment.calculateBF();
        newAssessment.setDataCriacao(LocalDate.now());
        newAssessment.setDataModificacao(LocalDate.now());
        avaliacaoFisicaDAO.add(newAssessment);
        jPhysicalReport(newAssessment);
    }

    public void jPhysicalReport(AvaliacaoFisica avaliacaoFisica) {
        String type = avaliacaoFisica.idealBodyFat();
        String report = "Relatório Avaliação Física " +
                "\n Nome " + avaliacaoFisica.getUser().getNome() +
                "\n Idade " + avaliacaoFisica.getIdade() +
                "\n Altura " + avaliacaoFisica.getAltura() +
                "\n Peso " + avaliacaoFisica.getPeso() +
                "\n IMC " + avaliacaoFisica.getImc() +
                "\n TMB " + avaliacaoFisica.getTbm() +
                "\n Massa Magra " + avaliacaoFisica.getMassaMagra() +
                "\n Massa Gorda " + avaliacaoFisica.getMassaGorda() +
                "\n %BF " + avaliacaoFisica.getBf() +
                "\n Avalição Gordura Corporal " + type;
        jConfirmation(report);
    }

}
