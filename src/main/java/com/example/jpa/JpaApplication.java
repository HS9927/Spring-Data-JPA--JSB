package com.example.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

	@Bean
	CommandLineRunner commandLineRunner (StudentRepository studentRepository)
	{
		return args -> {

			/// student record 1
			Student noname = new Student(
					"No",
					"Name",
					"noname@dontknow.com",
					21
			);

			/// student record 2
			Student johnsena = new Student(
					"John",
					"Sena",
					"johnsena@dontknow.com",
					18
			);

			/// save 1 student record
//			studentRepository.save(noname);

			/// save all student record
			System.out.println("Adding");
			studentRepository.saveAll(List.of(noname, johnsena));

			/// output record counted
			System.out.print("Number of students: ");
			System.out.println(studentRepository.count());

			///
			studentRepository
					.findById(2L)
					.ifPresentOrElse(
							System.out::println,
							() -> System.out.println("Student with ID 2 not found !")
					);

			studentRepository
					.findById(3L)
					.ifPresentOrElse(
							System.out::println,
							() -> System.out.println("Student with ID 3 not found !")
					);

			System.out.println("Select all students");
			List<Student> students = studentRepository.findAll();
			students.forEach(System.out::println);

			System.out.println("Delete student ID 1");
			studentRepository.deleteById(1L);

			System.out.print("Number of students: ");
			System.out.println(studentRepository.count());

			studentRepository.deleteAll();

			System.out.print("Number of students: ");
			System.out.println(studentRepository.count());


		};
	}

}
