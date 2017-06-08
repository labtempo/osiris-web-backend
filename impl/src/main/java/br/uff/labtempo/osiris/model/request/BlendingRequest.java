package br.uff.labtempo.osiris.model.request;

import br.uff.labtempo.osiris.to.common.data.FieldTo;
import br.uff.labtempo.osiris.to.common.definitions.FunctionOperation;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * Request data required to create/update Blending Sensors on VirtualSensorNet Module.
 * @author Andre Ghigo
 * @version 1.0
 * @since 1.8
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BlendingRequest {
    /**
     * name of the function (prefix of the complete function uri)
     * e.g. average = omcp://average.function.osiris/
     * sum = omcp://sum.function.osiris/
     */
    @NotNull @NotEmpty
    private String functionName;

    /**
     * Valid milliseconds number
     */
    @Min(1)
    private long callIntervalInMillis;

    @NotNull @NotEmpty
    private String responseParamName;

    @Min(1)
    private long responseDataTypeId;
}
