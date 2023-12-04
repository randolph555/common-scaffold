package v.show.backend.common.pagination;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页结果对象
 */

@Slf4j
@Data
@ApiModel("分页结果对象")
public class Paging<T> implements Serializable {
    private static final long serialVersionUID = 4784961132604516495L;

    @ApiModelProperty("总行数")
    private long total = 0;

    @ApiModelProperty("数据列表")
    private List<T> records = Collections.emptyList();

    @ApiModelProperty(value = "页码")
    private Long pageIndex;

    @ApiModelProperty(value = "页大小")
    private Long pageSize;

    public Paging() {

    }

    public Paging(IPage<T> page) {
        this.total = page.getTotal();
        this.records = page.getRecords();
        this.pageIndex = page.getCurrent();
        this.pageSize = page.getSize();
    }

}
