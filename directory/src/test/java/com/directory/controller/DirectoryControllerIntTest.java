package com.directory.controller;

import com.directory.domain.Directory;
import com.directory.mapper.DirectoryMapper;
import com.directory.service.DirectoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DirectoryController.class)
class DirectoryControllerIntTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private DirectoryService service;

    @Test
    void create() throws Exception {
        Directory directory = Directory.builder()
                .name("SomeName")
                .abbreviation("SN")
                .build();

        MvcResult result = mock.perform(MockMvcRequestBuilders.post("/directory")
                .content(asJsonString(directory))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

//        String resultToString = result.getResponse().getContentAsString();
//        System.out.println(Responses.SUCCESS.getMessage().equals(resultToString)); //expected true
    }

    @Test
    void findAll() throws Exception {
        List<Directory> directoryList = new ArrayList<>();
        directoryList.add(Directory.builder().name("Test1").abbreviation("T1").build());
        directoryList.add(Directory.builder().name("Test2").abbreviation("T2").build());

        Mockito.when(service.findAll()).thenReturn(directoryList);

        MvcResult result = mock.perform(MockMvcRequestBuilders.get("/directory"))
                .andExpect(status().isOk())
                .andReturn();

//        Object[] arr = new Object[2];
//        arr[0] = asJsonString(directoryList.get(0));
//        arr[1] = asJsonString(directoryList.get(1));

//        String arrToString = Arrays.toString(arr).replaceAll(" ", "");
//        String resultToString = result.getResponse().getContentAsString();
//        System.out.println(arrToString.equals(resultToString)); //expected true
    }

    //Этот тест бесполезный. У меня не получилось добиться правильной работы теста
    @Test
    void getOne() throws Exception {
        Directory directory = Directory.builder()
                .name("SomeName")
                .abbreviation("SN")
                .build();

        Mockito.when(service.findById(1L)).thenReturn(directory);

        mock.perform(MockMvcRequestBuilders.get("/directory"))
                .andExpect(status().isOk());
    }

    @Test
    void edit() {
    }

    @Test
    void delete() {
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}