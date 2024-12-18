package com.br.wb.config;

import com.br.wb.domain.Address;
import com.br.wb.domain.Administrator;
import com.br.wb.domain.Client;
import com.br.wb.domain.Installment;
import com.br.wb.enums.PaymentStatus;
import com.br.wb.enums.Values;
import com.br.wb.respositories.AdministratorRepository;
import com.br.wb.respositories.InstallmentRepository;
import com.br.wb.respositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {
    private final MongoTemplate mongoTemplate;
    private final InstallmentRepository installmentRepository;
    private final ClientRepository clientRepository;
    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        mongoTemplate.getDb().drop();

        List<String> phones = Arrays.asList("11955696863", "11955696864", "11955696865");
        Client.RNMDocument rnm = new Client.RNMDocument("12345640", "classificação", "11/10/2005");
        Address address = new Address("São Paulo", "São paulo", "Brás", "Rua da custura", "01111-000", 1689);
        Client client = new Client(null, "Boliva", "client@gmail.com", encoder.encode("1234"), "111.111.111-111", rnm, "Bolivia", phones, address);
        clientRepository.save(client);

        Administrator administrator = new Administrator(null, "Cristiane", "admin@gmail.com", encoder.encode("1234"));
        administratorRepository.save(administrator);

        // UserId: 123
        Installment installment1 =
                new Installment(null, "123", 1, "processo de naturalização", 229.0, LocalDateTime.parse("2024-03-01T00:00:00"), LocalDateTime.parse("2024-03-30T00:00:00" ), PaymentStatus.PAGO);
        Installment installment2 =
                new Installment(null, "123", 2, "processo de naturalização", 229.0, LocalDateTime.parse("2024-04-01T00:00:00"), LocalDateTime.parse("2024-04-30T00:00:00" ), PaymentStatus.VENCIDO);
        Installment installment3 =
                new Installment(null, "123", 3, "processo de naturalização", 229.0, LocalDateTime.parse("2024-05-01T00:00:00"), LocalDateTime.parse("2024-05-30T00:00:00" ), PaymentStatus.PENDENTE);

        // UserId: 321
        Installment installment4 =
                new Installment(null, client.getId(), 1, "processo de naturalização", 229.0, LocalDateTime.parse("2024-03-01T00:00:00"), LocalDateTime.parse("2024-03-30T00:00:00" ), PaymentStatus.PAGO);
        Installment installment5 =
                new Installment(null, client.getId(), 2, "processo de naturalização", 229.0, LocalDateTime.parse("2024-04-01T00:00:00"), LocalDateTime.parse("2024-04-30T00:00:00" ), PaymentStatus.VENCIDO);
        Installment installment6 =
                new Installment(null, client.getId(), 3, "processo de naturalização", 229.0, LocalDateTime.parse("2024-05-01T00:00:00"), LocalDateTime.parse("2024-05-30T00:00:00" ), PaymentStatus.PENDENTE);

        installmentRepository.saveAll(Arrays.asList(installment1, installment2, installment3, installment4, installment5, installment6));
    }
}
