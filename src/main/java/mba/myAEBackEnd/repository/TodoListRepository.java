package mba.myAEBackEnd.repository;

import mba.myAEBackEnd.entity.TodoList;
import mba.myAEBackEnd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList,String> {

    List<TodoList> findAllByUser(User user);
}
