package com.cycligo.backend.profile.event.race;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by panda on 08/11/2016.
 */

@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class RaceRestControllerTest {

  //  @Autowired
  //  private MockMvc mvc;

    @Test
    public void dumpTest() {

    }

    @Ignore("Add posibility to test rest services")
    public void getActiveRaces() {
    /*    mvc.perform(
                MockMvcRequestBuilders.get("/cycligo/event/active-races")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));*/
    }
}
