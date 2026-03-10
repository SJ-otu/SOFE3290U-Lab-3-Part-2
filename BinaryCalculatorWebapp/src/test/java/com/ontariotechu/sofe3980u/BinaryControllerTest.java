package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }
	
	// Design 3 cases
    @Test
	    public void postParameter2() throws Exception {
        this.mvc.perform(post("/").param("operand1","0").param("operator","+").param("operand2","1"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1"));
    }
    @Test
	    public void postParameter3() throws Exception {
        this.mvc.perform(post("/").param("operand1","01").param("operator","+").param("operand2","10"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "11"));
    }
    @Test
	    public void postParameter4() throws Exception {
        this.mvc.perform(post("/").param("operand1","101").param("operator","+").param("operand2","010"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "111"));
    }
	
	// Design cases for the other 3 operations
	@Test
	public void orTest1() throws Exception {
    this.mvc.perform(post("/").param("operand1","0").param("operator","|").param("operand2","1"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
		.andExpect(model().attribute("result", "1"));
    }
	@Test
	public void orTest2() throws Exception {
    this.mvc.perform(post("/").param("operand1","0").param("operator","|").param("operand2","0"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
		.andExpect(model().attribute("result", "0"));
    }
	@Test
	public void orTest3() throws Exception {
    this.mvc.perform(post("/").param("operand1","11").param("operator","|").param("operand2","11"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
		.andExpect(model().attribute("result", "11"));
    }
	
	@Test
	public void andTest1() throws Exception {
    this.mvc.perform(post("/").param("operand1","0").param("operator","&").param("operand2","1"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
		.andExpect(model().attribute("result", "0"));
    }
	@Test
	public void andTest2() throws Exception {
    this.mvc.perform(post("/").param("operand1","0").param("operator","&").param("operand2","0"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
		.andExpect(model().attribute("result", "0"));
    }
	@Test
	public void andTest3() throws Exception {
    this.mvc.perform(post("/").param("operand1","11").param("operator","&").param("operand2","11"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
		.andExpect(model().attribute("result", "11"));
    }
	
	@Test
	public void multiplyTest1() throws Exception {
    this.mvc.perform(post("/").param("operand1","0").param("operator","*").param("operand2","1"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
		.andExpect(model().attribute("result", "0"));
    }
	@Test
	public void multiplyTest2() throws Exception {
    this.mvc.perform(post("/").param("operand1","0").param("operator","*").param("operand2","0"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
		.andExpect(model().attribute("result", "0"));
    }
	@Test
	public void multiplyTest3() throws Exception {
    this.mvc.perform(post("/").param("operand1","11").param("operator","*").param("operand2","11"))//.andDo(print())
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
		.andExpect(model().attribute("result", "1001"));
    }
}