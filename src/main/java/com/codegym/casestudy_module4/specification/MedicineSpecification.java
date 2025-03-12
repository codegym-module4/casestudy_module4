package com.codegym.casestudy_module4.specification;

import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.entity.Medicine_;
import org.springframework.data.jpa.domain.Specification;

public class MedicineSpecification {

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
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(Medicine_.MEDICINE_GROUP), "%" + group + "%");
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
