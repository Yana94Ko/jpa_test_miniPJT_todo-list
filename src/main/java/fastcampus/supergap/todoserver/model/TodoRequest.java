package fastcampus.supergap.todoserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {
    //변경이 필요한 경우의 수를 가진 값만 설정
    private String title;
    private Long order;
    private Boolean completed;
}
