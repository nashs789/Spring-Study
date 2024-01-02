package hellospringbasic.core.beanfind;

import hellospringbasic.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String name: beanDefinitionNames) {
            Object bean = ac.getBean(name);
            System.out.println("name = " + name + " object = " + bean);
        }
    }

    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String name: beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(name);

            // BeanDefinition.ROLE_APPLICATION: 직접 등록한 어플리케이션 빈
            // BeanDefinition.ROLE_INFRASTRUCTURE: 스프링 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(name);
                System.out.println("name = " + name + " object = " + bean);
            }
        }
    }
}
