package com.springing;

import lombok.Data;

@Data
public class ApiDTO {
	private String stationName;  // 대여소 이름
    private Integer parkingBikeTotCnt; // 주차된 자전거 총수
    private String stationLatitude; // 위도
    private String stationLongitude; // 경도
}
