package com.digitalageexperts.csrtracker;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import com.digitalageexperts.csrtracker.model.Csr;
import com.digitalageexperts.csrtracker.model.CsrRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@PropertySource("file:/Users/Benjamin Laibson/Documents/CSRTracker_properties/local.properties")
public class App {
	
		private static final Logger log = Logger.getLogger(App.class);

		public static void main(String[] args) {
			SpringApplication.run(App.class);
		}

		@Bean
		public CommandLineRunner demo(CsrRepository repository) {
			return (args) -> {
				System.out.println("starting test");
				
				try
				{
					Iterable<Csr> csrs = repository.findAll();
					ObjectMapper mapper = new ObjectMapper();
					csrs.forEach((c) -> {
					
						System.out.println("Printing Item==============================");
						try {
							System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(c));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
					});
					
					
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			};
		}

}
