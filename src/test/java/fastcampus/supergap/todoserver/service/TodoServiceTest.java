package fastcampus.supergap.todoserver.service;

import fastcampus.supergap.todoserver.model.TodoModel;
import fastcampus.supergap.todoserver.model.TodoRequest;
import fastcampus.supergap.todoserver.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    //mock을 사용하는 이유
    //1) 외부 시스템에 의존하지 않고 자체 테스트를 시행 할 수 있어야 하기 때문
    //2) 실제 데이터베이스를 사용하게 되면, DB에 값이 추가되거나 삭제될 수 있는데 DB에는 민감한 정보들이 포함되어있기도 하고, 서비스에서 사용중인 DB를 건들면 안되기 때문
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;
    @Test
    void add() {
        when(this.todoRepository.save(any(TodoModel.class)))
                .then(AdditionalAnswers.returnsFirstArg());
        TodoRequest expected = new TodoRequest();
        expected.setTitle("Test Title");

        TodoModel actual = this.todoService.add(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
    }

    @Test
    void searchById() {
        TodoModel entity = new TodoModel();
        entity.setId(123L);
        entity.setTitle("TITLE");
        entity.setOrder(0L);
        entity.setCompleted(false);
        Optional<TodoModel> optional = Optional.of(entity);

        given(this.todoRepository.findById(anyLong()))
                .willReturn(optional);

        TodoModel actual = this.todoService.searchById((123L));

        TodoModel expacted = optional.get();

        assertEquals(expacted.getId(), actual.getId());
        assertEquals(expacted.getTitle(), actual.getTitle());
        assertEquals(expacted.getOrder(), actual.getOrder());
        assertEquals(expacted.getCompleted(), actual.getCompleted());

    }

    @Test
    public void searchByIdFailed () {
        given(this.todoRepository.findById(anyLong()))
                .willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> this.todoService.searchById(123L));
    }
}