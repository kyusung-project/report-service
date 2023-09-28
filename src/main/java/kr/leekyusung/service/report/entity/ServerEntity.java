package kr.leekyusung.service.report.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.leekyusung.service.report.dto.ServerResDto;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seqNo;

    String serviceName;
    String url;

    public ServerResDto toServerResDto() {
        return ServerResDto.builder()
                .serviceName(this.serviceName)
                .url(this.url)
                .build();
    }
}
