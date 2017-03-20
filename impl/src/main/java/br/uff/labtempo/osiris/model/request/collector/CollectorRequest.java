package br.uff.labtempo.osiris.model.request.collector;

import lombok.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CollectorRequest {
    private String id;
    private long captureInterval;
    private TimeUnit captureIntervalTimeUnit;
    private Map<String, String> info;

    private boolean isValid() {
        return this.id != null && !this.id.isEmpty()
                && this.captureInterval > 0
                && this.captureIntervalTimeUnit != null;
    }
}
