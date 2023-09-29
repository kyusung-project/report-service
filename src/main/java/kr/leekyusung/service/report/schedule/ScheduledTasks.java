package kr.leekyusung.service.report.schedule;

import kr.leekyusung.service.report.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@EnableScheduling
@RequiredArgsConstructor
@Component
public class ScheduledTasks {
    private final ServerService serverService;

    //    @Scheduled(fixedDelay = 1000 * 60 * 5) // 5분마다
    @Scheduled(fixedDelay = 1000 * 30)  // 30초마다 테스트
    public void runJob() {
        log.info("runJob");
        log.info(serverService.getServer().toString());
    }

}
