package kr.leekyusung.service.report.schedule;

import kr.leekyusung.service.report.client.AlarmServiceClient;
import kr.leekyusung.service.report.dto.ServerResDto;
import kr.leekyusung.service.report.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@EnableScheduling
@RequiredArgsConstructor
@Component
public class ScheduledTasks {
    private final ServerService serverService;
    private final RestTemplate restTemplate;
    private final AlarmServiceClient alarmServiceClient;

    @Scheduled(fixedDelay = 1000 * 60 * 5) // 5분마다
//    @Scheduled(fixedDelay = 1000 * 30)  // 30초마다 (테스트)
    public void runJob() {
        log.info("runJob");

        List<String> downServices = new ArrayList<>();
        for (ServerResDto serverResDto : serverService.getServer()) {
            log.info(serverResDto.toString());

            try {
                Map<String, Object> response = restTemplate.getForObject(serverResDto.getUrl(), Map.class);

                // 대부분의 actuator/health 응답에서 status 키에 상태 정보가 포함됩니다.
                // 상태가 'UP'이 아니라면 서비스가 다운 상태로 간주합니다.
                if (!"UP".equals(response.get("status"))) {
                    downServices.add(serverResDto.getServiceName());
                }
            } catch (Exception e) {
                // 통신 중 오류가 발생하면 해당 서비스는 다운 상태로 간주합니다.
                downServices.add(serverResDto.getServiceName());
            }
        }

        if (!downServices.isEmpty()) {
            String alertMessage = "다음 서비스들이 다운 상태입니다: " + String.join(", ", downServices);
            log.info("alertMessage: {}", alertMessage);
            try {
                alarmServiceClient.sendAlarm("서비스 다운 알림", alertMessage);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

}
