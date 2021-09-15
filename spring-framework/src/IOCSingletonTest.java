import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCSingletonTest {
    public static void main(String[] args) {
        //读取配置文件实例化一个IOC容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        //默认模式 scope="singleton"
        Book book1 = applicationContext.getBean("book", Book.class);
        Book book2 = applicationContext.getBean("book", Book.class);
        System.out.println("singleton:" + (book1 == book2));

        //指定scope="prototype"
        Book2 book3 = applicationContext.getBean("book2", Book2.class);
        Book2 book4 = applicationContext.getBean("book2", Book2.class);
        System.out.println("prototype:" + (book3 == book4));
    }
}
