package cn.ucmed.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query<T> extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	// 
	private int offset;
	// 每页条数
	private int limit;

	/**
	 * mybatis-plus分页参数
	 */
	private Page<T> page;

	public Query(Map<String, Object> params) {
		this.putAll(params);
		// 分页参数
		this.offset = Integer.parseInt(params.get("offset").toString());
		this.limit = Integer.parseInt(params.get("limit").toString());
		this.put("offset", offset);
		this.put("page", offset / limit + 1);
		this.put("limit", limit);
		//mybatis-plus分页
		this.page = new Page<>(offset / limit + 1, limit);
	}

	public Page<T> getPage() {
		return page;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.put("offset", offset);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
