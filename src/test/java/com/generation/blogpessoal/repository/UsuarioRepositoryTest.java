package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();
		usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@email.com", "1346257"));
		usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "manuela@email.com", "1346257"));
		usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "adriana@email.com", "1346257"));
		usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "paulo@email.com", "1346257"));
	}
	
	@Test
	@DisplayName("Retornar 1 Usuário")
	public void deveRetornarUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com"));
	}
	
	@Test
	@DisplayName("Retornar 3 Usuários")
	public void deveRetornarTresUsuarios() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
			assertEquals(3,listaDeUsuarios.size());
			assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
			assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
			assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
	}
}
