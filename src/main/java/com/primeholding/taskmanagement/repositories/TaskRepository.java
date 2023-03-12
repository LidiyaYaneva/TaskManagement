package com.primeholding.taskmanagement.repositories;

import com.primeholding.taskmanagement.models.entities.ClientEntity;
import com.primeholding.taskmanagement.models.entities.Task;
import com.primeholding.taskmanagement.models.enums.TaskStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Long countByAssignee_IdAndStatus(Long id, TaskStatus taskStatus);

    List<Task> findAllByOrderById(PageRequest pageRequest);

    List<Task> findAllByAssigneeId(Long id);

    List<Task> findAllByTitleContaining(String titleKeyword);

    @Query("SELECT t FROM Task AS t " +
            "WHERE t.status='COMPLETED' AND t.finishDate BETWEEN :startDate AND :endDate " +
            "AND t.assignee IS NOT NULL" )
    List<Task> findAllFinishedPastMonth(@Param("startDate") LocalDate startDate, @Param("endDate")LocalDate endDate);

    List<Task> findAllByStatusAndFinishDateIsNull(TaskStatus taskStatus);

    List<Task> findAllByStatus(TaskStatus taskstatus);

    List<Task> findAllByClient(ClientEntity client);
}
