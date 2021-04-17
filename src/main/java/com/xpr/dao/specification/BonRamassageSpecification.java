package com.xpr.dao.specification;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import com.xpr.dto.BonRamassageSearch;
import com.xpr.dto.ColisSearch;
import com.xpr.entities.BonRamassage;
import com.xpr.entities.Colis;


public class BonRamassageSpecification implements Specification<BonRamassage> {
	
	private BonRamassageSearch bonRamassageSearch;
	
	
	public BonRamassageSpecification(BonRamassageSearch bonRamassageSearch) {
		this.bonRamassageSearch = bonRamassageSearch;
	}

	@Override
	public Predicate toPredicate(Root<BonRamassage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		final List<Predicate> predicates = new ArrayList<Predicate>();
		
		predicates.add(cb.equal(root.get("disabled"),false));
		
		
           
		
		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	
}
