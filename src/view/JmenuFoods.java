package view;

import model.*;

import javax.swing.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static utils.Utils.jConfirmation;
import static utils.Utils.jError;
import static view.JMenu.userlogged;


public class JmenuFoods {

    AlimentoReceitaDAO foods = new AlimentoReceitaDAO();
    TipoDietaDAO diets = new TipoDietaDAO();

    public JmenuFoods() {
        jDiet();
    }

    private void jDiet() {
        int op;
        String txt = "O que deseja? " +
                "\n 1 - Tipo Dieta " +
                "\n 2 - Registro Dieta" +
                "\n 3 - Refeições" +
                "\n 4 - Alimentos" +
                "\n 5 - Preferencias " +
                "\n 0 - Sair";

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(txt));
            switch (op) {
                case 0:
                    break;
                case 1:
                    jTypeDiet();
                    break;
                case 2:
                    //                   jRegisterDiet();
                    break;
                case 3:
                    //                   jMenuAddMeal();
                    break;
                case 4:
                    jMenusRecipe();
                    break;
                case 5:
//                    jPreferences();
                    break;
                default:
                    jError("Opção Inválida, Por favor insira novamente.");
                    break;
            }
        } while (op != 0);
    }

    public void jMenusRecipe() {
        int opcao;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Bem vindo ao menu de dietas, informe o que você deseja \n" +
                    "1 - Criar novo alimento \n" +
                    "2 - Verificar alimentos por ID \n" +
                    "3 - Atualizar alimentos\n" +
                    "4 - Excluir alimentos  \n" +
                    "5 - Verificar alimentos existentes \n" +
                    "0 - Sair \n"));
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    createRecipe();
                    break;
                case 2:
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Por favor, informe o ID do alimento"));
                    AlimentoReceita alimento2 = foods.getRecipeByIDFood(id);
                    if (alimento2 != null) {
                        jConfirmation("Dados do Alimento \n\n" + alimento2.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Alimento ou receita não encontrado", "Alerta", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 3:
                    jMenuAtt();
                    break;
                case 4:
                    int idDelete = Integer.parseInt(JOptionPane.showInputDialog("Por favor, informe o ID desse alimento"));
                    AlimentoReceita alimentoDelete = foods.getRecipeByIDFood(idDelete);
                    foods.delete(alimentoDelete);
                    break;
                case 5:
                    StringBuilder elementos = new StringBuilder();
                    elementos.append("Id\tNome\tCarboidratos\tProteina\tGordura\tCalorias\tPorção\n");
                    ArrayList<AlimentoReceita> FoodsExamples = new ArrayList<>();
                    FoodsExamples = foods.list();

                    if (!FoodsExamples.isEmpty()) {
                        for (AlimentoReceita alimento : FoodsExamples) {
                            elementos.append(alimento.toString());
                        }
                    } else {
                        System.out.println("\n\nNenhum elemento encontrado!! Verifique se você já criou algum");
                    }
                    JOptionPane.showMessageDialog(null,new JTextArea(elementos.toString()));

                    break;
                default:
                    jError("Opção Inválida, Por favor insira novamente.");
                    break;
            }

        }while (opcao != 0) ;
    }

    private void jMenuAtt(){
        int id  = Integer.parseInt(JOptionPane.showInputDialog("Por favor, informe o ID do alimento"));
        AlimentoReceita alimentoUpdate  = new AlimentoReceita();
        alimentoUpdate = foods.getRecipeByIDFood(id);

        int op;
        String menuUpdate =
                "\n Qual seria o campo a ser atualizado? "
                + "\n 1 - Nome do alimento "
                + "\n 2 - Carboidratos "
                + "\n 3 - Proteínas "
                + "\n 4 - Gorduras "
                + "\n 5 - Porcao "
                + "\n 6 - Sair ";
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog(menuUpdate));
            switch (op){
                case 1:
                    alimentoUpdate.setNome(JOptionPane.showInputDialog("Insira o Novo nome"));
                    foods.update(alimentoUpdate,op);
                    break;
                case 2:
                    alimentoUpdate.setCarboidratos(Double.parseDouble(JOptionPane.showInputDialog("Insira a nova data de nascimento Exemplo 25-09-1999")));
                    alimentoUpdate.setCalorias();
                    foods.update(alimentoUpdate,op);
                    break;
                case 3:
                    alimentoUpdate.setProteinas(Double.parseDouble(JOptionPane.showInputDialog("Insira a nova quantidade de proteínas")));
                    alimentoUpdate.setCalorias();
                    foods.update(alimentoUpdate,op);
                    break;
                case 4:
                    alimentoUpdate.setGorduras(Double.parseDouble(JOptionPane.showInputDialog("Insira a nova quantidade de gorduras")));
                    alimentoUpdate.setCalorias();
                    foods.update(alimentoUpdate,op);
                    break;
                case 5:
                    alimentoUpdate.setPorcao(Double.parseDouble(JOptionPane.showInputDialog("Insira a nova porção")));
                    foods.update(alimentoUpdate,op);
                    break;
                case 0:
                    break;
                default:
                    jError("Opção Invalida, Por favor insira novamente.");
                    break;
            }
        }while (op != 6);

    }

    private void jTypeDiet() {
        int op;
        String txt = "O que deseja?" +
                "\n 1 - Criar Novo Tipo de Dieta" +
                "\n 2 - Ver Tipos de Dieta " +
                "\n 3 - Sair";
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog(txt));
            switch (op) {
                case 1:
                    createDiet();
                    break;
                case 2:
                    StringBuilder dietss = new StringBuilder();
                    dietss.append("ID\tNome\tCarboidratos\t Proteina \tGorduras\n");
                    ArrayList<TipoDieta> diets11 = new ArrayList<>();
                    diets11 = diets.list();

                    if (!diets11.isEmpty()) {
                        for (TipoDieta d : diets11) {
                            dietss.append(d.toString());
                        }
                    } else {
                        System.out.println("\n\nNenhuma dieta encontrada!! Verifique se você já criou algum");
                    }
                    JOptionPane.showMessageDialog(null,new JTextArea(dietss.toString()));

                    break;

                default:
                    jError("Opção Inválida, Por favor insira novamente.");
                    break;
            }
        } while (op != 3);
    }
    private void createRecipe() {
        AlimentoReceita newreceipss = new AlimentoReceita();
        {
            newreceipss.setNome(JOptionPane.showInputDialog("Insira o nome do novo alimento"));
            newreceipss.setCarboidratos(Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade de carboidratos de-sse alimento")));
            newreceipss.setProteinas(Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade de de proteinas desse alimento")));
            newreceipss.setGorduras(Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade de gorduras desse alimento")));
            newreceipss.setPorcao(Double.parseDouble(JOptionPane.showInputDialog("Digite a porção desse alimento")));
            newreceipss.setCalorias();
            newreceipss.setDataCriacao(LocalDate.now());
            newreceipss.setDataModificacao(LocalDate.now());

            foods.add(newreceipss);
            JOptionPane.showMessageDialog(null, "Alimento criado com sucesso");
        }
    }

    private void createDiet() {
        TipoDieta newdiet = new TipoDieta();
        {
            newdiet.setNome(JOptionPane.showInputDialog("Insira o nome da nova dieta"));
            newdiet.setCarboidrato(Double.parseDouble(JOptionPane.showInputDialog("Insira a quantidade de carboidratos da nova dieta")));
            newdiet.setProteina(Double.parseDouble(JOptionPane.showInputDialog("Insira a quantidade de proteinas da nova dieta")));
            newdiet.setGordura(Double.parseDouble(JOptionPane.showInputDialog("Insira a quantidade de gorduras da nova dieta")));
            newdiet.setDataCriacao(LocalDate.now());
            newdiet.setDataModificacao(LocalDate.now());

            System.out.println(newdiet);

            diets.add(newdiet);
            JOptionPane.showMessageDialog(null, "Dieta base Adicionada com sucesso!!");
        }
    }
}
