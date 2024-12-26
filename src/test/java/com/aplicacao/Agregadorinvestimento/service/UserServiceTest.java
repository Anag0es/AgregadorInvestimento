package com.aplicacao.Agregadorinvestimento.service;

import com.aplicacao.Agregadorinvestimento.dto.CreateUserDTO;
import com.aplicacao.Agregadorinvestimento.entity.User;
import com.aplicacao.Agregadorinvestimento.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Padrão - triple A (Arrange, Act, Assert)
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    // Arrange -> Preparação
    // Act -> Ação
    // Assert -> Verificação

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    // ArgumentCaptor -> captura argumentos passados para um metodo
    @Captor
    private ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);


    @Captor
    private ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

    @Nested
    class createUser {

        @Test
        @DisplayName("Should create user with success")
        void shouldCreateUser() {

            // Arrange

            var dto = new CreateUserDTO("username", "email", "password");

            var user = new User();
            user.setUsername(dto.username());
            user.setEmail(dto.email());
            user.setPassword(dto.password());

            doReturn(user).when(repository).save(userCaptor.capture());

            // Act
            var result = service.createUser(dto);

            // Assert
            assertNotNull(result);
            assertEquals(result.getUsername(), userCaptor.getValue().getUsername());
            assertEquals(result.getEmail(), userCaptor.getValue().getEmail());
            assertEquals(result.getPassword(), userCaptor.getValue().getPassword());
        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs() {
            // Arrange
            doThrow(new RuntimeException("Erro ao salvar usuário")).when(repository).save(userCaptor.capture());

            // Act
            var dto = new CreateUserDTO("username", "email", "password");

            // Assert
            assertThrows(RuntimeException.class, () -> service.createUser(dto));

        }
    }

    @Nested
    class getUserById {

        @Test
        @DisplayName("Should return user by id when optional is Present")
        void shouldReturnUserByIdWhenOptionalIsPresent() {

           // Arrange
            var dto = new CreateUserDTO("username", "email", "password");
            var user = new User();
            user.setUsername(dto.username());
            user.setEmail(dto.email());
            user.setPassword(dto.password());
            user.setUserId(UUID.randomUUID());

            doReturn(Optional.of(user)).when(repository).findById(uuidCaptor.capture());

            // Act
            var result = service.getUserById(user.getUserId().toString());

            // Assert
            assertTrue(result.isPresent());
            assertEquals(user.getUserId(), uuidCaptor.getValue());
        }

        @Test
        @DisplayName("Should return user by id when optional is empty")
        void shouldReturnUserByIdWhenOptionalIsEmpty() {

           // Arrange
            var dto = new CreateUserDTO("username", "email", "password");
            var user = UUID.randomUUID();

            doReturn(Optional.empty()).when(repository).findById(uuidCaptor.capture());

            // Act
            var result = service.getUserById(user.toString());

            // Assert
            assertTrue(result.isEmpty());
            assertEquals(user, uuidCaptor.getValue());
        }


    }

    @Nested
    class getUsers {

        @Test
        @DisplayName("Should return all users with success")
        void shouldReturnListOfUsers() {

            // Arrange
            var dto = new CreateUserDTO("username", "email", "password");
            var user = new User();
            user.setUsername(dto.username());
            user.setEmail(dto.email());
            user.setPassword(dto.password());
            user.setUserId(UUID.randomUUID());

            doReturn(java.util.List.of(user)).when(repository).findAll();

            // Act
            var result = service.getUsers();

            // Assert
            assertNotNull(result);
            assertEquals(1, result.size());
        }

        @Test
        @DisplayName("Should return empty list when there are no users")
        void shouldReturnEmptyListWhenThereAreNoUsers() {

            // Arrange
            doReturn(java.util.List.of()).when(repository).findAll();

            // Act
            var result = service.getUsers();

            // Assert
            assertNotNull(result);
            assertEquals(0, result.size());
        }
    }

    @Nested
    class deleteUser {

        @Test
        @DisplayName("Should delete user with success")
        void shouldDeleteUser() {

            // Arrange
            doReturn(true).when(repository).existsById(uuidCaptor.capture());
            doNothing().when(repository).deleteById(uuidCaptor.capture());

            // Act
            var userId = UUID.randomUUID();
            service.deleteUser(userId.toString());

            // Assert
            var idList = uuidCaptor.getAllValues();
            assertEquals(userId, idList.get(0));
            assertEquals(userId, idList.get(1));

            verify(repository, times(1)).existsById(idList.get(0));
            verify(repository, times(1)).deleteById(idList.get(1));
        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs() {

            // Arrange
            doReturn(false).when(repository).existsById(uuidCaptor.capture());

            // Act
            var userId = UUID.randomUUID();

            // Assert
            assertThrows(RuntimeException.class, () -> service.deleteUser(userId.toString()));
        }

    }

    @Nested
    class updateUser {

        @Test
        @DisplayName("Should update user with success")
        void shouldUpdateUser() {

            // Arrange
            var dto = new CreateUserDTO("username", "email", "password");
            var user = new User();
            user.setUsername(dto.username());
            user.setEmail(dto.email());
            user.setPassword(dto.password());
            user.setUserId(UUID.randomUUID());

            doReturn(Optional.of(user)).when(repository).findById(uuidCaptor.capture());
            doReturn(user).when(repository).save(userCaptor.capture());

            // Act
            var result = service.updateUser(user.getUserId().toString(), dto);

            // Assert
            assertNotNull(result);
            assertEquals(dto.username(), userCaptor.getValue().getUsername());
            assertEquals(dto.email(), userCaptor.getValue().getEmail());
            assertEquals(dto.password(), userCaptor.getValue().getPassword());
        }

        @Test
        @DisplayName("Should throw exception when user not found")
        void shouldThrowExceptionWhenUserNotFound() {

            // Arrange
            doReturn(Optional.empty()).when(repository).findById(uuidCaptor.capture());

            // Act
            var userId = UUID.randomUUID();
            var dto = new CreateUserDTO("username", "email", "password");

            // Assert
            assertThrows(RuntimeException.class, () -> service.updateUser(userId.toString(), dto));
        }

    }

}