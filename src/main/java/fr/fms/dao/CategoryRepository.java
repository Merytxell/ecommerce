package fr.fms.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import fr.fms.entities.Category;

public interface CategoryRepository  extends JpaRepository <Category, Long>{
	
	
@Query ("select c.name from Category c  ORDER BY c.name ASC")
public List <String> findAllCategoryNamesAsc();
@Query ("select c.name from Category c  ORDER BY c.name DESC")
public List <String> findAllCategoryNamesDesc();
}


