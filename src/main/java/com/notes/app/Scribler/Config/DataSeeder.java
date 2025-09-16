package com.notes.app.Scribler.Config;

import com.notes.app.Scribler.Entity.Tenant;
import com.notes.app.Scribler.Entity.User;
import com.notes.app.Scribler.Repository.NoteRepository;
import com.notes.app.Scribler.Repository.TenantRepository;
import com.notes.app.Scribler.Repository.UserRepository;
import com.notes.app.Scribler.enums.Role;
import com.notes.app.Scribler.enums.SubType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSeeder  implements CommandLineRunner {
    private final UserRepository userRepository;
    private  final NoteRepository noteRepository;
    private final TenantRepository tenantRepository;

    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, NoteRepository noteRepository, TenantRepository tenantRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.tenantRepository = tenantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        noteRepository.deleteAll();
        tenantRepository.deleteAll();

        Tenant acme = new Tenant();
        acme.setName("Acme");
        acme.setSlug("acme");
        acme.setSubType(SubType.FREE);
        tenantRepository.save(acme);

        Tenant globex = new Tenant();
        globex.setName("Globex");
        globex.setSlug("globex");
        globex.setSubType(SubType.FREE);
        tenantRepository.save(globex);

        User adminAcme = new User();
        adminAcme.setName("adminAcme");
        adminAcme.setEmail("admin@acme.test");
        adminAcme.setPassword(passwordEncoder.encode("admin123"));
        adminAcme.setRole(Role.ADMIN);
        adminAcme.setTenant(acme);

        User memberAcme = new User();
        memberAcme.setName("memberAcme");
        memberAcme.setEmail("user@acme.test");
        memberAcme.setPassword(passwordEncoder.encode("member123"));
        memberAcme.setRole(Role.MEMBER);
        memberAcme.setTenant(acme);

        User adminGlobex = new User();
        adminGlobex.setName("adminGlobex");
        adminGlobex.setEmail("admin@globex.test");
        adminGlobex.setPassword(passwordEncoder.encode("admin123"));
        adminGlobex.setRole(Role.ADMIN);
        adminGlobex.setTenant(globex);

        User memberGlobex = new User();
        memberGlobex.setName("memberGlobex");
        memberGlobex.setEmail("user@globex.test");
        memberGlobex.setPassword(passwordEncoder.encode("member123"));
        memberGlobex.setRole(Role.MEMBER);
        memberGlobex.setTenant(globex);

        userRepository.saveAll(Arrays.asList(
                memberAcme, memberGlobex,
                adminGlobex, adminAcme
        ));
    }
}
