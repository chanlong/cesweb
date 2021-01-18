/**
 * <p>Copyright:Copyright(c) 2020</p>
 * <p>Company:Professional</p>
 * <p>Package:cn.cesgroup.cesweb.service.tenant.controller</p>
 * <p>File:TenantControllerTestCase.java</p>
 * <p>类更新历史信息</p>
 * @todo chanlong(陈龙) 创建于 2020年12月14日 下午3:57:52
 */
package cn.cesgroup.cesweb.service.tenant.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

/**
 * <p>描述: </p>
 * <p>Company: Professional</p>
 * @author chanlong(陈龙)
 * @date 2020年12月14日 下午3:57:52
 * @version 1.0.2020
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class TenantControllerTestCase extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mvc;
    
    @Test
    public void getAllTest() throws Exception {

      // EXPECT HTTP STATUS 200
      // BUT GET 401
      this.mvc.perform(get("/")
              .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk());
    }
}
