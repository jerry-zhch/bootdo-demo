package cn.ucmed;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan({"cn.ucmed.common.db.*.mapper","cn.ucmed.common.dbplus.mapper"})
@EnableCaching  // 开启redis注解
@EnableAsync
@Slf4j
public class BootDoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootDoDemoApplication.class, args);
		log.info("======================project started!!!============================");
	}

}

