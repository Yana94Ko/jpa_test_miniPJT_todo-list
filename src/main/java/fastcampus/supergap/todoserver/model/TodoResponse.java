package fastcampus.supergap.todoserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    //모든 값을 돌려 줄 수 있어햐 하기 때문에 모든 필드 지정
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    //프론트 단에 반환할 url
    private String url;

    //이후에 코드 작성을 조금더 편하게 하기위해 entity를 받는 생성자 하나 더 추가
    public TodoResponse(TodoModel todoModel) {
        this.id = todoModel.getId();
        this.title = todoModel.getTitle();
        this.order = todoModel.getOrder();
        this.completed = todoModel.getCompleted();

        this.url = "http://localhost:8080/" + this.id;
    }
}
