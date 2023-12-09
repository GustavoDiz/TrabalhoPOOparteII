package view;

import model.AlimentoReceita;
import model.AlimentoReceitaDAO;

import javax.swing.*;

import java.time.LocalDate;

import static utils.Utils.jConfirmation;
import static utils.Utils.jError;

public class JmenuFoods {

    AlimentoReceitaDAO foods = new AlimentoReceitaDAO();

    public JmenuFoods(){
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
                    //                   jTypeDiet();
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
                    "2 - Verificar alimentos por nome \n" +
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
                    int id  = Integer.parseInt(JOptionPane.showInputDialog("Por favor, informe o ID do alimento"));
                    AlimentoReceita alimento2  = foods.getRecipeByIDFood(id);
                    if (alimento2 != null) {
                        jConfirmation("Dados do Alimento \n\n" + alimento2.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Alimento ou receita não encontrado", "Alerta", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 3:
 /*
                    break;
                case 4:
                    int idDelete  = Integer.parseInt(JOptionPane.showInputDialog("Por favor, informe o nome desse alimento"));
//                    foods.deleteRecipe(idDelete);
                    if (foods.deleteRecipe(idDelete)){
                        jConfirmation("Comida Deletada com Sucesso");
                    }else{
                        jError("Comida não Encontrada, Por favor insira novamente");
                    }
                    break;
                case 5:
                    foodsExemples();
                    break;

                    */
                default:
                    jError("Opção Inválida, Por favor insira novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    private void createRecipe() {

        AlimentoReceita newreceipss = new AlimentoReceita();
        {
            newreceipss.setNome(JOptionPane.showInputDialog("Insira o nome do novo alimento"));
            newreceipss.setCarboidratos(Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade de carboidratos desse alimento")));
            newreceipss.setProteinas(Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade de de proteinas desse alimento")));
            newreceipss.setGorduras(Double.parseDouble(JOptionPane.showInputDialog("Digite a quantidade de gorduras desse alimento")));
            newreceipss.setCalorias();
            newreceipss.setDataCriacao(LocalDate.now());
            newreceipss.setDataModificacao(LocalDate.now());

            foods.add(newreceipss);
            JOptionPane.showMessageDialog(null, "Alimento criado com sucesso");
        }
    }



}
