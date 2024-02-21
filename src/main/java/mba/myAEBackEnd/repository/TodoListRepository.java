package mba.myAEBackEnd.repository;

import mba.myAEBackEnd.entity.Task;
import mba.myAEBackEnd.entity.TodoList;
import mba.myAEBackEnd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, String> {

    List<TodoList> findAllByUser(User user);

    @Query("select t From Task t where t.critical = true and t.todoList.user = :user")
    List<Task> findCriticalTask(@Param("user") User user);
}
