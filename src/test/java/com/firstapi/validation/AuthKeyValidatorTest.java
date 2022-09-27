package com.firstapi.validation;

import com.firstapi.entity.AuthKey;
import com.firstapi.exception.KeyIsNotValidException;
import com.firstapi.repository.AuthKeyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthKeyValidatorTest {
    @Mock
    AuthKeyRepository authKeyRepository;
    @Test
    public void validate(){
        UUID keyId = UUID.randomUUID();
        AuthKey authKey = new AuthKey(keyId, 1664184721000L);
        when(authKeyRepository.findById(keyId)).thenReturn(Optional.ofNullable(authKey));
        AuthKey actualAuthKey = authKeyRepository.findById(keyId).orElseThrow( () -> new KeyIsNotValidException("No key with that id found!! "));
        boolean valid =  actualAuthKey.getExpTime() > 1644184721000L;
        assertEquals(valid, true);

        AuthKey notValidAuthKey = new AuthKey(UUID.randomUUID(), 1664184721000L);
        assertThrows(KeyIsNotValidException.class, () -> authKeyRepository.findById(notValidAuthKey.getId()).orElseThrow( () -> new KeyIsNotValidException("No key with that id found!! ")));

    }
}
