package cn.ucmed.admin.controller;

import cn.ucmed.common.config.CommonProperties;
import cn.ucmed.common.dbplus.entity.SysFile;
import cn.ucmed.common.dbplus.service.ISysFileService;
import cn.ucmed.common.utils.FileType;
import cn.ucmed.common.utils.FileUtil;
import cn.ucmed.common.utils.PageUtils;
import cn.ucmed.common.utils.result.Result;
import cn.ucmed.common.utils.result.ResultUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 文件上传
 */
@Slf4j
@Controller
@RequestMapping("/admin/common/sysFile")
public class FileController extends SysBaseController {

	@Autowired
	private ISysFileService sysFileService;
	@Autowired
    private CommonProperties commonProperties;

	@GetMapping()
//	@RequiresPermissions("common:sysFile:sysFile")
	private String sysFile(Model model) {
		Map<String, Object> params = new HashMap<>(16);
		return "common/file/file";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:sysFile:sysFile")
	public PageUtils list(@RequestParam Map<String, Object> params) {
	    log.info("list param:"+ JSONObject.toJSONString(params));
		// 查询列表数据
		List<SysFile> sysFileList = sysFileService.list(null);
        return new PageUtils(sysFileList, null == sysFileList ? 0 : (long) sysFileList.size());
	}

	@GetMapping("/add")
	// @RequiresPermissions("common:bComments")
	public String add() {
		return "common/sysFile/add";
	}

	@GetMapping("/edit")
	// @RequiresPermissions("common:bComments")
	public String edit(Long id, Model model) {
		SysFile sysFile = sysFileService.getById(id);
		model.addAttribute("sysFile", sysFile);
		return "common/sysFile/edit";
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
//	@RequiresPermissions("common:info")
	public Result info(@PathVariable("id") Long id) {
		SysFile sysFile = sysFileService.getById(id);
		return ResultUtils.success(sysFile);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("common:save")
	public Result save(SysFile sysFile) {
		if (sysFileService.save(sysFile)) {
			return ResultUtils.success();
		}
		return ResultUtils.error("保存到数据库失败");
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
//	@RequiresPermissions("common:update")
	public Result update(@RequestBody SysFile sysFile) {
		if (sysFileService.updateById(sysFile)){
		    return ResultUtils.success();
        }
		return ResultUtils.error("更新数据库失败");
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	// @RequiresPermissions("common:remove")
	public Result remove(Long id, HttpServletRequest request) {

		String fileName = commonProperties.getProfile() + sysFileService.getById(id).getUrl().replace("/files/", "");
		if (sysFileService.removeById(id)) {
			boolean b = FileUtil.deleteFile(fileName);
			if (!b) {
				return ResultUtils.error("数据库记录删除成功，文件删除失败");
			}
			return ResultUtils.success();
		} else {
			return ResultUtils.error("数据库记录删除失败");
		}
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
//	@RequiresPermissions("common:remove")
	public Result remove(@RequestParam("ids[]") Long[] ids) {
		sysFileService.removeByIds(Arrays.asList(ids));
		return ResultUtils.success();
	}

	@ResponseBody
	@PostMapping("/upload")
	public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

		String fileName = file.getOriginalFilename();
        assert fileName != null;
        fileName = FileUtil.renameToUUID(fileName);
		SysFile sysFile = new SysFile();
		sysFile.setType(FileType.fileType(fileName));
		sysFile.setUrl("/files/" + fileName);
		sysFile.setCreateDate(new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), commonProperties.getProfile(), fileName);
		} catch (Exception e) {
			return ResultUtils.error("上传失败"+e.getMessage());
		}

		if (sysFileService.save(sysFile)) {
			return ResultUtils.success(sysFile.getUrl());
		}
		return ResultUtils.error("保存到数据库失败");
	}

}
