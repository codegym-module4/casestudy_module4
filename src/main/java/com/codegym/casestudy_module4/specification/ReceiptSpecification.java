package com.codegym.casestudy_module4.specification;

import com.codegym.casestudy_module4.entity.Receipt;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReceiptSpecification {
    public static Specification<Receipt> searchWithFilters(Map<String, String> search) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
            // Chuyển đổi sang LocalDate
            search.forEach((key, value) -> {
                if (value != null && !value.isEmpty()) {
                    switch (key) {
                        case "created_from":
                            LocalDate dateFrom = LocalDate.parse(value, formatterDate);
                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), dateFrom));
                            break;
                        case "created_to":
                            LocalDate dateEnd = LocalDate.parse(value, formatterDate);
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), dateEnd));
                            break;
                        case "hour_from":
                            LocalDate hourFrom = LocalDate.parse(value, formatterTime);
                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), hourFrom));
                            break;
                        case "hour_to":
                            LocalDate hourTo = LocalDate.parse(value, formatterTime);
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), hourTo));
                            break;
                        case "receipt_type":
                            predicates.add(criteriaBuilder.equal(root.get("receiptType"), value));
                            break;
                        default:
                            break;
                    }
                }
            });

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
