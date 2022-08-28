package rikkei.academy.view;


import rikkei.academy.config.Config;
import rikkei.academy.controller.CategoryController;
import rikkei.academy.model.category.Category;

import java.util.List;

public class ViewCategory {
    CategoryController categoryController = new CategoryController();
    List<Category> categoryList = categoryController.showCategoryList();

    public void categoryMenu() {
        CategoryController categoryController = new CategoryController();
        List<Category> categoryList = categoryController.showCategoryList();

        System.out.println("CATEGORY MANAGE");
        System.out.println("1. Category product list");
        System.out.println("2. Create category product");
        System.out.println("3. Edit category product");
        System.out.println("4. Delete category product");
        System.out.println("5. Back to menu");
        int choice = Config.scanner().nextInt();
        switch (choice) {
            case 1:
                formShowCategoryList();
                break;
            case 2:
                formCreateCategory();
                break;
            case 3:
                formEditCategory();
                break;
            case 4:
                formDeleteCategory();
                break;
            case 5:
                new ViewHome();
                break;
        }
    }

    private void formDeleteCategory() {
        System.out.println("ENTER ID TO DELETE: ");
        int idCategory = Config.scanner().nextInt();
        if (categoryController.findCategory(idCategory) == null) {
            System.out.println("NOT EXIST");
        } else {
            System.out.println("1 FOR YES/ 2 FOR NO");
            int chooseDelete = Config.scanner().nextInt();
            switch (chooseDelete) {
                case 1:
                    categoryController.deleteCategory(idCategory);
                    formShowCategoryList();
                    categoryController.showCategoryList();
                    break;
                case 2:
                    new ViewHome();
                    break;
            }


        }
        System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewCategory().categoryMenu();
        }
    }

    private void formEditCategory() {
        System.out.println("ENTER ID TO UPDATE: ");
        int idCategory = Config.scanner().nextInt();
        if (categoryController.findCategory(idCategory) == null) {
            System.out.println("NOT EXIST");
        } else {
            Category category = categoryController.findCategory(idCategory);
            System.out.println("OLD CATEGORY NAME: " + category.getName());
            System.out.println("ENTER THE NEW CATEGORY NAME: ");
            String newCategoryName = Config.scanner().nextLine();
            if (newCategoryName.trim().equals("")) {
                newCategoryName = category.getName();
            }
            Category newCategory = new Category(newCategoryName);
            categoryController.editCategory(idCategory, newCategory);
            System.out.println("UPDATE SUCCESS");
            categoryController.showCategoryList();

        }
        System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewCompany().menuCompany();
        }
    }

    private void formCreateCategory() {
        System.out.println("-----CREATE CATEGORY-----");
        while (true) {
            int idCategory;
            if (categoryList.size() == 0) {
                idCategory = 1;
            } else {
                idCategory = categoryList.get(categoryList.size() - 1).getId() + 1;
            }
            System.out.println("INSERT CATEGORY NAME : ");
            String categoryName = Config.scanner().nextLine();
            Category category = new Category(idCategory, categoryName);
            categoryController.createCategory(category);
            System.out.println("Create success");
            categoryController.showCategoryList();
            System.out.println("INPUT ANY KEY TO CONTINUE - INPUT QUIT TO BACK : ");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new ViewCompany().menuCompany();
                break;
            }
        }
    }

    public void formShowCategoryList() {
        System.out.printf("| %-15s | %-15s |%n", " CATEGORY ID ", " CATEGORY NAME ");
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.printf("| %-15d | %-15s |%n", categoryList.get(i).getId(), categoryList.get(i).getName());

        }
    }


}
