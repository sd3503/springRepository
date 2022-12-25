package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    public void findAllBean(){
        String[] beanDefinitionsNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionsName : beanDefinitionsNames) {
            Object bean = ac.getBean(beanDefinitionsName);
            System.out.println("name = " + beanDefinitionsName + "object = "+bean);
        }
    }
    @Test
    @DisplayName("애플리캐이션 빈 출력하기")
    public void findApplicationBean(){
        String[] beanDefinitionsNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionsName : beanDefinitionsNames) {
            // Role ROLE_APPLICATION: 직접 등록한 애플리캐이션 빈
            // ROLE ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionsName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionsName);
                System.out.println("name = " + beanDefinitionsName + "object = "+bean);
            }
        }
    }
}
