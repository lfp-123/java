package com.newland.boss.cib.crmb.web.common;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;

@WebAppConfiguration("src/test/java") // 集成Web应用上下文
public abstract class AbstractControllerTestNGTest extends AbstractTestNGTest {

	/**
	 * MVC mock
	 */
	protected MockMvc mockMvc;
	
	/**
	 * Gets the tested controller.
	 * 
	 * @return the controller that is tested
	 */
	protected abstract Object getController();
	
	/**
	 * Setups the tested controller in MVC Mock environment.
	 */
	@BeforeClass(alwaysRun = true)
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.getController()).build();
	}
	
}
