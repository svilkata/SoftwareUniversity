package bg.softuni.intro.ioc;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Dog implements Animal, BeanNameAware, DisposableBean {
    private final boolean superDog;

    public Dog() {
        this(false);
    }

    public Dog(boolean superDog) {
        this.superDog = superDog;
    }

    @Override
    public void makeNoise() {
        if (this.superDog) {
            System.out.println("Super Bark super bark");
        } else {
            System.out.println("Bark bark");
        }
    }

    @PostConstruct
    public void afterInit() {
        System.out.println("Dog is ready to bite");
    }


    @Override
    public void setBeanName(String name) {
        System.out.println("The name of tgis Dog bean is: " + name);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Dog is about to die... Bye!");
    }
}





