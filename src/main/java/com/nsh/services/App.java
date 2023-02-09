package com.nsh.services;

import com.nsh.services.lamps.model.DefectNomenclature;
import com.nsh.services.lamps.model.JobNomenclature;
import com.nsh.services.lamps.model.MaterialNomenclature;
import com.nsh.services.lamps.service.DefectNomenclature.DefectNomenclatureService;
import com.nsh.services.lamps.service.JobNomenclature.JobNomenclatureService;
import com.nsh.services.lamps.service.MaterialNomenclature.MaterialNomenclatureService;
import com.nsh.services.user.enums.UserStatus;
import com.nsh.services.user.model.Role;
import com.nsh.services.user.model.User;
import com.nsh.services.user.roleService.RoleService;
import com.nsh.services.user.userService.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
//@EnableMethodSecurity
public class App implements CommandLineRunner {
    private Logger logger = LogManager.getLogger(App.class);
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private DefectNomenclatureService defectNomenclatureService;
    @Autowired
    private MaterialNomenclatureService materialNomenclatureService;
    @Autowired
    private JobNomenclatureService jobNomenclatureService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (defectNomenclatureService.getDefectNomenclatureCount() == 0)
            registerDefectNomenclature();

        if (materialNomenclatureService.getMaterialNomenclatureCount() == 0)
            registerMaterialNomenclature();

        if (jobNomenclatureService.getJobNomenclatureCount() == 0)
            registerJobNomenclature();

        if (roleService.count() == 0)
            registerRole();

        if (userService.count() == 0)
            registerUser();
    }


    public void registerDefectNomenclature() {
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Нет в системе позиционирования").build());
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Нет ответа от радио сигнализатора").build());
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Нет ближнего или дальнего света").build());
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Деградация светодиода").build());
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Повреждено защитное стекло").build());
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Поврежден корпус").build());
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Повреждено головное крепление").build());
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Моргает как при вызове").build());
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Моргает как при замыкании").build());
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Нет зарядка АКБ").build());
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Быстро разряжается").build());
        defectNomenclatureService.save(DefectNomenclature.builder().id(0).nomenclature("Поврежден токонесущий шнур").build());
    }

    public void registerMaterialNomenclature() {
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Аккумулятор НРГ 7 А/ч Ni-MH для головных светильников НГР").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Блок НГР искрозащиты").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Модуль НГР светодиодный (светодиод с отражателем) для головных светильников НГР").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Модуль управления светом светильника НГР 05").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Разьем ВРШИ-М 1.5-4 вибростойкий штекерный \"мама\"").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Разьем ВРШИ-П 1.5-4 вибростойкий штекерный \"папа\"").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Шнур ШАСРВм 2х1-12В").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Стекло к СГГ-5М (8.06.402.068)").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Фара НГР в сборе (фара, провод, крышка, искробезопасный блок").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Светильник НГР 06-4-003.01.05 головной шахтный взрывозащищенный").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Плобма самоклеющаяся").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Радиоблок СУБР-01СМ-04").build());
        materialNomenclatureService.save(MaterialNomenclature.builder().id(0).nomenclature("Радиоблок СУБР-01СМ-07").build());


    }

    public void registerJobNomenclature() {
        jobNomenclatureService.save(JobNomenclature.builder().id(0).nomenclature("Ремонт электрической цепи").build());
        jobNomenclatureService.save(JobNomenclature.builder().id(0).nomenclature("Зарядка АКБ").build());
        jobNomenclatureService.save(JobNomenclature.builder().id(0).nomenclature("Обновление микро ПО радио блока").build());

    }

    public void registerRole() {
        roleService.saveRole(Role.builder().id(0L).role("ROLE_ADMIN").build());
        roleService.saveRole(Role.builder().id(0L).role("ROLE_USER").build());
        roleService.saveRole(Role.builder().id(0L).role("ROLE_TECHNICAL").build());

    }

    public void registerUser() {
        Role admin = roleService.findByRole("ROLE_ADMIN");
        Role user = roleService.findByRole("ROLE_USER");
        Role technical = roleService.findByRole("ROLE_TECHNICAL");

        User userAdmin = User.builder()
                .id(0L)
                .username("ADMIN")
                .fullName("Администратор")
                .password(bCryptPasswordEncoder.encode("lost"))
                .userStatus(UserStatus.ACTIVE)
                .roles(List.of(admin, user, technical))
                .build();

        User userUser = User.builder()
                .id(0L)
                .username("Lamp")
                .fullName("Дежурный ламповщик")
                .password(bCryptPasswordEncoder.encode("lost"))
                .userStatus(UserStatus.ACTIVE)
                .roles(List.of(user))
                .build();

        User technicalUser1 = User.builder()
                .id(0L)
                .username("Tech1")
                .fullName("Технический специалист 1")
                .password(bCryptPasswordEncoder.encode("lost"))
                .userStatus(UserStatus.ACTIVE)
                .roles(List.of(technical))
                .build();

        User technicalUser2 = User.builder()
                .id(0L)
                .username("Tech2")
                .fullName("Технический специалист 2")
                .password(bCryptPasswordEncoder.encode("lost"))
                .userStatus(UserStatus.ACTIVE)
                .roles(List.of(technical))
                .build();


        User registeredAdmin = userService.register(userAdmin);
        User registeredUser = userService.register(userUser);
        User registeredTechnical1 = userService.register(technicalUser1);
        User registeredTechnical2 = userService.register(technicalUser2);

    }
}

