package com.sbakht.QuranRoots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuranRootsApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuranRootsApplication.class, args);
    }

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//			// read json and write to db
//			ObjectMapper mapper = new ObjectMapper();
//			TypeReference<Surah> typeReference = new TypeReference<Surah>(){};
//			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/test.json");
//			try {
//				Surah users = mapper.readValue(inputStream,typeReference);
//				System.out.println("Users Saved!");
//			} catch (IOException e){
//				System.out.println("Unable to save users: " + e.getMessage());
//			}
//		};
//	}

}
