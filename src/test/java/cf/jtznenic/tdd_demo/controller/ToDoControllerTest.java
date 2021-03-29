package cf.jtznenic.tdd_demo.controller;

import cf.jtznenic.tdd_demo.entity.ToDo;
import cf.jtznenic.tdd_demo.service.ToDoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * controller 层测试
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ToDoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ToDoService toDoService;

    @Test
    public void getAllToDos() throws Exception {
        List<ToDo> toDoList = new ArrayList<>();
        toDoList.add(new ToDo(1L, "Eat thrice", true));
        toDoList.add(new ToDo(2L, "Sleep Twice", true));
        when(toDoService.findAll()).thenReturn(toDoList);

        mockMvc.perform(get("/todos")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }

    @Test
    void successfullyCreateAToDo() throws Exception {
        ToDo eatToDo = new ToDo(1L, "Eat thrice", true);
        when(toDoService.save(ArgumentMatchers.any(ToDo.class))).thenReturn(eatToDo);

        ObjectMapper objectMapper = new ObjectMapper();
        String eatToDoJSON = objectMapper.writeValueAsString(eatToDo);

        ResultActions result = mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(eatToDoJSON)
        );

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("Eat thrice"))
                .andExpect(jsonPath("$.completed").value(true));
    }
}
