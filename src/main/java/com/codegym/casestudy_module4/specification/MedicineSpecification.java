package com.codegym.casestudy_module4.specification;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.MedicineGroup;
import com.codegym.casestudy_module4.entity.MedicineGroup_;
import com.codegym.casestudy_module4.entity.Medicine_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class MedicineSpecification {

//    @PersistenceContext
//    public static EntityManager entityManager;

    // Filter: "="
    public static Specification<Medicine> nameLike(String name) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(Medicine_.NAME), "%" + name + "%");
    }

    public static Specification<Medicine> codeLike(String code) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(Medicine_.CODE), "%" + code + "%");
    }

    public static Specification<Medicine> groupLike(String group) {
        return (root, query, builder) -> {
            Join<Medicine, MedicineGroup> medicineGroup = root.join("medicineGroup");
            return builder.like(medicineGroup.get("name"), "%" + group + "%");
        };
    }

    public static Specification<Medicine> ingredientsLike(String ingredients) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(Medicine_.ACTIVE_INGREDIENT), "%" + ingredients + "%");
    }

    public static Specification<Medicine> importPriceEqual(int importPrice) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(Medicine_.IMPORT_PRICE), importPrice);
    }

    public static Specification<Medicine> retailPriceEqual(int retailPrice) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(Medicine_.IMPORT_PRICE), retailPrice);
    }

    public static Specification<Medicine> wholesalePriceEqual(int wholesalePrice) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(Medicine_.IMPORT_PRICE), wholesalePrice);
    }


    // Filter: "> || ="
    public static Specification<Medicine> importPriceMin(int importPrice) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.ge(root.get(Medicine_.IMPORT_PRICE), importPrice);
    }

    public static Specification<Medicine> retailPriceMin(int retailPrice) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.ge(root.get(Medicine_.IMPORT_PRICE), retailPrice);
    }

    public static Specification<Medicine> wholesalePriceMin(int wholesalePrice) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.ge(root.get(Medicine_.IMPORT_PRICE), wholesalePrice);
    }

    // Filter: "< || ="

    public static Specification<Medicine> importPriceMax(int importPrice) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.le(root.get(Medicine_.IMPORT_PRICE), importPrice);
    }

    public static Specification<Medicine> retailPriceMax(int retailPrice) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.le(root.get(Medicine_.IMPORT_PRICE), retailPrice);
    }

    public static Specification<Medicine> wholesalePriceMax(int wholesalePrice) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.le(root.get(Medicine_.IMPORT_PRICE), wholesalePrice);
    }

}
