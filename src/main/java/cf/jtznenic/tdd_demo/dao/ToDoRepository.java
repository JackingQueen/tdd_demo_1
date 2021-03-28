package cf.jtznenic.tdd_demo.dao;

import cf.jtznenic.tdd_demo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository("toDoRepository")
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
