package kr.leekyusung.service.report.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServerResDto {
    String serviceName;
    String url;
}
