package cf.jtznenic.tdd_demo.service;

import cf.jtznenic.tdd_demo.dao.ToDoRepository;
import cf.jtznenic.tdd_demo.entity.ToDo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 服务层测试
 */
@SpringBootTest
public class ToDoServiceTest2 {

    // version2
    @Autowired
    private ToDoRepository toDoRepository;

    @AfterEach
    void tearDown() {
        toDoRepository.deleteAll();
    }

    @Test
    // version2
    void getAllToDos() {
        ToDo todoSample = new ToDo("Todo Sample 1",true);
        toDoRepository.save(todoSample);
        ToDoService toDoService = new ToDoService(toDoRepository);

        ToDo firstResult = toDoService.findAll().get(0);

        assertEquals(todoSample.getText(), firstResult.getText());
        assertEquals(todoSample.isCompleted(), firstResult.isCompleted());
        assertEquals(todoSample.getId(), firstResult.getId());
    }

    @Test
    void saveAToDo() {
        ToDoService toDoService = new ToDoService(toDoRepository);
        ToDo todoSample = new ToDo("Todo Sample 1",true);

        toDoService.save(todoSample);

        assertEquals(1.0, toDoRepository.count());
    }
}
