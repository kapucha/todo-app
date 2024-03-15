package com.capgemini.training.todoapp.task.dataaccess.repo;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.training.todoapp.task.dataaccess.entity.TaskItemEntity;

public interface TaskItemRepository extends JpaRepository<TaskItemEntity, Long>{
	
	@Query("select item from TaskItemEntity item where item.deadline < :deadline")
	List<TaskItemEntity> findByDeadlineBefore (@Param ("deadline") Instant deadline);
	
//	@Query("select item from TaskItemEntity item where item.completed  = 0")
//	List<TaskItemEntity> findNotCompleted();
	
	List<TaskItemEntity> findAllByCompleted(boolean completed);
	
	//@Query("select item from TaskItemEntity item where now item.completed and item.deadline > 0 now()")
	@Query("select item from TaskItemEntity item where not item.completed")
	List<TaskItemEntity> findUncompletedWithDeadlineExceeded ();
	
	//Task 2.2) Find TaskItems with deadline between - use @Query 
	@Query("select item from TaskItemEntity item where item.deadline between :date1 and :date2")
	List<TaskItemEntity> findWithDeadlineBetween (@Param ("date1") Instant date1, @Param ("date2") Instant date2);

}
