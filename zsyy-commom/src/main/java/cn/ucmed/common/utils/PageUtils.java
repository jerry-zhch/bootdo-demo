package cn.ucmed.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 */
public class PageUtils<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long total;
	private List<T> rows;

	public PageUtils(List<T> list, Long total) {
		this.rows = list;
		this.total = total;
	}

	/**
	 * 通过bootstrapTable获取mybatis-plus的分页包装
	 */
	public static IPage getIPage(JSONObject data){
		long offset = data.getLong("offset");
		if (offset== 0L) {
			offset = 1;
		}
		long limit = data.getLong("limit");
		if (limit == 0L) {
			limit = 1000000;
		}
		long pages = offset / limit + 1;
		return new Page(pages, limit);
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
