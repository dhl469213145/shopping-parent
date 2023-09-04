package com.shopping.order.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class BatchCreateTestOrderDto {

    @ApiModelProperty(value = "总数量")
    @NotNull(message = "总数量不能为空")
    private Long totalSize;

    @ApiModelProperty(value = "每批次数量")
    @NotNull(message = "每批次数量不能为空")
    private Long eachSize;
}
