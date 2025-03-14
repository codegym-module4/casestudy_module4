package com.codegym.casestudy_module4.repository;

import com.codegym.casestudy_module4.entity.MedicineGroup;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicineGroupRepository extends JpaRepository<MedicineGroup, Long> {
    Page<MedicineGroup> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<MedicineGroup> findAllByCodeContainingIgnoreCase(String code, Pageable pageable);

    // Soft Delete: Cập nhật isDeleted = true
    @Modifying
    @Transactional //giúp chạy lệnh UPDATE để thay đổi trạng thái isDeleted.
    @Query("UPDATE medicine_groups m SET m.isDeleted = true WHERE m.id = :id")
    void softDeleteById(@Param("id") Long id);

    // Khôi phục dữ liệu đã xóa
    @Modifying
    @Transactional
    @Query("UPDATE medicine_groups m SET m.isDeleted = false WHERE m.id = :id")
    void restoreById(@Param("id") Long id);
}