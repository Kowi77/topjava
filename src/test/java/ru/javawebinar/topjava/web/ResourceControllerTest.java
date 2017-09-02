package ru.javawebinar.topjava.web;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Андрей on 02.09.2017.
 */
public class ResourceControllerTest extends AbstractControllerTest {

    @Test
    @Ignore
    public void testStyle() throws Exception{
            mockMvc.perform(get("/resource/style.css"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}
