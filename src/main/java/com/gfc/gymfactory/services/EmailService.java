package com.gfc.gymfactory.services;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final Resend resend;

    @Value("${resend.from}")
    private String from;

    public void sendWelcomeEmail(String to, String name, String tempPassword) throws ResendException {
        CreateEmailOptions options = CreateEmailOptions.builder()
                .from(from)
                .to(List.of(to))
                .subject("Bem-vindo ao GymFactory!")
                .html(buildWelcomeEmailBody(name, tempPassword))
                .build();

        resend.emails().send(options);
    }

    public void sendPasswordResetCode(String to, String name, String code) throws ResendException {
        CreateEmailOptions options = CreateEmailOptions.builder()
                .from(from)
                .to(List.of(to))
                .subject("Código de recuperação de senha — GymFactory")
                .html(buildPasswordResetEmailBody(name, code))
                .build();

        resend.emails().send(options);
    }

    private String buildWelcomeEmailBody(String name, String tempPassword) {
        return """
                <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto;">
                    <div style="background-color: #0f0f0f; padding: 20px; text-align: center;">
                        <h1 style="color: #ffffff; margin: 0;">
                            Gym<span style="color: #dc2626;">Factory</span>
                        </h1>
                    </div>
                    <div style="padding: 30px; background-color: #ffffff;">
                        <p style="font-size: 16px;">Olá, <strong>%s</strong>! Sua conta foi criada.</p>
                        <p style="font-size: 14px; color: #555;">
                            Use a senha temporária abaixo para acessar o sistema:
                        </p>
                        <div style="background-color: #f5f5f5; padding: 15px; text-align: center; border-radius: 4px; margin: 20px 0;">
                            <span style="font-size: 24px; font-weight: bold; letter-spacing: 4px; color: #0f0f0f;">%s</span>
                        </div>
                        <p style="font-size: 13px; color: #999;">
                            Recomendamos que você troque sua senha após o primeiro acesso.
                        </p>
                    </div>
                    <div style="background-color: #f5f5f5; padding: 15px; text-align: center;">
                        <p style="font-size: 12px; color: #999; margin: 0;">
                            GymFactory — Seu treino, sua evolução
                        </p>
                    </div>
                </div>
                """.formatted(name, tempPassword);
    }

    private String buildPasswordResetEmailBody(String name, String code) {
        return """
                <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto;">
                    <div style="background-color: #0f0f0f; padding: 20px; text-align: center;">
                        <h1 style="color: #ffffff; margin: 0;">
                            Gym<span style="color: #dc2626;">Factory</span>
                        </h1>
                    </div>
                    <div style="padding: 30px; background-color: #ffffff;">
                        <p style="font-size: 16px;">Olá, <strong>%s</strong>!</p>
                        <p style="font-size: 14px; color: #555;">
                            Recebemos uma solicitação de recuperação de senha. Use o código abaixo:
                        </p>
                        <div style="background-color: #f5f5f5; padding: 15px; text-align: center; border-radius: 4px; margin: 20px 0;">
                            <span style="font-size: 32px; font-weight: bold; letter-spacing: 8px; color: #dc2626;">%s</span>
                        </div>
                        <p style="font-size: 13px; color: #999;">
                            Este código expira em 15 minutos. Se você não solicitou, ignore este email.
                        </p>
                    </div>
                    <div style="background-color: #f5f5f5; padding: 15px; text-align: center;">
                        <p style="font-size: 12px; color: #999; margin: 0;">
                            GymFactory — Seu treino, sua evolução
                        </p>
                    </div>
                </div>
                """.formatted(name, code);
    }
}
