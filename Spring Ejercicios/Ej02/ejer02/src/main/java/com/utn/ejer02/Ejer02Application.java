package com.utn.ejer02;

import com.utn.ejer02.entities.Domicilio;
import com.utn.ejer02.entities.Persona;
import com.utn.ejer02.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ejer02Application {

    @Autowired
    PersonaRepository personaRepository;

    public static void main(String[] args) {
        SpringApplication.run(Ejer02Application.class, args);
    }

    @Bean
    CommandLineRunner init(PersonaRepository personaRepo) {
        return args -> {
            System.out.println("-----------------ESTOY FUNCIONANDO---------");

		/*
		El método builder() se genera automáticamente por Lombok
		y te permite crear una instancia de Persona.Builder.
		Luego, puedes encadenar llamadas a los métodos
		setters generados automáticamente para establecer los
		valores de los atributos de la clase.
		Finalmente, build() crea la instancia
		de la clase Persona con los valores proporcionados.
		*/
            Persona persona = Persona.builder()
                    .nombre("Juan")
                    .apellido("Pérez")
                    .edad(30)
                    .build();

            Domicilio domicilio = Domicilio.builder()
                    .calle("Suipacha")
                    .numero(30)
                    .build();

            // Asocio el domicilio
            persona.setDomicilio(domicilio);

            // Guardar el objeto Persona en la base de datos

            personaRepository.save(persona);

            // Recuperar el objeto Persona desde la base de datos
            Persona personaRecuperada = personaRepository.findById(persona.getId()).orElse(null);
            if (personaRecuperada != null) {
                System.out.println("Nombre: " + personaRecuperada.getNombre());
                System.out.println("Apellido: " + personaRecuperada.getApellido());
                System.out.println("Edad: " + personaRecuperada.getEdad());
                System.out.println("Calle : " + personaRecuperada.getDomicilio().getCalle());
                System.out.println("Número :" + personaRecuperada.getDomicilio().getNumero());
            }

        };

    }

}
