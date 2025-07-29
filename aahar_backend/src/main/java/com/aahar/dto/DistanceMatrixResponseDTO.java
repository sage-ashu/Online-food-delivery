// dto/DistanceMatrixResponse.java
package com.aahar.dto;

import lombok.Data;
import java.util.List;

@Data
public class DistanceMatrixResponseDTO {
    private List<Row> rows;

    @Data
    public static class Row {
        private List<Element> elements;
    }

    @Data
    public static class Element {
        private Distance distance;
        private Duration duration;
    }

    @Data
    public static class Distance {
        private String text;
        private int value; // distance in meters
    }

    @Data
    public static class Duration {
        private String text;
        private int value; // time in seconds
    }
}
//package com.aahar.dto;
//
//public class DistanceMatrixResponseDTO {
//
//}
