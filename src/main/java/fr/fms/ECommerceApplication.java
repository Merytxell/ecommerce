package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner{
	
	public static void displayArticle(ArticleRepository articleRepository) { 		
		System.out.println(Article.centerString(COLUMN_ID) + Article.centerString(COLUMN_BRANDOFARTICLE) + Article.centerString(COLUMN_DESCRIPTION) + Article.centerString(COLUMN_PRICE));
		//articleRepository.findAll().forEach(System.out::println);
		List<Article> listArticle = articleRepository.findAll();
		for (Article article : listArticle) {
			//System.out.println(article.centerString());
		}
				
	}

	//public static final String TEXT_BLUE = "\u001B[36m";
	//public static final String TEXT_RESET = "\u001B[0m";	
	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_BRANDOFARTICLE= "BRAND";
	private static final String COLUMN_DESCRIPTION = "DESRIPTION";
	private static final String COLUMN_PRICE = "PRIX";
	//private static final String COLUMN_CATEGORYID = "CATEGORYID";

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run (String...args) throws Exception {

		System.out.println( "Bienvenue dans notre application de gestion d'articles ! vivement la couche web parce que...");
		int choice = 0;
		Scanner scanner = new Scanner(System.in);
		displayMenu();
		while(choice != 12) {
			
			choice = scanner.nextInt();
			scanner.nextLine();
			switch(choice) {
			case 1 : displayArticle(articleRepository);
			break;					
			case 2 : 
				break;	
				
			case 3 : 
				System.out.println("Saisissez la marque de l'article à ajouter :");
				String brand = scanner.nextLine();
				System.out.println("Description :");
				String description = scanner.nextLine();
				System.out.println("Prix : ");
				double price = scanner.nextDouble();
				System.out.println("Insérer l'id category correspondant : 1 : smartphone, 2 : tablet, 3: pc, 4 : keyboard,  5 : screen, 6 : print, 7: console");
				int categoryId = scanner.nextInt();
				Category category = categoryRepository.findById((long) categoryId).orElse(null);
				    if (category == null) {
				        System.out.println("Catégorie introuvable !");
				    } else {
				Article article = new Article (brand,description, price,category);
				articleRepository.save(article);
				    }
				break;
				
			case 4 : 
				System.out.println("Saisissez l'ID de l'article que vous souhaitez afficher");
				int articleId = scanner.nextInt();
				Optional<Article> articleToFind = articleRepository.findById((long) articleId);
				if (articleToFind.isPresent()) {
					//System.out.println(articleToFind);
				}else {
					System.out.println("article introuvable");
				}
				break;						
			case 5 : 
				System.out.println("Saisissez l'ID de l'article que vous souhaitez supprimer");
				int id = scanner.nextInt();
				Optional<Article> articleToDelete = articleRepository.findById((long) id);
				if ( articleToDelete.isPresent()) {
					articleRepository.deleteById((long) id);
					System.out.println("l'article a été supprimé");
				}else {
					System.out.println("l'article n'existe pas");
				}
				break;
				
			case 6 : 
				System.out.println("Saisissez l'ID de l'article que vous souhaitez modifier");
				int idArticle = scanner.nextInt();
				Optional <Article> articleToUpdate = articleRepository.findById((long)idArticle);
				if (articleToUpdate.isPresent() ) {
					System.out.println("Saisissez la marque de l'article à modifier");
					String newBrand = scanner.next();
					System.out.println("Description :");
					String newDescription = scanner.next();
					System.out.println("prix : ");
					double newPrice = scanner.nextDouble();
					
					Article article = articleToUpdate.get();
					article.setBrand(newBrand);
					article.setDescription(newDescription);
					article.setPrice(newPrice);
					articleRepository.save(article);
					System.out.println("l'article a été modifié");
					
				}else {
					System.out.println("l'article n'existe pas");
				}
				break;
				
			case 7 : 
				System.out.println("Saisissez le nom de la catégorie :");
				String categoryName = scanner.next();
				Category newCategory = new Category((String) categoryName);
				categoryRepository.save(newCategory);
				break;
				
			case 8 : 
				System.out.println("Saisissez l'ID de la catégorie que vous souhaitez afficher");
				int idCategory = scanner.nextInt();
				Optional<Category> categoryToFind = categoryRepository.findById((long) idCategory);
				if (categoryToFind.isPresent()) {
					 Category categoryFind = categoryToFind.get();
					System.out.println("ID: " + categoryFind.getId() + ", Nom: " + categoryFind.getName());
				}else {
					System.out.println("category introuvable");
				}
				break;
				
			case 9 : 
				System.out.println("Saisissez l'ID de la catégorie que vous souhaitez modifier");
				int newIdCategory = scanner.nextInt();
				Optional <Category> categoryToUpdate = categoryRepository.findById((long)newIdCategory);
				if (categoryToUpdate.isPresent() ) {
					System.out.println("Saisissez le nom de la catégorie à modifier");
					String newCategoryName = scanner.next();
					Category category1 = categoryToUpdate.get();
					category1.setName(newCategoryName);
					categoryRepository.save(category1);
					System.out.println("la catégorie a été modifiée");
				}else {
					System.out.println("l'id de la catégorie n'existe pas");
				}
				break;
				
			case 10 : 
				System.out.println("Saisissez l'ID de la catégorie que vous souhaitez supprimer");
				int idCategoryDeleted = scanner.nextInt();
				Optional<Category> categoryToDelete = categoryRepository.findById((long) idCategoryDeleted);
				if ( categoryToDelete.isPresent()) {
					categoryRepository.deleteById((long) idCategoryDeleted);
					System.out.println("la catégorie a été supprimée");
				}else {
					System.out.println("la catégorie n'existe pas");
				}
				break;
				
			case 11 : 
				System.out.println("Saisissez l'id de la catégorie que vous souhaitez afficher");
				int idCategoryDisplay = scanner.nextInt();
				Optional<Category> oneCategoryToFind = categoryRepository.findById((long) idCategoryDisplay);
				if (oneCategoryToFind.isPresent()) {
					Category oneCategory = oneCategoryToFind.get();
					List<Article> articlesInCategory = oneCategory.getArticles();
					if (articlesInCategory.isEmpty()) {
						System.out.println("aucun article n'a été trouvé dans cette catégorie");
				}else {
					System.out.println("Articles de la catégorie" + oneCategory.getName() + " : ");
					for (Article article : articlesInCategory) {
						//System.out.println(article.toString());
					}
				}
				}else {
					System.out.println("aucun Id de  la catégorie correspondant");
				}
				break;
				
			case 12 : System.out.println("à bientôt dans notre boutique :)");
			scanner.close();
			break;
							
			default : System.out.println("veuillez saisir une valeur entre 1 et 12");
			}
		}
	}
	public static void displayMenu() {
		System.out.println("1: Afficher tous les articles sans pagination \n");
		System.out.println("2: Afficher tous les articles avec pagination \n" );
		System.out.println("********************************************** \n");
		System.out.println("3: Ajouter un article \n");
		System.out.println("4: Afficher un article \n");
		System.out.println("5: Supprimer un article \n"); 
		System.out.println("6: Modifier un article \n");
		System.out.println("**********************************************\n");
		System.out.println("7: Ajouter une categorie \n");
		System.out.println("8: Afficher une categorie \n"); 
		System.out.println("9: Mettre à jour une categorie \n"); 
		System.out.println("10:supprimer une categorie \n"); 
		System.out.println("11: Afficher tous les articles d'une categorie \n");	
		System.out.println("*********************************************** \n");
		System.out.println("12: sortir du programme \n");
	}
	
	

}
















