package mylab.notification.di.annot.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.SmsNotificationService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class)
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    public void testNotificationManager() {
        // 관리 객체가 주입되었는지 확인
        assertNotNull(notificationManager);

        // 이메일 서비스 주입 및 구현 클래스 확인
        assertNotNull(notificationManager.getEmailService());
        assertTrue(notificationManager.getEmailService()
                instanceof EmailNotificationService);

        EmailNotificationService emailService =
                (EmailNotificationService)
                notificationManager.getEmailService();

        // 이메일 설정값 확인
        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());

        // SMS 서비스 주입 및 구현 클래스 확인
        assertNotNull(notificationManager.getSmsService());
        assertTrue(notificationManager.getSmsService()
                instanceof SmsNotificationService);

        SmsNotificationService smsService =
                (SmsNotificationService)
                notificationManager.getSmsService();

        // SMS 설정값 확인
        assertEquals("SKT", smsService.getProvider());

        // 관리 객체를 통해 각 서비스 실행
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}