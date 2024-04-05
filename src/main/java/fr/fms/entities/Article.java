package fr.fms.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import fr.fms.dao.ArticleRepository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


	@Entity
	public class Article implements Serializable {
		
	public static final int MAX_STRING_LENGTH = 20;
		
		private static final long serialVersionUID = 1L;
		
		@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		private String brand;
		private String description;
		private double price;
		
		@ManyToOne
		private Category category;
		
		public Article() {
			
		}
		
		public Article (String brand, String description, double price, Category category) {
			this.brand=brand;
			this.description=description;
			this.price=price;
			this.category=category;
			
		}
		
		public Article (String brand, String description, double price) {
			this.brand=brand;
			this.description=description;
			this.price=price;
			
		}
		public Article (Long id, String brand, String description, double price) {
			this.id=id;
			this.brand=brand;
			this.description = description;
			this.price=price;
		}
		
		
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public String centerString() {
			String idStr = centerString (String.valueOf(id));
			String brandStr = centerString (brand);
			String descriptionStr = centerString (description);
			String priceStr = centerString (String.valueOf(price));
			String categoryStr = centerString (category != null ? category.getName(): " ");
			
			return idStr + brandStr + descriptionStr +  priceStr;
		}
			
		public static String centerString(String str) {
			if(str.length() >= MAX_STRING_LENGTH) return str;
			String dest = "                    ";
			int deb = (MAX_STRING_LENGTH - str.length())/2 ;
			String data = new StringBuilder(dest).replace( deb, deb + str.length(), str ).toString();
			return data;
		}
		
		public String toString() {
			return "Id de l'article " + this.id+ "  " + this.brand + " " +  this.description + " " + this.price;
		}
	}
	




