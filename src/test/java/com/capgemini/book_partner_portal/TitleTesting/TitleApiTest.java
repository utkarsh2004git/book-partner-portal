package com.capgemini.book_partner_portal.TitleTesting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;



@SpringBootTest
@AutoConfigureMockMvc
class TitleApiTest {

    @Autowired
    private MockMvc mockMvc;

   

    @Test
    void testFetchMasterBookList() throws Exception {
        mockMvc.perform(get("/titles"))
        .andDo(print())
        .andExpect(status().isOk())
        ;       
    }

    @Test
    void testFindByTitle() throws Exception{
        mockMvc.perform(get("/titles/search/findByTitle")
                .param("title", "Marvel") 
                .accept("application/hal+json")) 
                .andDo(print()) 
                // checking status first
                .andExpect(status().isOk()) 
                
                // here if status is ok it will return hal+json
                .andExpect(content().contentType("application/hal+json"));
    }

    @Test
    void testFindByType() throws Exception{
         mockMvc.perform(get("/titles/search/findByType")
                .param("type", "popular_comp") 
                .accept("application/hal+json")) 
                .andDo(print()) 
                // checking status first
                .andExpect(status().isOk()) 
                
                // here if status is ok it will return hal+json
                .andExpect(content().contentType("application/hal+json"));
    }

}