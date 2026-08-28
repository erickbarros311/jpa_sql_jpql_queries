package com.devsuperior.uri2737.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2737.entities.Lawyer;
import com.devsuperior.uri2737.projections.LawyerMinProjection;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
	
	@Query(nativeQuery = true, value = "(select  name, customers_number AS customersNumber "
			+ "from lawyers "
			+ "where customers_number = ( "
			+ "	select MAX(customers_number) "
			+ "	from lawyers "
			+ "))"
			+ "UNION ALL "
			+ "(select  name, customers_number "
			+ "from lawyers "
			+ "where customers_number = ( "
			+ "	select MIN(customers_number) "
			+ "	from lawyers "
			+ ")) "
			+ "UNION ALL "
			+ "\n"
			+ "(SELECT 'Average', ROUND(AVG(customers_number), 0)  "
			+ "from lawyers)")
	List<LawyerMinProjection> search1();
	
	

}
