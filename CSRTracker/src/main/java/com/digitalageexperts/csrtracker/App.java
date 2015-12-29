package com.digitalageexperts.csrtracker;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.digitalageexperts.csrtracker.excel.ParseCsrExcel;
import com.digitalageexperts.csrtracker.model.Csr;
import com.digitalageexperts.csrtracker.model.CsrRepository;

@SpringBootApplication
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
					InputStream reader = this.getClass().getClassLoader().getResourceAsStream("testCSRFileTOTES.xlsx");
					String ext = "xlsx";
					
					ParseCsrExcel parser = new ParseCsrExcel(reader,ext);
					
					while (parser.HasNextItem()) {
						System.out.println("saving item");
						log.info("Saving Item");
						Csr item = parser.ReadNextItem();
						
						repository.save(item);
					}
					
					
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			};
		}

}
