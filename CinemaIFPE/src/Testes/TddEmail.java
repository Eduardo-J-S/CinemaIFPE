package Testes;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;

import Core.EnviarEmail;
import Core.Funcionario;
import Database.DatabaseFuncionario;

class TddEmail {

	@Test
	void emailVazio() {
			DatabaseFuncionario databaseFunc = new DatabaseFuncionario();
			assertThrows(MessagingException.class, () -> EnviarEmail.transfer_to_email(""));
			
	}

	@Test
	void emailInvalido() {
			DatabaseFuncionario databaseFunc = new DatabaseFuncionario();
			assertThrows(MessagingException.class, () -> EnviarEmail.transfer_to_email("dfldalçfma"));
	}
}
