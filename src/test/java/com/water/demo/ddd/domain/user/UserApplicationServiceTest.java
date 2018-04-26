package com.water.demo.ddd.domain.user;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.water.demo.ddd.domain.user.model.User;
import com.water.demo.ddd.domain.user.repository.UserRepository;
import com.water.demo.ddd.domain.user.service.RegisterService;
import com.water.demo.ddd.exception.ResourceNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserApplicationServiceTest {
    @InjectMocks
    private UserApplicationService applicationService = new UserApplicationService();

    @Mock
    private RegisterService registerService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldApplicationServiceDelegateToRegisterServiceAndUserRepositoryToRegisterUser() {
        String policyNumber = "123456789";
        String email = "sqlin@gmail.com";
        User user = new User(email, policyNumber);
        when(registerService.register(email, policyNumber)).thenReturn(user);

        String uuid = applicationService.register(email, policyNumber);

        verify(registerService).register(email, policyNumber);
        verify(userRepository).save(user);
        assertThat(uuid, is(user.getUuid()));
    }

    @Test
    public void shouldInitUserPasswordGivenTheUuidAndPasswordAreCorrect() {
        String uuid = "U123456";
        String password = "*******";
        User user = new User("", "");
        when(userRepository.byUuid(uuid)).thenReturn(user);

        applicationService.initPassword(uuid, password);

        verify(userRepository).save(user);
        assertThat(user.getPassword(), is(password));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionGivenNonExistUuid() {
        String nonExistUuid = "U0000";

        applicationService.initPassword(nonExistUuid, "****");

        verify(userRepository).byUuid(nonExistUuid);
        verify(userRepository, times(0)).save(any());
    }
}