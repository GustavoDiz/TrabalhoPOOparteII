package view;

import model.*;
import model.AlimentoReceita;
import model.AlimentoReceitaDAO;
import model.Preferencia;
import model.PreferenciaDAO;

import javax.swing.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static utils.Utils.jConfirmation;
import static utils.Utils.jError;
import static view.JMenu.userlogged;


public class JmenuFoods {

    AlimentoReceitaDAO foods = new AlimentoReceitaDAO();
    TipoDietaDAO diets = new TipoDietaDAO();

    PreferenciaDAO preferences = new PreferenciaDAO();
    RegistroDietaDAO diets2 = new RegistroDietaDAO();

    PreferenciaDAO preferenciaDAO = new PreferenciaDAO();
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
                    jRegisterDiet();
                    break;
                case 3:
                    jMenuAddMeal();
                    break;
                case 4:
                    jMenusRecipe();
                    break;
                case 5:
                    jPreferences();
                    break;
                default:
                    jError("Opção Inválida, Por favor insira novamente.");
                    break;
            }
        } while (op != 0);
    }

    private void jRegisterDiet(){
        int op;
        RegistroDietaDAO registrodietaacc = new RegistroDietaDAO();
        AvaliacaoFisicaDAO physical =  new AvaliacaoFisicaDAO();
        TipoDietaDAO dietasearch = new TipoDietaDAO();
        TipoDieta dietaset = new TipoDieta();

        StringBuilder txt = new StringBuilder();
        txt.append("Menu Dieta, Selecione a opção");
        txt.append("\n 1 - Ver Dieta Atual");
        txt.append("\n 2 - Adicionar Dieta");
        txt.append("\n 3 - Apagar Dieta");
        txt.append("\n 0 - Sair");
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog(txt));
            switch (op){
                case 1:
                    RegistroDieta currentDiet = registrodietaacc.getRegisterByUser(userlogged.getId());
                    if (currentDiet == null){
                        jError("Você não possui nenhuma dieta.");
                    }else{
                        jConfirmation(currentDiet.toString());
                    }
                    break;
                case 2:
                    if (registrodietaacc.getRegisterByUser(userlogged.getId()) == null){
                        long opc = Long.parseLong(JOptionPane.showInputDialog("Qual o ID do tipo da dieta que você quer?"));

                        dietaset = dietasearch.getDietByID(opc);
                        System.out.println(dietaset.toString());
                        if(dietaset != null){
                            RegistroDietaDAO addDieta = new RegistroDietaDAO();
                            RegistroDieta newDiet = new RegistroDieta();

                            newDiet.setUserID(userlogged.getId());
                            newDiet.setPhysicalAssessmentID(physical.getPhysicalAssessmentID(userlogged.getId()));
                            newDiet.setTipoDietaid(dietaset.getId());
                            newDiet.setGoal(Integer.parseInt(JOptionPane.showInputDialog("Qual seu objetivo: \n 1 - Manter o Peso \n 2 - Perder Peso \n 3 - Ganhar Peso")));
                            double calories = (4 * dietaset.getCarboidrato()) + (4 * dietaset.getProteina()) + (9 * dietaset.getGordura());
                            newDiet.setCalories(calories);
                            newDiet.setnMeals(Integer.parseInt(JOptionPane.showInputDialog("Quantidade de Refeições")));
                            newDiet.setDataCriacao(LocalDate.now());
                            newDiet.setDataModificacao(LocalDate.now());

                            addDieta.add(newDiet);
                        }
                    }else{
                        jError("Ja adicioda a Dieta, se tiver alguma alteração Atualize as Informações");
                    }
                    break;
                case 3:
                    if(!(registrodietaacc.getRegisterByUser(userlogged.getId()) == null)){
                        if(dietaset != null){
                            RegistroDietaDAO pegarDieta = new RegistroDietaDAO();
                            RegistroDieta getDiet = new RegistroDieta();

                            getDiet = pegarDieta.getRegisterByUser(userlogged.getId());

                            pegarDieta.delete(getDiet);
                        }
                    }else{
                        jError("Você não tem possui dieta!!");

                    }
                    break;
                case 0:
                    break;
                default:
                    jError("Opção Inválida,Insira novamente");
                    break;
            }
        }while (op != 0);
    }

    private void jMenuAddMeal() {
        RegistroDietaDAO registrodietaacc = new RegistroDietaDAO();
        RefeicaoDAO meals = new RefeicaoDAO();
        int op;
        StringBuilder txt = new StringBuilder();
        txt.append("Menu Refeição,Selecione a opção");
        txt.append("\n 1 - Ver Refeições Criadas");
        txt.append("\n 2 - Criar Refeição");
        txt.append("\n 3 - Adicionar Alimentos a Refeição");
        txt.append("\n 4 - Deletar Refeição");
        txt.append("\n 0 - Sair");

        RegistroDieta hasDiet = registrodietaacc.getRegisterByUser(userlogged.getId());
        do {
            if (hasDiet == null){
                jError("Você não possui nenhuma dieta,Por favor insira antes de criar as Refeições");
                break;
            }
            op = Integer.parseInt(JOptionPane.showInputDialog(txt));
            switch (op){
                case 1:
                    StringBuilder info = new StringBuilder();
                    ArrayList<Refeicao> myMeals = meals.myList(userlogged.getId());

                    for (Refeicao refeicao : myMeals) {
                        if (refeicao != null) {
                            info.append("\n ").append(refeicao.toString());
                            System.out.println(refeicao);
                        }
                    }

                    jConfirmation(info.toString());
                    break;
                case 2:
                    Refeicao newMeal = new Refeicao();
                    RefeicaoDAO addMeal = new RefeicaoDAO();
                    diets2.getRegisterByUser(userlogged.getId());

                    RegistroDieta userDiet = diets2.getRegisterByUser(userlogged.getId());
                    int mealsqt = userDiet.getnMeals();
                    double caloriesPerMeal = userDiet.getCalories()/mealsqt;
                    newMeal.setNome(JOptionPane.showInputDialog("Digite o Nome da Refeição"));
                    newMeal.setCodigo_user(userlogged.getId());
                    newMeal.setCodigo_dietType(userDiet.getTipoDietaid());
                    newMeal.setCalorias(caloriesPerMeal);
                    newMeal.setCarboidrato(caloriesPerMeal*0.4);
                    newMeal.setGordura(caloriesPerMeal*0.3);
                    newMeal.setProteina(caloriesPerMeal*0.3);
                    newMeal.setDataCriacao(LocalDate.now());
                    newMeal.setDataModificacao(LocalDate.now());
                    addMeal.add(newMeal);
                    jConfirmation("Refeição Adicionada com Sucesso");
                    break;
                 case 3:
                     RefeicaoDAO found = new RefeicaoDAO();
                     long idpesquisa = Long.parseLong((JOptionPane.showInputDialog("Qual o ID da Refeição")));
                     Refeicao foundMeal = found.getMealByID(idpesquisa);
                     System.out.println(foundMeal);
                     jMenuAddMealFood(foundMeal);
                     break;
                case 4:
                    StringBuilder delete = new StringBuilder();
                    delete.append("Informe o ID do alimento a ser deletado");
                    int index = Integer.parseInt(JOptionPane.showInputDialog(delete));
                    meals.deleteMeal(index);
                    if(meals.deleteMeal(index)){
                        jConfirmation("Refeição Deletada Com Sucesso");
                    }else{
                        jError("Refeição não Encontrada!Por Favor Insira Novamente");
                    }
                    break;
                default:
                    jError("Opção Inválida, Por favor insira novamente.");
                    break;
            }
        }while (op != 0);
    }

    private void jPreferences() {
        int op;
        String txt = "O que deseja? " +
                "\n 1 - Criar Preferencias " +
                "\n 2 - Ver suas Preferencias"+
                "\n 3 - Excluir suas Preferencias"+
                "\n 0 - Sair";
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog(txt));
            switch (op) {
                case 0:
                    break;
                case 1:
                    jCreatePreference();
                    break;
                case 2:
                    jMyPreferences();
                    break;
                case 3:
                    String id = JOptionPane.showInputDialog("Id do Alimento");
                    Preferencia delete = new Preferencia();
                    delete.setId(Long.parseLong(id));
                    preferenciaDAO.delete(delete);
                    break;
                default:
                    jError("Opção Inválida");
                    break;
            }
        }while (op != 0);
    }

    private void jMenuAddMealFood(Refeicao refeicao) {
        int index;
        double calories = refeicao.getCalorias();
        double fat = refeicao.getGordura();
        double carb = refeicao.getCarboidrato();
        double protein = refeicao.getProteina();
        AlimentoReceita[] listFoods = preferences.getFoodsPreferencesByUser(userlogged);
        if (listFoods[0] == null){
            listFoods = foods.getAlimentore();
        }
        AlimentoReceita[] selectedFoods = mealFoods.getFoodsByMeal(refeicao);
        StringBuilder txt = new StringBuilder();
        txt.append("Refeição:  \t" + refeicao.getNome());
        txt.append("\n Calorias Restantes: \t" + calories);
        txt.append("\n Quantidade de Gordura Restantes: \t" + fat);
        txt.append("\n Quantidade de Carboidrato Restantes: \t" + carb);
        txt.append("\n Quantidade de Proteína Restantes: \t" + protein);
        txt.append("\n Digite o ID do alimento a ser adicionado");
        txt.append("\n ### Lista de Alimentos ###");
        for (AlimentoReceita listFood : listFoods) {
            if (listFood != null) {
                txt.append("\n ----------------");
                txt.append("\n Id" + listFood.getId());
                txt.append("\n Nome" + listFood.getNome());
                txt.append("\n Calorias por porção" + listFood.getNome());
                txt.append("\n Gordura por porção" + listFood.getGorduras());
                txt.append("\n Carboidrato por porção" + listFood.getCarboidratos());
                txt.append("\n Proteína por porção" + listFood.getProteinas());
                txt.append("\n ----------------");
            }
        }

        txt.append("\n ### Alimentos Selecionado ###");
        for (AlimentoReceita selectedFood : selectedFoods) {
            if (selectedFood != null) {
                txt.append("\n ----------------");
                txt.append("\n Id" + selectedFood.getId());
                txt.append("\n Nome" + selectedFood.getNome());
                txt.append("\n Calorias por porção" + selectedFood.getNome());
                txt.append("\n Gordura por porção" + selectedFood.getGorduras());
                txt.append("\n Carboidrato por porção" + selectedFood.getCarboidratos());
                txt.append("\n Proteína por porção" + selectedFood.getProteinas());
                txt.append("\n ----------------");
            }
        }
        do {

            index = Integer.parseInt(JOptionPane.showInputDialog(txt));
            if (index == 9){
                break;
            }
            AlimentoReceita foodAdd = foods.getRecipeByIDFood(index);
            if (foodAdd == null){
                jError("Comida não encontrada!Por favor Insira Novamente o ID.");
            }else{
                AlimentoRefeicao newFoodMeal = new AlimentoRefeicao();
                newFoodMeal.setMeal(refeicao);
                newFoodMeal.setFood(foodAdd);
                newFoodMeal.setPortion(Double.parseDouble(JOptionPane.showInputDialog("Quantidade da Porçao")));
                newFoodMeal.setCalories(foodAdd.getCalorias() * newFoodMeal.getPortion());
                newFoodMeal.setFat(foodAdd.getGorduras() * newFoodMeal.getPortion());
                newFoodMeal.setProtein(foodAdd.getProteinas() * newFoodMeal.getPortion());
                newFoodMeal.setDataCriacao(LocalDate.now());
                mealFoods.addMealFood(newFoodMeal);
                refeicao.setCalorias(refeicao.getCalorias() - (foodAdd.getCalorias() * newFoodMeal.getPortion()));
                refeicao.setGordura(refeicao.getGordura() - foodAdd.getGorduras() * newFoodMeal.getPortion());
                refeicao.setCarboidrato(refeicao.getCarboidrato() - foodAdd.getCarboidratos() * newFoodMeal.getPortion());
                refeicao.setProteina(refeicao.getProteina() - foodAdd.getProteinas() * newFoodMeal.getPortion());
            }
        }while (index != 9);

    }

    private void jMyPreferences() {
        ArrayList<Preferencia> myPreferences = preferenciaDAO.myPreferences(userlogged.getId());
        StringBuilder txt = new StringBuilder();
        for (Preferencia p:
                myPreferences) {
            txt.append('\n');
            txt.append(p.toString());


        }
        jConfirmation(String.valueOf(txt));
    }

    private void jCreatePreference() {
        Preferencia newPreferencia = new Preferencia();
        String idFood = JOptionPane.showInputDialog("Id do Alimento");
        AlimentoReceita foodPreferencia = foods.getRecipeByIDFood(Integer.parseInt(idFood));
        newPreferencia.setUserId(userlogged.getId());
        newPreferencia.setFoodId(foodPreferencia.getId());
        newPreferencia.setDataModificacao(LocalDate.now());
        newPreferencia.setDataCriacao(LocalDate.now());
        preferenciaDAO.add(newPreferencia);
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
