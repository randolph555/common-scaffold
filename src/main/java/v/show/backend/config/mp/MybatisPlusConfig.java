package v.show.backend.config.mp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
@MapperScan("v.show.backend.biz.*.mapper")
@EnableTransactionManagement
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        List<InnerInterceptor> list = Arrays.asList(
                new PaginationInnerInterceptor(DbType.MYSQL),
                new OptimisticLockerInnerInterceptor());
        mybatisPlusInterceptor.setInterceptors(list);
        return mybatisPlusInterceptor;
    }

    public MybatisPlusConfig() {
        log.info("MybatisPlusConfig start ..");
    }
}
