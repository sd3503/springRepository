package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonBeanFind {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        System.out.println("SingletonBean1 = " + singletonBean1);
        System.out.println("SingletonBean2 = " + singletonBean2);

        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

}
