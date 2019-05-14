package cafe.jjdev.mall;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class MallApplication {
	
	public static void main(String[] args) {
		System.out.println("=====Tomcat 실행 전=====");
		SpringApplication.run(MallApplication.class, args);
		System.out.println("=====Spring 구동 후=====");
		
		System.out.println("___________                        _________                      __  .__    .__                 \n" + 
				"\\__    ___/__.__.______   ____    /   _____/ ____   _____   _____/  |_|  |__ |__| ____    ____   \n" + 
				"  |    | <   |  |\\____ \\_/ __ \\   \\_____  \\ /  _ \\ /     \\_/ __ \\   __\\  |  \\|  |/    \\  / ___\\  \n" + 
				"  |    |  \\___  ||  |_> >  ___/   /        (  <_> )  Y Y  \\  ___/|  | |   Y  \\  |   |  \\/ /_/  > \n" + 
				"  |____|  / ____||   __/ \\___  > /_______  /\\____/|__|_|  /\\___  >__| |___|  /__|___|  /\\___  /  \n" + 
				"          \\/     |__|        \\/          \\/             \\/     \\/          \\/        \\//_____/   ");
	}
}
