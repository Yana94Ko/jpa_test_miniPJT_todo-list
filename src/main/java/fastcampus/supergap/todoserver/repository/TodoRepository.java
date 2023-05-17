package fastcampus.supergap.todoserver.repository;

import fastcampus.supergap.todoserver.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository를 상속받은 클래스, 제네릭으로 TodoEntity와 TodoEntity의 id에 해당하는 형식을 받음
@Repository
public interface TodoRepository extends JpaRepository<TodoModel, Long> {

}
