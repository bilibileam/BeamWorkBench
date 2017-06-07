package beam.test.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.beam.aop.TestClass;
import com.beam.archtec.test.AbstractBasicSpringContextTest;

public class TestAop extends AbstractBasicSpringContextTest{

    @Autowired
    public TestClass testClass;
    
    @Test
    public void test(){
        try {
            testClass.test();
        } catch (Exception e) {
        }
    }
    
}
