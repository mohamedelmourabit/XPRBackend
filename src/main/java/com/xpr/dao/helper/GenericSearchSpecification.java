package com.xpr.dao.helper;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericSearchSpecification<T> implements Specification<T> {

    private final Map<String, Object> filters;
    private final Map<String, List<Object>> securityFilter;

    public GenericSearchSpecification(Map<String, Object> filters, Map<String, List<Object>> securityFilter) {
        this.filters = filters;
        this.securityFilter = securityFilter;
    }

    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filters != null) {
            for (String column : filters.keySet()) {
                Expression<String> expression = buildExpression(column, root);
                Predicate predicate = criteriaBuilder.equal(criteriaBuilder.upper(expression), filters.get(column));
                predicates.add(predicate);
            }
        }

        if (this.securityFilter != null) {
            for (String column : securityFilter.keySet()) {
                Expression<String> expression = buildExpression(column, root);
                Predicate predicate = expression.in(securityFilter.get(column));
                predicates.add(predicate);
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Expression<String> buildExpression(String column, Path<T> path) {
        if (!column.contains("."))
            return path.get(column);

        String[] parts = column.split("\\.");
        int i = 0;
        for (; i < parts.length - 1; i++) {
            path = path.get(parts[i]);
        }

        return path.get(parts[i]);
    }
}

